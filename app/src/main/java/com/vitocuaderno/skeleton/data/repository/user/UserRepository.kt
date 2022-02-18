package com.vitocuaderno.skeleton.data.repository.user

import androidx.paging.PagingData
import com.vitocuaderno.skeleton.data.local.models.User
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAsync(id: String): Deferred<User>
    fun getAll(): Flow<PagingData<User>>
}
