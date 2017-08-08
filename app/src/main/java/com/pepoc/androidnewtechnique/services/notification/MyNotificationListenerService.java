package com.pepoc.androidnewtechnique.services.notification;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.widget.Toast;

import com.pepoc.androidnewtechnique.log.LogManager;

/**
 * @author Arlenyang
 * @Description:
 * @date 2017/2/23 14:02
 * @copyright TCL-MIG
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class MyNotificationListenerService extends NotificationListenerService {

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Toast.makeText(getApplicationContext(), "Success hahaha", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        LogManager.i("---------------onCreate--------------");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogManager.i("---------------onStartCommand--------------");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        handler.sendMessage(handler.obtainMessage(0));
        LogManager.i("onNotificationPosted === " + sbn.toString());
        super.onNotificationPosted(sbn);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        LogManager.i("onNotificationRemoved === " + sbn.toString());
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogManager.i("---------------onBind--------------");
        return super.onBind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogManager.i("---------------onUnbind--------------");
        return super.onUnbind(intent);
    }
}
