package com.vitocuaderno.skeleton.data.remote.models

import com.google.gson.annotations.SerializedName
import com.vitocuaderno.skeleton.data.local.models.User

data class UserResponse(
    val id: String,
    val email: String,
    @field:SerializedName("first_name") val firstName: String,
    @field:SerializedName("last_name") val lastName: String,
    val avatar: String
) {
    fun toLocal(): User {
        this.apply {
            return User(
                id.toInt(),
                email,
                firstName,
                lastName,
                avatar
            )
        }
    }
}
