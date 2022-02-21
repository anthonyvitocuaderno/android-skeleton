package com.vitocuaderno.skeleton.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vitocuaderno.skeleton.data.remote.models.UserResponse

@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
) {
    fun toRemote(): UserResponse {
        this.apply {
            return UserResponse(
                id.toString(),
                email,
                firstName,
                lastName,
                avatar
            )
        }
    }
}
