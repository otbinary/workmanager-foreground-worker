package com.github.otbinary.fgworker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class StartSleepingWorkerBroadcastReceiver : BroadcastReceiver() {

    companion object {
        private val TAG = StartSleepingWorkerBroadcastReceiver::class.simpleName
    }

    override fun onReceive(context: Context?, intent: Intent?) {

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<SleepingWorker>()
            .setConstraints(constraints)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .build()

        Log.i(TAG, "Scheduling WorkManager request ${workRequest.id} for SleepingWorker")
        WorkManager.getInstance(context!!).enqueue(workRequest)
    }
}
