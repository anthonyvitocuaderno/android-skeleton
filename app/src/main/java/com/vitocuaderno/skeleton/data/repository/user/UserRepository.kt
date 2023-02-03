package com.vitocuaderno.skeleton.data.repository.user

import androidx.paging.PagingData
import com.vitocuaderno.skeleton.data.local.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getAsync(id: String): User
    fun getAll(): Flow<PagingData<User>>
}
