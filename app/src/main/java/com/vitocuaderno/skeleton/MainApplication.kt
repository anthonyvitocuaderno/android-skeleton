package com.vitocuaderno.skeleton

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.vitocuaderno.skeleton.data.remote.ApiService
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/***
 * https://console.firebase.google.com/u/0/project/sample-kotlin-app-5f4d2/crashlytics/app/android:com.vitocuaderno.skeleton/issues?state=open&time=last-seven-days&tag=all
 */
@HiltAndroidApp
class MainApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var apiService: ApiService

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        apiService
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
