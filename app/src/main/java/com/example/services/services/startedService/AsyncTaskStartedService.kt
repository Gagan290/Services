package com.example.services.services.startedService

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import android.util.Log

class AsyncTaskStartedService : Service() {
    val MessageKey = "message_key"
    val MyTag = "MyTag"
    val MyLogTag = "MyLogTag"

    override fun onCreate() {
        super.onCreate()
        Log.e(MyTag, "Service : onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(MyTag, "Service : onStartCommand()")
        val songName = intent?.getStringExtra(MessageKey)

        val asyncTask = MyDownloadTask()
        asyncTask.execute(songName)

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


    class MyDownloadTask : AsyncTask<String, String, String>() {

        /*override fun onPreExecute() {
            super.onPreExecute()
        }*/

        private fun downLoadSong(songName: String?) {
            try {
                Thread.sleep(4000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        override fun doInBackground(vararg songsArray: String?): String {
            for (song in songsArray) {
                downLoadSong(song)
                publishProgress(song)
            }

            return "All songs have been downloaded"
        }

        override fun onProgressUpdate(vararg values: String?) {
            Log.e("MyTag", "onProgressUpdate: Song Downloaded : ${values[0]}")
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.e("MyTag", "onPostExecute: Result is: $result")
        }
    }
}