package com.vitocuaderno.skeleton.data.repository.auth

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vitocuaderno.skeleton.data.local.AppDatabase
import com.vitocuaderno.skeleton.data.remote.ApiService
import com.vitocuaderno.skeleton.data.remote.models.SessionResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class AuthRepositoryImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: AuthRepository

    private val mockApi = mock(ApiService::class.java)

    private val mockSharedPrefs = mock(SharedPreferences::class.java)

    private val mockDatabase = mock(AppDatabase::class.java)

    @Before
    fun setUp() {
        subject = AuthRepositoryImpl(
            mockApi,
            mockDatabase,
            mockSharedPrefs
        )
    }

    @Test
    fun loginShouldReturnSessionToken() = runBlockingTest {

        val username = "test@email.com"
        val password = "12345678"
        val response = SessionResponse("sample token", "1")
        val expected = "sample token"

        `when`(mockApi.login(anyString(), anyString()))
            .thenReturn(response)

        val editor = mock(SharedPreferences.Editor::class.java)
        `when`(mockSharedPrefs.edit()).thenReturn(editor)

        val result = subject.login(username, password)

        Assert.assertTrue("invalid session token returned", result == expected)

        verify(mockApi, times(1)).login(anyString(), anyString())
        verify(mockSharedPrefs, times(1)).edit()
    }
}
