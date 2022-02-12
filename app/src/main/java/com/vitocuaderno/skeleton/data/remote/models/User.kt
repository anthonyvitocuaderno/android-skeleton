package com.vitocuaderno.skeleton.data.remote.models

import com.vitocuaderno.skeleton.data.local.models.UserDb

data class User(
    val id: String
) {
    companion object {
        fun toLocal(user: User): UserDb {
            return UserDb("1")
        }
    }
}
