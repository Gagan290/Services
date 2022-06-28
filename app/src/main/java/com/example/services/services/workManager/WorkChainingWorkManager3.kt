package com.example.services.services.workManager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkChainingWorkManager3(private val context: Context, params: WorkerParameters) :
    Worker(context, params) {

    private val TAG: String? = "MyTag"

    override fun doWork(): Result {
//        Log.d(TAG, "doWork: WorkManager WorkChaining3")
        getRandomNumber()
        return Result.success()
    }

    private fun getRandomNumber() {
        var i = 0
//        Log.d(TAG, "run: WorkManager Started")
        while (i < 5) {
            Log.d(TAG, "run: WorkChaining3 Progress: ${i + 1}")
            Thread.sleep(1000)
            i++
        }
//        Log.d(TAG, "run: WorkManager Completed")
    }

    override fun onStopped() {
        super.onStopped()

//        Log.d(TAG, "onStopped: WorkManager WorkChaining3")
    }
}