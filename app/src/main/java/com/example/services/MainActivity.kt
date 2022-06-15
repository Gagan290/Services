package com.example.services

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.services.services.StopSelfStopServiceInStartedService

class MainActivity : AppCompatActivity() {
    private val MessageKey = "message_key"
    lateinit var mLog: TextView
    lateinit var btnRunCode: Button
    lateinit var btnClearCode: Button
    lateinit var progressBar: ProgressBar
    lateinit var scrollView: ScrollView

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
    }

    private fun runCode() {
        log("Running Code")
        displayProgressBar(true)

        for (song in Playlist.songs) {
            val intent = Intent(this, StopSelfStopServiceInStartedService::class.java)
            intent.putExtra(MessageKey, song)
            startService(intent)
        }
    }

    private fun clearCode() {
        mLog.text = ""
        displayProgressBar(false)
        scrollTextToEnd()

        val intent = Intent(this, StopSelfStopServiceInStartedService::class.java)
        stopService(intent)
    }

    public fun log(message: String) {
        mLog.append("\n $message")
        scrollTextToEnd()
    }

    public fun displayProgressBar(displayProgressBar: Boolean) {
        if (displayProgressBar) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun scrollTextToEnd() {
        scrollView.post(Runnable { scrollView.fullScroll(ScrollView.FOCUS_DOWN) })
    }
}