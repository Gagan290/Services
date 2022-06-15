package com.example.services;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DownloadHandler extends Handler {
    private static final String TAG = "MyTag";

    @Override
    public void handleMessage(Message msg) {
        //Log.e(TAG, "run: staring download on Thread : " + Thread.currentThread().getName());
        downloadSong(msg.obj.toString());
    }

    private void downloadSong(final String songName) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "downloadSong: " + songName + " Downloaded...");
    }
}