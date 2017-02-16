package com.pepoc.androidnewtechnique.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.pepoc.androidnewtechnique.log.LogManager;

/**
 * Created by Yangchen on 2016/5/10.
 */
public class ServicesDemo extends Service {

    private final IBinder mBinder = new LocalBinder();

    @Override
    public void onCreate() {
        LogManager.i("----------onCreate---------");

        LogManager.i("------------ServicesDemo----------- Pid = " + android.os.Process.myPid());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogManager.i("----------onStartCommand---------");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogManager.i("----------onBind---------");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogManager.i("----------onUnbind---------");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        LogManager.i("----------onDestroy---------");
        super.onDestroy();
    }

    public class LocalBinder extends Binder {
        public ServicesDemo getService() {
            return ServicesDemo.this;
        }
    }

    public void print() {
        LogManager.i("Test Bind Service");
    }
}
