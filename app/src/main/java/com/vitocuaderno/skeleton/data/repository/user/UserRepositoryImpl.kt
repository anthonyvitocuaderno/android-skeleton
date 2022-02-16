package com.vitocuaderno.skeleton.data.repository.user

import com.vitocuaderno.skeleton.data.local.AppDatabase
import com.vitocuaderno.skeleton.data.remote.ApiService
import com.vitocuaderno.skeleton.data.remote.models.User
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val db: AppDatabase,
) : UserRepository {
    override fun getAsync(id: String): Deferred<User> = GlobalScope.async {
        val response = apiService.getUser(id)

        // TODO db update user

        response
    }
}
