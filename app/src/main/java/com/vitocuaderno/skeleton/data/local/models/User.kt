package com.vitocuaderno.skeleton.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vitocuaderno.skeleton.data.remote.models.UserResponse

@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
) {
    companion object {
        fun toRemote(user: User): UserResponse {
            user.apply {
                return UserResponse(
                    id,
                    email,
                    firstName,
                    lastName,
                    avatar
                )
            }
        }
    }
}
