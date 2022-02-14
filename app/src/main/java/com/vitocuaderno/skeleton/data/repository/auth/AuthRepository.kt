package com.vitocuaderno.skeleton.data.repository.auth

import kotlinx.coroutines.Deferred

interface AuthRepository {
    fun loginAsync(username: String, password: String): Deferred<String>
    fun registerAsync(username: String, password: String): Deferred<String>
}
