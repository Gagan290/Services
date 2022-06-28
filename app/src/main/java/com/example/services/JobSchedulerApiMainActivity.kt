package com.example.services

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.services.services.jobScheduler.MyDownloadJobSchedulerService

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class JobSchedulerApiMainActivity : AppCompatActivity() {
    private val TAG = "MyTag"
    lateinit var mLog: TextView
    lateinit var btnRunCode: Button
    lateinit var btnClearCode: Button
    lateinit var progressBar: ProgressBar
    lateinit var scrollView: ScrollView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.job_scheduler_activity_main)

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
        val jobScheduler: JobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

        val componentName = ComponentName(this, MyDownloadJobSchedulerService::class.java)

        val jobInfo = JobInfo.Builder(1001, componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setMinimumLatency(5000)
            //.setPeriodic(15 * 60 * 1000)
            .setPersisted(true)
            .build()

        val result = jobScheduler.schedule(jobInfo)

        if (result == JobScheduler.RESULT_SUCCESS)
            Log.d(TAG, "ScheduleService: Job scheduled")
        else
            Log.d(TAG, "ScheduleService: Job not scheduled")
    }

    private fun clearCode() {
        val jobScheduler: JobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancel(1001)
        Log.d(TAG, "CancelService: job cancelled")
    }
}