/*
 * Copyright (c) Ergon Informatik AG, Switzerland
 */
package com.github.otbinary.fgworker

import android.app.Application
import androidx.work.Configuration

class SleepingForegroundWorkerApplication : Application(), Configuration.Provider {
    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()
}
