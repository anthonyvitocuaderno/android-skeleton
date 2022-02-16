package com.vitocuaderno.skeleton.data.repository.auth

import com.vitocuaderno.skeleton.data.remote.models.Session
import kotlinx.coroutines.Deferred

interface AuthRepository {
    fun loginAsync(username: String, password: String): Deferred<String>
    fun registerAsync(username: String, password: String): Deferred<String>
    fun getSessionAsync(): Deferred<Session?>
    fun logoutAsync(): Deferred<Unit>
}
