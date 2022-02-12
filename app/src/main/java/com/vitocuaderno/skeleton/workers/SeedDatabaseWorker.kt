package com.vitocuaderno.skeleton.workers
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.vitocuaderno.skeleton.data.local.AppDatabase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker @AssistedInject constructor(
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
