package com.example.services.services.startedService

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Message
import android.os.ResultReceiver
import android.util.Log
import com.example.services.StartedServiceDownloadThread

class ResultReceiverStartedService : Service() {
    val MessageKey = "message_key"
    val MyTag = "MyTag"
    val MyLogTag = "MyLogTag"
    lateinit var thread: StartedServiceDownloadThread


    override fun onCreate() {
        super.onCreate()
        Log.e(MyTag, "Service : onCreate()")

        thread = StartedServiceDownloadThread()
        thread.start()

        while (thread.mHandler == null) {
        }

        //set the service instance
        thread.mHandler?.setStopSelfStopService(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        Log.e("MyTag", "onStartCommand Intent : ${intent?.getStringExtra(MessageKey)}")

        val songName = intent?.getStringExtra(MessageKey)

        //this is for handling the result receiver for sending data to ui
        /*val resultReceiver: ResultReceiver =
            intent?.getParcelableExtra(Intent.EXTRA_RESULT_RECEIVER)!!*/

        //set the result receiver value
        //thread.mHandler?.setMyResultReceiver(resultReceiver)


        //Send the data to handler using Message object
        val message: Message = Message.obtain()
        message.obj = songName
        message.arg1 = startId

        thread.mHandler?.handleMessage(message)

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