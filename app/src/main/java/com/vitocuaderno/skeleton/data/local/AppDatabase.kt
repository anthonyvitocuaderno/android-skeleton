package com.vitocuaderno.skeleton.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.vitocuaderno.skeleton.data.local.converters.Converters
import com.vitocuaderno.skeleton.data.local.dao.UserDao
import com.vitocuaderno.skeleton.data.local.models.User
import com.vitocuaderno.skeleton.utils.DATABASE_NAME
import com.vitocuaderno.skeleton.workers.SeedDatabaseWorker

/**
 * The Room database for this app
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            // Create and pre-populate the database. See this article for more details:
                            // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}
