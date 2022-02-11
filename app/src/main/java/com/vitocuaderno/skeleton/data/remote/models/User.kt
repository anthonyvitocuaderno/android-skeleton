package com.vitocuaderno.skeleton.data.remote.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.vitocuaderno.skeleton.data.local.models.UserDb

data class User(
    @PrimaryKey @ColumnInfo(name = "id") val id: String
) {
    companion object {
        fun toLocal(user: User): UserDb {
            return UserDb("1")
        }
    }
}
