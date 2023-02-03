package com.vitocuaderno.skeleton.data.repository.auth

import android.content.SharedPreferences
import com.vitocuaderno.skeleton.data.local.AppDatabase
import com.vitocuaderno.skeleton.data.remote.ApiService
import com.vitocuaderno.skeleton.data.remote.models.SessionResponse
import com.vitocuaderno.skeleton.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val db: AppDatabase,
    private val sharedPreferences: SharedPreferences,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {
    override suspend fun login(username: String, password: String): String = withContext(ioDispatcher) {
        val session = apiService.login(username, password)

        // TODO validate session
        // Mock user id = 4
        saveSession("4", session.token)

        session.token
    }

    override suspend fun registerAsync(username: String, password: String): String = withContext(ioDispatcher) {
        val session = apiService.register(username, password)

        // TODO validate session

        saveSession(session.id, session.token)

        session.token
    }

    override fun getSession(): SessionResponse? {
        /***
         * Async for any server-side verification
         */
        return try {
            val id = sharedPreferences.getString(PREF_SESSION_ID, "")
            val token = sharedPreferences.getString(PREF_SESSION_TOKEN, "")
            if (id.isNullOrEmpty() || token.isNullOrEmpty()) {
                null
            } else {
                SessionResponse(token, id)
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun logoutAsync() = withContext(ioDispatcher) {
        db.userDao().clear()
        clearSession()
    }

    private fun saveSession(id: String, token: String) {
        sharedPreferences.edit().apply {
            putString(PREF_SESSION_ID, id)
            putString(PREF_SESSION_TOKEN, token)
        }.apply()
    }

    private fun clearSession() {
        sharedPreferences.edit().apply {
            remove(PREF_SESSION_ID)
            remove(PREF_SESSION_TOKEN)
        }.apply()
    }

    companion object {
        private const val PREF_SESSION_ID = "PREF_SESSION_ID"
        private const val PREF_SESSION_TOKEN = "PREF_SESSION_TOKEN"
    }
}
