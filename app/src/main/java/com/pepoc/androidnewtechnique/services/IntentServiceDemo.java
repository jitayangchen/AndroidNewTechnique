package com.pepoc.androidnewtechnique.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import com.pepoc.androidnewtechnique.log.LogManager;

/**
 * Created by Yangchen on 2016/5/10.
 */
public class IntentServiceDemo extends IntentService {

    public IntentServiceDemo() {
        this("IntentServiceDemo");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentServiceDemo(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        LogManager.i("----------onHandleIntent---------");
    }

    @Override
    public void onCreate() {
        LogManager.i("----------onCreate---------");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        LogManager.i("----------onStart---------");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogManager.i("----------onStartCommand---------");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogManager.i("----------onDestroy---------");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogManager.i("----------onBind---------");
        return super.onBind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogManager.i("----------onUnbind---------");
        return super.onUnbind(intent);
    }

}
