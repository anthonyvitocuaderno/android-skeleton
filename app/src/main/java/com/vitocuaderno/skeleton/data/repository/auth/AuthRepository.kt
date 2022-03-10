package com.vitocuaderno.skeleton.data.repository.auth

import com.vitocuaderno.skeleton.data.remote.models.SessionResponse
import kotlinx.coroutines.Deferred

interface AuthRepository {
    suspend fun login(username: String, password: String): String
    fun registerAsync(username: String, password: String): Deferred<String>
    fun getSession(): SessionResponse?
    fun logoutAsync(): Deferred<Unit>
}
