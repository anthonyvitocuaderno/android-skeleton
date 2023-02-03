package com.vitocuaderno.skeleton.data.repository.auth

import com.vitocuaderno.skeleton.data.remote.models.SessionResponse

interface AuthRepository {
    suspend fun login(username: String, password: String): String
    suspend fun registerAsync(username: String, password: String): String
    fun getSession(): SessionResponse?
    suspend fun logoutAsync()
}
