package com.example.services.services.workManager

import android.app.Notification
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.services.activity.WorkManagerMainActivity
import com.example.services.notifications.MyAppsNotificationManager

class MyLongRunningWorkManager(private val context: Context, params: WorkerParameters) :
    Worker(context, params) {

    private val TAG: String? = "MyTag"

    var myAppsNotificationManager: MyAppsNotificationManager? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun doWork(): Result {
        Log.d(TAG, "doWork: WorkManager Thread Name: ${Thread.currentThread().name}")

        myAppsNotificationManager = MyAppsNotificationManager.getInstance(context)

        //Display notification
        val foregroundInfo = createForegroundInfo("Long Running WorkManager")
        if (foregroundInfo != null) {
            setForegroundAsync(foregroundInfo)
        }

        getRandomNumber()

        return Result.success()
    }

    private fun getRandomNumber() {
        var i = 0
        Log.d(TAG, "run: WorkManager Started")
        while (i < 10) {
            Log.d(TAG, "run: WorkManager Progress: ${i + 1}")
            Thread.sleep(1000)
            i++
        }
        Log.d(TAG, "run: WorkManager Completed")
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun createForegroundInfo(message: String?): ForegroundInfo? {
        val notification: Notification? =
            myAppsNotificationManager?.getNotification(
                WorkManagerMainActivity::class.java,
                message,
                1,
                false,
                100
            )

        return notification?.let { ForegroundInfo(100, it) }
    }

    override fun onStopped() {
        super.onStopped()

        Log.d(TAG, "onStopped: WorkManager")
    }
}