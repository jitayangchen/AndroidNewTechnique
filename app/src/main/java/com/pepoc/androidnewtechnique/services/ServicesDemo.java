package com.pepoc.androidnewtechnique.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.pepoc.androidnewtechnique.log.LogManager;

import cn.com.findfine.servicedemo.IMyAidlInterface;

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
        new Thread() {
            @Override
            public void run() {
                super.run();
                int count = 0;
                while (true) {
                    LogManager.i("onStartCommand count === " + count++);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
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

    class LocalBinder extends Binder {
        ServicesDemo getService() {
            return ServicesDemo.this;
        }
    }

    class RemoteBinder extends IMyAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getName() throws RemoteException {
            return null;
        }
    }

    public void print() {
        LogManager.i("Test Bind Service");
    }
}
