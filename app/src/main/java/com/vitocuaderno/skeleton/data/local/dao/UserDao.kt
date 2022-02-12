package com.vitocuaderno.skeleton.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vitocuaderno.skeleton.data.local.models.UserDb
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY id")
    fun getAll(): Flow<List<UserDb>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getOne(id: String): Flow<UserDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(collection: List<UserDb>)
}
