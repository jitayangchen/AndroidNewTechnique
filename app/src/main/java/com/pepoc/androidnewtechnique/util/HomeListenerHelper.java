package com.pepoc.androidnewtechnique.util;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by danfeng on 16/12/1.
 */

public class HomeListenerHelper {
    private WeakReference<HomeCallback> mHomeCallback = null;
    public static String KEY_HOME = "homekey";
    public static String KEY_HOME_RECENT_APPS = "recentapps";
    private static HomeListenerHelper mInstance;
    private static String mLastAction = null;

    private HomeListenerHelper() {
    }

    public static HomeListenerHelper getInstance() {
        if (mInstance == null) {
            synchronized (HomeListenerHelper.class) {
                if (mInstance == null) {
                    mInstance = new HomeListenerHelper();
                }
            }
        }
        return mInstance;
    }

    public void onHomeClick(String current) {
//        Log.i("YYY", " current ======= " + current);
//        Log.i("YYY", " mLastAction ===== " + mLastAction);
//        if(!KEY_HOME_RECENT_APPS.equals(mLastAction) && KEY_HOME.equals(current)) {
//            Log.i("YYY", "-----------------onHomeClick----------------- current = " + current);
//            if (mHomeCallback != null && mHomeCallback.get() != null) {
//                mHomeCallback.get().onHomeClick();
//                Toast.makeText(SpaceApplication.getContext(), "onHome", Toast.LENGTH_SHORT).show();
//            }
//        }else if (KEY_HOME_RECENT_APPS.equals(mLastAction) && KEY_HOME.equals(current)) {
//            Log.i("YYY", "=============onHomeClick=============");
//        }
//
//        mLastAction = current;

        if (KEY_HOME.equals(current)) {
            if (mHomeCallback != null && mHomeCallback.get() != null) {
                mHomeCallback.get().onHomeClick();
            }
            Log.i("YYY", "=============onHomeClick=============");
        }
    }

    public void registCallback(HomeCallback mHomeCallback) {
        this.mHomeCallback = new WeakReference<>(mHomeCallback);
        Log.i("YYY", "=============registCallback=============");
    }

    public void unregistCallback(HomeCallback mHomeCallback) {
        this.mHomeCallback.clear();
        Log.i("YYY", "=============un    registCallback=============");

    }

    public interface HomeCallback {
        void onHomeClick();
    }
}
