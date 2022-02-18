package com.vitocuaderno.skeleton.data.repository.user

import androidx.paging.*
import com.vitocuaderno.skeleton.data.local.AppDatabase
import com.vitocuaderno.skeleton.data.local.models.User
import com.vitocuaderno.skeleton.data.remote.ApiService
import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import com.vitocuaderno.skeleton.utils.PAGE_SIZE
import com.vitocuaderno.skeleton.utils.PAGING_MAX_SIZE
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val db: AppDatabase,
    private val authRepository: AuthRepository
) : UserRepository {
    override fun getAsync(id: String): Deferred<User> = GlobalScope.async {
        val response = apiService.getUser(id)
        // TODO db update user
        response.toLocal()
    }

    override fun getAll(): Flow<PagingData<User>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE,
            enablePlaceholders = true,
            maxSize = PAGING_MAX_SIZE
        ),
        remoteMediator = PageKeyedRemoteMediator(db, apiService, authRepository)
    ) {
        db.userDao().getAllPaged()
    }.flow
}

@OptIn(ExperimentalPagingApi::class)
private class PageKeyedRemoteMediator(
    private val db: AppDatabase,
    private val apiService: ApiService,
    private val authRepository: AuthRepository
) : RemoteMediator<Int, User>() {

    private var key: Long = 0

    override suspend fun initialize(): InitializeAction {
        // Require that remote REFRESH is launched on initial load and succeeds before launching
        // remote PREPEND / APPEND.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, User>
    ): MediatorResult {
        try {

            val page = key / state.config.pageSize + 1
            authRepository.getSession()?.let { session ->
                val response = apiService.getUsers(
                    token = session.token,
                    perPage = state.config.pageSize,
                    page = page.toInt()
                )
                val users = response.data.map { userResponse ->
                    userResponse.toLocal()
                }
                val keys = db.userDao().insertAll(users)
                if (keys.isEmpty()) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                key = keys.last()
            }

            return MediatorResult.Success(endOfPaginationReached = state.isEmpty())
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}
