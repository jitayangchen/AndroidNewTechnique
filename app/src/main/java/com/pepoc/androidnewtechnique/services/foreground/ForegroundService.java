package com.pepoc.androidnewtechnique.services.foreground;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
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

    @Override
    public void onCreate() {
        super.onCreate();

        floatingView = new FloatingView(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createForegroundService();
        setClipboardListener();
        return super.onStartCommand(intent, flags, startId);
    }

    private void createForegroundService() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Intent notificationIntent = new Intent(this, ForegroundServiceDemoActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        builder.setContentIntent(pendingIntent)
                .setContentTitle("Foreground Demo")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis());

        Notification notification = builder.build();
        startForeground(1, notification);
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
