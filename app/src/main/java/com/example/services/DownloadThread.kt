package com.example.services

import java.lang.Thread
import com.example.services.DownloadHandler
import android.util.Log
import com.example.services.DownloadThread
import android.os.Looper

class DownloadThread : Thread() {
    var mHandler: DownloadHandler? = null

    override fun run() {
        Log.e(TAG, "run: Download Thread : " + currentThread().name)
        Looper.prepare()
        mHandler = DownloadHandler()
        Looper.loop()
    }

    companion object {
        private const val TAG = "MyTag"
    }
}