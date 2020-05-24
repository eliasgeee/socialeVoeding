package com.socialevoeding.bap.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.socialevoeding.bap.data.database.getDatabase

class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params){

    companion object{
        const val WORK_NAME = "RefreshLocalPlaces"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        return Result.success()
    }
}