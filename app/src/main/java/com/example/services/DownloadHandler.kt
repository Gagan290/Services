package com.example.services

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.example.services.services.StopSelfStopServiceInStartedService

class DownloadHandler : Handler(Looper.getMainLooper()) {
    companion object {
        private const val TAG = "MyTag"
    }

    var service: StopSelfStopServiceInStartedService? = null

    override fun handleMessage(msg: Message) {
        //Log.e(TAG, "run: staring download on Thread : " + Thread.currentThread().getName());

        downloadSong(msg.obj.toString())

        //completely stop the service
        //service?.stopSelf()

        // this will stop that startId
        //service?.stopSelf(msg.arg1)

        // this will stop that startId and return the boolean
        //val result: Boolean? = service?.stopSelfResult(msg.arg1)
    }

    private fun downloadSong(songName: String) {
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Log.e(TAG, "downloadSong: $songName Downloaded...")
    }

    fun setStopSelfStopService(service: StopSelfStopServiceInStartedService) {
        this.service = service
    }
}