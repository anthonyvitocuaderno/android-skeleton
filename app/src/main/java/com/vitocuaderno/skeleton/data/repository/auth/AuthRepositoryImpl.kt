package com.vitocuaderno.skeleton.data.repository.auth

import android.content.SharedPreferences
import com.vitocuaderno.skeleton.data.local.AppDatabase
import com.vitocuaderno.skeleton.data.remote.ApiService
import com.vitocuaderno.skeleton.data.remote.models.SessionResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val db: AppDatabase,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {
    override suspend fun login(username: String, password: String): String {
        val session = apiService.login(username, password)

        // TODO validate session
        // Mock user id = 4
        saveSession(session.id, username, session.token)

        return session.token
    }

    override fun registerAsync(username: String, password: String): Deferred<String> = GlobalScope.async {
        val session = apiService.register(username, password)

        // TODO validate session

        saveSession(session.id, username, session.token)

        session.token
    }

    override fun getSession(): SessionResponse? {
        /***
         * Async for any server-side verification
         */
        return try {
            val id = sharedPreferences.getInt(PREF_SESSION_ID, -1)
            val username = sharedPreferences.getString(PREF_SESSION_USERNAME, "")
            val token = sharedPreferences.getString(PREF_SESSION_TOKEN, "")
            if (id == -1 || username.isNullOrEmpty() || token.isNullOrEmpty()) {
                null
            } else {
                SessionResponse(token, id, username)
            }
        } catch (e: Exception) {
            null
        }
    }

    override fun logoutAsync(): Deferred<Unit> = GlobalScope.async {
        db.userDao().clear()
        clearSession()
    }

    private fun saveSession(id: Int, username: String, token: String) {
        sharedPreferences.edit().apply {
            putInt(PREF_SESSION_ID, id)
            putString(PREF_SESSION_USERNAME, username)
            putString(PREF_SESSION_TOKEN, token)
        }.apply()
    }

    private fun clearSession() {
        sharedPreferences.edit().apply {
            remove(PREF_SESSION_ID)
            remove(PREF_SESSION_USERNAME)
            remove(PREF_SESSION_TOKEN)
        }.apply()
    }

    companion object {
        private const val PREF_SESSION_ID = "PREF_SESSION_ID"
        private const val PREF_SESSION_TOKEN = "PREF_SESSION_TOKEN"
        private const val PREF_SESSION_USERNAME = "PREF_SESSION_USERNAME"
    }
}
