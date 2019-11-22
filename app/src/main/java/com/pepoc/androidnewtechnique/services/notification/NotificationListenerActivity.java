package com.pepoc.androidnewtechnique.services.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.screenlocker.ScreenLockPermissionUtil;
import com.pepoc.androidnewtechnique.util.Util;

public class NotificationListenerActivity extends AppCompatActivity {

    private TextView tvContent;
    private TextView tvContentWindowSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_listener);

        init();

//        toggleNotificationListenerService();
    }

    private void init() {
        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(NotificationListenerActivity.this, MyNotificationListenerService.class);
//                startService(intent);

                sendNotification();
            }
        });

        tvContent = (TextView) findViewById(R.id.tv_content);

        findViewById(R.id.btn_jump_window_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScreenLockPermissionUtil.openSystemWindowPermissionSetting(NotificationListenerActivity.this);
            }
        });

        tvContentWindowSet = (TextView) findViewById(R.id.tv_content_window_set);
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean notificationListenerEnabled = Util.isNotificationListenerEnabled(this);
        if (notificationListenerEnabled) {
            tvContent.setText("啦啦啦 获取到了通知权限");
        } else {
            tvContent.setText("没有通知权限");
        }

        if (ScreenLockPermissionUtil.hasWindowPermission(this)) {
            tvContentWindowSet.setText("啦啦啦 获取到了Window权限");
        } else {
            tvContentWindowSet.setText("没有Window权限");
        }
    }

    private void sendNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_android_black_24dp)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, NotificationListenerActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(NotificationListenerActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());
    }

    private void toggleNotificationListenerService() {
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(this, MyNotificationListenerService.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        pm.setComponentEnabledSetting(new ComponentName(this, MyNotificationListenerService.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

    }
}
