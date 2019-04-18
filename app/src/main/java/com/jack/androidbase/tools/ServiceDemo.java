package com.jack.androidbase.tools;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceDemo extends Service {
    private final String TAG = "ServiceDemo";


    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind被调用");
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate被调用");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy被调用");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand被调用");
        return super.onStartCommand(intent, flags, startId);
    }
}