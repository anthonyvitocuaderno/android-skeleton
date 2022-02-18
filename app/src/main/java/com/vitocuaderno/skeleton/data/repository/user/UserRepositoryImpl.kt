package com.vitocuaderno.skeleton.data.repository.user

import androidx.paging.PagingSource
import com.vitocuaderno.skeleton.data.local.AppDatabase
import com.vitocuaderno.skeleton.data.local.models.User
import com.vitocuaderno.skeleton.data.remote.ApiService
import com.vitocuaderno.skeleton.data.remote.models.UserResponse
import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
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
        UserResponse.toLocal(response)
    }

    override fun getAllPaged(): PagingSource<Int, User> {
        GlobalScope.launch {
            authRepository.getSessionAsync().await()?.let {
                var nextPage: Int? = 1
//                while (nextPage != null) {
                    val response = apiService.getUsers(
                        page = nextPage!!,
                        perPage = 10,
                        token = it.token
                    )

                    response.data.map { userResponse ->
                        db.userDao().insertAll(listOf(UserResponse.toLocal(userResponse)))
                    }
                    if (nextPage < response.totalPages) {
                        nextPage = response.page + 1
                    }
//                }
            }
        }

        return db.userDao().getAllPaged()
    }
}
