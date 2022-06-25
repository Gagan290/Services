package com.example.services.services.startedService

import android.app.Service
import android.content.Intent
import android.content.res.Configuration
import android.os.IBinder
import android.util.Log

class MainThreadStartedService : Service() {
    val MessageKey = "message_key"
    val MyTag = "MyTag"
    val MyLogTag = "MyLogTag"


    override fun onCreate() {
        super.onCreate()
        Log.e(MyLogTag, "Service : onCreate()")
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.e(MyLogTag, "Service : onBind()")
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(MyLogTag, "Service : onDestroy()")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.e(MyLogTag, "Service : onLowMemory()")
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.e(MyLogTag, "Service : onRebind()")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
        Log.e(MyLogTag, "Service : onUnbind()")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e(MyLogTag, "Service : onConfigurationChanged()")
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Log.e(MyLogTag, "Service : onTaskRemoved()")
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Log.e(MyLogTag, "Service : onTrimMemory()")
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.e(MyLogTag, "Service : onStart()")
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(MyLogTag, "Service : onStartCommand()")
        Log.e(MyTag, "CurrentThread : ${Thread.currentThread().name}")

        val songName = intent?.getStringExtra(MessageKey)
        downLoadSong(songName)
        return Service.START_REDELIVER_INTENT
    }

    private fun downLoadSong(songName: String?) {
        Log.e(MyTag, "run: starting download")
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Log.e(MyTag, "downloadSong : ${songName} Downloaded....")
    }
}