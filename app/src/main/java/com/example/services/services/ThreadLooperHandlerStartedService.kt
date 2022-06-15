package com.example.services.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Message
import android.util.Log
import com.example.services.DownloadThread

class ThreadLooperHandlerStartedService : Service() {
    val MessageKey = "message_key"
    val MyTag = "MyTag"
    val MyLogTag = "MyLogTag"
    lateinit var thread: DownloadThread

    override fun onCreate() {
        super.onCreate()
        Log.e(MyTag, "Service : onCreate()")

        thread = DownloadThread()
        thread.start()

        while (thread.mHandler == null) {
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val songName = intent?.getStringExtra(MessageKey)
        /*Log.e(
            MyTag,
            "onStartCommand CurrentThread : ${Thread.currentThread().name}\n Song Name : $songName"
        )*/

        val message: Message = Message.obtain()
        message.obj = songName
        thread.mHandler.handleMessage(message)


        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.e(MyLogTag, "Service : onBind()")
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(MyLogTag, "Service : onDestroy()")
    }
}