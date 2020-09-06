package com.socialevoeding.bap

import android.app.Application
import android.os.Build
import androidx.work.*
import com.socialevoeding.presentation_android.di.presentationModule
import com.socialevoeding.bap.work.RefreshDataWorker
import com.socialevoeding.data.di.dataModule
import com.socialevoeding.framework_androidsdk.di.frameworkAndroidModule
import com.socialevoeding.framework_shared.di.frameworkSharedModule
import com.socialevoeding.usecases.di.useCaseModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

/**
 * Initializing Koin for dependency injection
 */
class Application : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(
                dataModule,
                presentationModule,
                useCaseModule, frameworkAndroidModule, frameworkSharedModule
            )
            delayedInit()
        }
    }

    private fun delayedInit() {
        applicationScope.launch {
            setUpRecurringWork()
        }
    }

    private fun setUpRecurringWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }.build()

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(
            7,
            TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }
}