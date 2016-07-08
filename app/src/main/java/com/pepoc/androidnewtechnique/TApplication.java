package com.pepoc.androidnewtechnique;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Environment;

import com.alipay.euler.andfix.patch.PatchManager;
import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.pepoc.androidnewtechnique.log.LogManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by yangchen on 16-1-12.
 */
public class TApplication extends Application implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onCreate() {
        super.onCreate();

        initLogger();
        initAndFix();

//        registerActivityLifecycleCallbacks(this);

        LogManager.i("------------onCreate()----------- Pid = " + android.os.Process.myPid());
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
}
