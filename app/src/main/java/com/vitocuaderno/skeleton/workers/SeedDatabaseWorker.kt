package com.vitocuaderno.skeleton.workers
import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.vitocuaderno.skeleton.data.local.AppDatabase
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker @WorkerInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {

        try {
            val database = AppDatabase.getInstance(applicationContext)
            // TODO seeding
            Result.success()
        } catch (ex: Exception) {
            Result.retry()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}
