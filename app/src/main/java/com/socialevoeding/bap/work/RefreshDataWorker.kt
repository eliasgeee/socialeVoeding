package com.socialevoeding.bap.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshLocalPlaces"
    }

    override suspend fun doWork(): Result {
        /*val database =
            getDatabase(
                applicationContext
            )*/
        return Result.success()
    }
}