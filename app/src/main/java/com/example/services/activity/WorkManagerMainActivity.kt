package com.example.services.activity

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.services.R
import com.example.services.services.workManager.MyLongRunningWorkManager

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class WorkManagerMainActivity : AppCompatActivity() {
    private val TAG = "MyTag"
    lateinit var mLog: TextView
    lateinit var btnRunCode: Button
    lateinit var btnClearCode: Button
    lateinit var progressBar: ProgressBar
    lateinit var scrollView: ScrollView

    lateinit var workManager: WorkManager
    lateinit var workRequest: WorkRequest

    lateinit var workRequest1: OneTimeWorkRequest
    lateinit var workRequest2: OneTimeWorkRequest
    lateinit var workRequest3: OneTimeWorkRequest
    lateinit var workRequest4: OneTimeWorkRequest


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.work_manager_activity_main)

        initViews()
        clickListener()

        initialData()
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

    private fun initialData() {
        val contraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        //Normal calling of work manager
        /*workRequest =
            PeriodicWorkRequest.Builder(MyWorkManager::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(contraints)
                .build()*/


        //****** Work Chaining Work Manager Start ******

        //Work Chaining Work Manager
        /*workRequest1 = OneTimeWorkRequest.Builder(WorkChainingWorkManager1::class.java)
            .setConstraints(contraints)
            .addTag("Worker1")
            .build()

        workRequest2 = OneTimeWorkRequest.Builder(WorkChainingWorkManager2::class.java)
            .setConstraints(contraints)
            .addTag("Worker2")
            .build()

        workRequest3 = OneTimeWorkRequest.Builder(WorkChainingWorkManager3::class.java)
            .setConstraints(contraints)
            .addTag("Worker3")
            .build()

        workRequest4 = OneTimeWorkRequest.Builder(WorkChainingWorkManager4::class.java)
            .setConstraints(contraints)
            .addTag("Worker4")
            .build()*/

        //****** Work Chaining Work Manager End ******


        //**** Long Running Work Manager *****
        workRequest = OneTimeWorkRequest.from(MyLongRunningWorkManager::class.java)

        workManager = WorkManager.getInstance(this)
    }

    private fun runCode() {
        //Normal calling of work manager
        // workManager.enqueue(workRequest)


        //****** Work Chaining Work Manager Start ******

        //Work chaining in sequential order
        /* workManager.beginWith(workRequest1)
             .then(workRequest2).then(workRequest3).then(workRequest4)
             .enqueue()*/

        //work chaining in work1 then work2 & work3 in parallel and then work4
        /*workManager.beginWith(workRequest1)
            .then(mutableListOf(workRequest2, workRequest3))
            .then(workRequest4).enqueue()*/

        /*workManager.beginWith(workRequest1).then(workRequest2)
            .then(mutableListOf(workRequest3, workRequest4))
            .enqueue()*/

        /*workManager.beginWith(mutableListOf(workRequest1, workRequest2, workRequest3))
            .then(workRequest4).enqueue()*/

        //Work Chaining all in parallel
        /*workManager.beginWith(mutableListOf(workRequest1, workRequest2, workRequest3, workRequest4))
            .enqueue()*/

        //****** Work Chaining Work Manager End ******


        //**** Long Running Work Manager ****
        workManager.enqueue(workRequest)

    }

    private fun clearCode() {
        workManager.cancelWorkById(workRequest.id)

        //****** Work Chaining Work Manager Start ******

//        workManager.cancelAllWorkByTag("Worker3")

        //****** Work Chaining Work Manager End ******
    }
}