package com.vitocuaderno.skeleton.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vitocuaderno.skeleton.data.local.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY id")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM users ORDER BY id")
    fun getAllPaged(): PagingSource<Int, User>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getOne(id: Int): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(collection: List<User>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: User)

    @Query("DELETE FROM users")
    suspend fun clear()
}
