package com.vitocuaderno.skeleton.data.repository.user

import com.vitocuaderno.skeleton.data.remote.models.User
import kotlinx.coroutines.Deferred

interface UserRepository {
    fun getAsync(id: String): Deferred<User>
}
