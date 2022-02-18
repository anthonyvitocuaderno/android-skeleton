package com.vitocuaderno.skeleton.data.repository.user

import androidx.paging.PagingSource
import com.vitocuaderno.skeleton.data.local.models.User
import kotlinx.coroutines.Deferred

interface UserRepository {
    fun getAsync(id: String): Deferred<User>
    fun getAllPaged(): PagingSource<Int, User>
}
