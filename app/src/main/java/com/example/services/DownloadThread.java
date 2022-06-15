package com.example.services;

import android.os.Looper;
import android.util.Log;

public class DownloadThread extends Thread {
    private static final String TAG = "MyTag";

    public DownloadHandler mHandler;

    @Override
    public void run() {
        Log.e(TAG, "run: Download Thread : " + Thread.currentThread().getName());

        Looper.prepare();
        mHandler = new DownloadHandler();
        Looper.loop();
    }
}