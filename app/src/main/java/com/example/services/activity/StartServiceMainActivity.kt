package com.example.services.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.services.Playlist
import com.example.services.R
import com.example.services.services.startedService.ResultReceiverStartedService

class StartServiceMainActivity : AppCompatActivity() {
    val MyTag = "MyTag"

    private val MessageKey = "message_key"
    lateinit var mLog: TextView
    lateinit var btnRunCode: Button
    lateinit var btnClearCode: Button
    lateinit var progressBar: ProgressBar
    lateinit var scrollView: ScrollView
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        clickListener()
    }

    private fun clickListener() {
        btnRunCode.setOnClickListener {
            runCode()
        }
        btnClearCode.setOnClickListener {
            clearCode()
        }
    }

    private fun initViews() {
        mLog = findViewById(R.id.tv)
        btnRunCode = findViewById(R.id.btnRunCode)
        btnClearCode = findViewById(R.id.btnClearCode)
        progressBar = findViewById(R.id.progressBar)
        scrollView = findViewById(R.id.scrollView)

        displayProgressBar(false)

        /*handler = Handler(Looper.getMainLooper(), object : Handler.Callback {
            override fun handleMessage(msg: Message): Boolean {
                Log.e(MyTag, "handleMessage: ${msg.obj}")

                log(msg.obj.toString())
                return true
            }
        })*/

        handler = Handler()
    }

    private fun runCode() {
        log("Running Code")
        displayProgressBar(true)

        //Create the instance of result Receiver class for getting the data from the background thread
        //val resultReceiver: ResultReceiver = MyResultReceiver(handler, this)

        for (song in Playlist.songs) {
            /*val intent = Intent(this, MainThreadStartedService::class.java)
            val intent = Intent(this, BackGroundThreadStartedService::class.java)
            val intent = Intent(this, ThreadLooperHandlerStartedService::class.java)
            val intent = Intent(this, AsyncTaskStartedService::class.java)
            val intent = Intent(this, OnStartCommandReturnFlagStartedService::class.java)
            val intent = Intent(this, StopSelfStopServiceInStartedService::class.java)*/

            val intent = Intent(this, ResultReceiverStartedService::class.java)
            intent.putExtra(MessageKey, song)

            //this is for handling the result receiver for sending data to ui
            //intent.putExtra(Intent.EXTRA_RESULT_RECEIVER, resultReceiver)
            startService(intent)
        }
    }

    private fun clearCode() {
        mLog.text = ""
        displayProgressBar(false)
        scrollTextToEnd()

        val intent = Intent(this, ResultReceiverStartedService::class.java)
        stopService(intent)
    }

    fun log(message: String) {
        mLog.append("\n $message")
        scrollTextToEnd()
    }

    fun displayProgressBar(displayProgressBar: Boolean) {
        if (displayProgressBar) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun scrollTextToEnd() {
        scrollView.post(Runnable { scrollView.fullScroll(ScrollView.FOCUS_DOWN) })
    }

    class MyResultReceiver(
        private val handler: Handler?, private val activity: StartServiceMainActivity
    ) : ResultReceiver(handler) {

        val MyTag = "MyTag"

        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            super.onReceiveResult(resultCode, resultData)

            if (resultCode == 100 && resultData != null) {
                Log.e(MyTag, "onReceiveResult: ${Thread.currentThread().name}")

                val songName = resultData.getString("message_key")

                /*activity.runOnUiThread(Runnable {
                    activity.log(songName.toString() + " Downloaded")
                })*/

                handler?.post {
                    activity.log(songName.toString() + " Downloaded")
                }

                /*val message: Message = Message.obtain()
                message.obj = songName.toString() + " Downloaded"
                handler?.handleMessage(message)*/
            }
        }
    }
}