package com.pepoc.androidnewtechnique.broadcastreceiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.services.notification.NotificationListenerActivity;

/**
 * @author Arlenyang
 * @Description:
 * @date 2017/3/1 16:21
 * @copyright TCL-MIG
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    private Handler handler = new Handler();

    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, "received", Toast.LENGTH_SHORT).show();

        sendNotification(context);

//        final PowerManager.WakeLock mWakelock;
//        PowerManager pm = (PowerManager)context.getSystemService(POWER_SERVICE);// init powerManager
//        mWakelock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP|
//                PowerManager.SCREEN_DIM_WAKE_LOCK,"bright");
//
//        mWakelock.acquire();
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mWakelock.release();
//            }
//        }, 10 * 1000);
    }

    private void sendNotification(Context context) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_android_black_24dp)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
        Intent resultIntent = new Intent(context, NotificationListenerActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(NotificationListenerActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }
}
