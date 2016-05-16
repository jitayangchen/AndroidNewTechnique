package com.pepoc.androidnewtechnique;

import android.app.Application;
import android.os.Environment;

import com.alipay.euler.andfix.patch.PatchManager;
import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Created by yangchen on 16-1-12.
 */
public class TApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initLogger();
        initAndFix();
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
}
