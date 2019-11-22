package com.pepoc.androidnewtechnique.services.foreground;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

/**
 * Created by yangchen on 2017/9/14.
 */

public class ForegroundService extends Service {

    private ClipboardManager clipboardManager;
    private MyClipboardManager myClipboardManager;
    private FloatingView floatingView;
    private NotificationManager notificationManager;
    private Handler handler = new Handler();
    private Notification notification;
    private int num = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        floatingView = new FloatingView(this);

        notification = createForegroundService(num);

        handler.postDelayed(runnable, 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        setClipboardListener();

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        startForeground(1, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            notification = createForegroundService(++num);
            notificationManager.notify(1, notification);
//            startForeground(1, notification);
            handler.postDelayed(this, 1000);
        }
    };

    private Notification createForegroundService(int i) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Intent notificationIntent = new Intent(this, ForegroundServiceDemoActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        builder.setContentIntent(pendingIntent)
                .setContentTitle("Foreground Demo_ " + i)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis());

        return builder.build();
    }

    private void setClipboardListener() {
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

//        int itemCount = clipboardManager.getPrimaryClip().getItemCount();
//        LogManager.i("itemCount === " + itemCount);

        myClipboardManager = new MyClipboardManager();

        clipboardManager.addPrimaryClipChangedListener(myClipboardManager);
    }

    private class MyClipboardManager implements ClipboardManager.OnPrimaryClipChangedListener {

        @Override
        public void onPrimaryClipChanged() {
            ClipData primaryClip = clipboardManager.getPrimaryClip();
            if (clipboardManager.hasPrimaryClip() && primaryClip.getItemCount() > 0) {
                for (int i = 0; i < primaryClip.getItemCount(); i++) {
                    CharSequence addedText = primaryClip.getItemAt(i).getText();
                    if (!TextUtils.isEmpty(addedText)) {
                        LogManager.i("ClipboardData", addedText.toString());
                    }
                }

                showButtonView();

            }
        }
    }

    private void showButtonView() {
        floatingView.show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        clipboardManager.removePrimaryClipChangedListener(myClipboardManager);
    }
}
