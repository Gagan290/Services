package com.example.services.services.jobScheduler

import android.annotation.SuppressLint
import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

@SuppressLint("SpecifyJobSchedulerIdRange")
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyDownloadJobSchedulerService : JobService() {
    private val TAG = "MyTag"
    private var isJobCancelled: Boolean = false
    private var mSuccess: Boolean = false

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStartJob: called")
        Log.d(TAG, "onStartJob: Thread name: ${Thread.currentThread().name}")

        Thread {
            var i = 0
            Log.d(TAG, "run: Download Started")
            while (i < 10) {
                if (isJobCancelled) {
                    return@Thread
                }

                Log.d(TAG, "run: Download Progress: ${i + 1}")
                Thread.sleep(1000)
                i++
            }
            Log.d(TAG, "run: Download Completed")
            mSuccess = true
            jobFinished(params, mSuccess)

        }.start()

        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        isJobCancelled = true
        return false
    }
}