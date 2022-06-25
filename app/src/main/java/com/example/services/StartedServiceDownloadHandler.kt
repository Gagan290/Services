package com.example.services

import android.app.Service
import android.os.*
import android.util.Log

class StartedServiceDownloadHandler : Handler(Looper.getMainLooper()) {
    companion object {
        private const val TAG = "MyTag"
    }

    var service: Service? = null
    var resultReceiver: ResultReceiver? = null

    override fun handleMessage(msg: Message) {
        //Log.e(TAG, "run: staring download on Thread : " + Thread.currentThread().getName());

        downloadSong(msg.obj.toString())

        if (service != null) {
            //completely stop the service
            //service?.stopSelf()

            // this will stop that startId
            service?.stopSelf(msg.arg1)

            // this will stop that startId and return the boolean
            //val result: Boolean? = service?.stopSelfResult(msg.arg1)
        }

        //set the data back to ui using Result Receiver
        /*val bundle = Bundle()
        bundle.putString("message_key", msg.obj.toString())
        resultReceiver?.send(100, bundle)*/
    }

    private fun downloadSong(songName: String) {
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Log.e(TAG, "downloadSong: $songName Downloaded...")
    }

    fun setStopSelfStopService(service: Service) {
        this.service = service
    }

    /*fun setMyResultReceiver(receiver: ResultReceiver) {
        resultReceiver = receiver
    }*/
}