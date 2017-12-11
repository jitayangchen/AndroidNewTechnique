package com.pepoc.androidnewtechnique;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;

import com.alipay.euler.andfix.patch.PatchManager;
import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.pepoc.androidnewtechnique.log.LogManager;
import com.pepoc.androidnewtechnique.services.notification.MyNotificationListenerService;
import com.pepoc.androidnewtechnique.util.HomeListenerHelper;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangchen on 16-1-12.
 */
public class TApplication extends Application implements Application.ActivityLifecycleCallbacks {

    public static String CONFIG = "default";
    private static Context context;
    public static List<Context> contextList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initLogger();
        initAndFix();

//        registerActivityLifecycleCallbacks(this);

        LogManager.i("------------onCreate()----------- Pid = " + android.os.Process.myPid());

        registerHomeReceiver();


        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

//        toggleNotificationListenerService();

//        Looper.getMainLooper().setMessageLogging(new LogPrinter(Log.INFO, "MainThread") {
//            @Override
//            public void println(String x) {
//                super.println(x);
//            }
//        });
    }

    public static Context getContext() {
        return context;
    }

    private void initLogger() {
        Logger.init("NT")                 // default PRETTYLOGGER or use just init()
                .methodCount(5)                 // default 2
//            .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2)                // default 0
                .logTool(new AndroidLogTool()); // custom log tool, optional
    }

    private void initAndFix() {
        PatchManager patchManager = new PatchManager(this);
        patchManager.init("1.0");
        patchManager.loadPatch();

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "out.apatch";
        try {
            patchManager.addPatch(path);
            Logger.i("===============patchManager.addPatch(path);===============");
        } catch (IOException e) {
            Logger.e(e, "===============initAndFix===============");
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
//        unregisterActivityLifecycleCallbacks(this);
//        LogManager.i("------------------onTerminate--------------------");
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogManager.i("------------------onActivityCreated--------------------" + activity.getClass().getName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogManager.i("------------------onActivityStarted--------------------");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        LogManager.i("------------------onActivityResumed--------------------");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogManager.i("------------------onActivityPaused--------------------");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogManager.i("------------------onActivityStopped--------------------");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        LogManager.i("------------------onActivitySaveInstanceState--------------------");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogManager.i("------------------onActivityDestroyed--------------------");
    }

    private void registerHomeReceiver() {
        BroadcastReceiver mObserver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(action)){
                    String reason = intent.getStringExtra("reason");
                    if(HomeListenerHelper.KEY_HOME.equals(reason)) {
                        //监听home键
                        HomeListenerHelper.getInstance().onHomeClick(HomeListenerHelper.KEY_HOME);
                    } else if (HomeListenerHelper.KEY_HOME_RECENT_APPS.equals(reason)) {
                        //历史记录
                        HomeListenerHelper.getInstance().onHomeClick(HomeListenerHelper.KEY_HOME_RECENT_APPS);
                    }
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(mObserver, filter);
    }

    private void toggleNotificationListenerService() {
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(this, MyNotificationListenerService.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        pm.setComponentEnabledSetting(new ComponentName(this, MyNotificationListenerService.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

    }
}
