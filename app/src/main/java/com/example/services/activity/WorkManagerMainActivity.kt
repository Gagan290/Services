package com.example.services.activity

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.services.R
import com.example.services.services.workManager.MyWorkManager
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class WorkManagerMainActivity : AppCompatActivity() {
    private val TAG = "MyTag"
    lateinit var mLog: TextView
    lateinit var btnRunCode: Button
    lateinit var btnClearCode: Button
    lateinit var progressBar: ProgressBar
    lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.work_manager_activity_main)

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
    }

    private fun runCode() {
        val contraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest =
            PeriodicWorkRequest.Builder(MyWorkManager::class.java, 5, TimeUnit.MINUTES)
                .setConstraints(contraints)
                .build()

        WorkManager.getInstance(this)
            .enqueue(workRequest)
    }

    private fun clearCode() {

    }
}