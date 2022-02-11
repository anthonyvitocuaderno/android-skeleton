package com.vitocuaderno.skeleton.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vitocuaderno.skeleton.data.remote.models.User

@Entity(tableName = "users")
data class UserDb(
    @PrimaryKey @ColumnInfo(name = "id") val id: String
) {
    companion object {
        fun toRemote(userDb: UserDb): User {
            return User("1")
        }
    }
}
