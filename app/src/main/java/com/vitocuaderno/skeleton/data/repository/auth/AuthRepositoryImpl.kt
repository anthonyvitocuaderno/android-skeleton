package com.vitocuaderno.skeleton.data.repository.auth

import com.vitocuaderno.skeleton.data.local.AppDatabase
import com.vitocuaderno.skeleton.data.remote.ApiService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val db: AppDatabase
) : AuthRepository {
    override fun loginAsync(username: String, password: String): Deferred<String> = GlobalScope.async {
        val response = apiService.login(username, password)
        response.token
    }

    override fun registerAsync(username: String, password: String): Deferred<String> = GlobalScope.async {
        val response = apiService.register(username, password)
        response.token
    }
}
