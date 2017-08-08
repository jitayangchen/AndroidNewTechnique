package com.pepoc.androidnewtechnique.screenlocker;


import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.pepoc.androidnewtechnique.TApplication;

public abstract class ScreenLock {

    protected static final WindowManager.LayoutParams mLayoutParams;
    static{
        mLayoutParams = new WindowManager.LayoutParams(-1, -1);
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.windowAnimations = 0;
        mLayoutParams.x = mLayoutParams.y = 0;
        mLayoutParams.format = PixelFormat.RGBA_8888;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;
        mLayoutParams.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        if(Build.VERSION.SDK_INT >= 19){
            mLayoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            mLayoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        }
    }



    protected static void showWindow(View view){
        if(view.getParent() != null || !ScreenLockPermissionUtil.hasWindowPermission(TApplication.getContext()))
            return ;
        WindowManager manager = (WindowManager) TApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        manager.addView(view, mLayoutParams);
    }

    protected static void dismissWindow(View view){
        if(view.getParent() == null)
            return ;
        WindowManager manager = (WindowManager) TApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        manager.removeView(view);
    }

}
