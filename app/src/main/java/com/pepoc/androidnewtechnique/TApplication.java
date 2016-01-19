package com.pepoc.androidnewtechnique;

import android.app.Application;

import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by yangchen on 16-1-12.
 */
public class TApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        Logger.init("NT")                 // default PRETTYLOGGER or use just init()
            .methodCount(5)                 // default 2
//            .hideThreadInfo()               // default shown
            .logLevel(LogLevel.FULL)        // default LogLevel.FULL
            .methodOffset(2)                // default 0
            .logTool(new AndroidLogTool()); // custom log tool, optional
    }
}
