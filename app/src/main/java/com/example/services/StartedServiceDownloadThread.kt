package com.example.services

import java.lang.Thread
import android.os.Looper

class StartedServiceDownloadThread : Thread() {
    var mHandler: StartedServiceDownloadHandler? = null

    override fun run() {
//        Log.e(TAG, "run: Download Thread : " + currentThread().name)
        Looper.prepare()
        mHandler = StartedServiceDownloadHandler()
        Looper.loop()
    }

    companion object {
        private const val TAG = "MyTag"
    }
}