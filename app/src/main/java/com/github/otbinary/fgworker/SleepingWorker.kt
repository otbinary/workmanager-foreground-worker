package com.github.otbinary.fgworker

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters

class SleepingWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    companion object {
        private val TAG = SleepingWorker::class.simpleName
        private const val CHANNEL_ID = "my-channel"
        private const val NOTIFICATION_ID = 1
    }

    override fun doWork(): Result {
        setForegroundAsync(ForegroundInfo(NOTIFICATION_ID, createNotification()))

        Log.i(TAG, "Sleeping for five seconds...")
        Thread.sleep(5000)
        Log.i(TAG, "...done sleeping")

        return Result.success()
    }

    private fun createNotification(): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(applicationContext, NotificationManager::class.java)!!
            notificationManager.createNotificationChannel(NotificationChannel(CHANNEL_ID, CHANNEL_ID, IMPORTANCE_DEFAULT))
        }

        return NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("Sleeping")
            .setContentText("Worker is sleeping for five seconds.")
            .setSmallIcon(android.R.drawable.sym_def_app_icon)
            .build()
    }
}