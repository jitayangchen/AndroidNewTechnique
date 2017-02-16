package com.pepoc.androidnewtechnique.util;

import android.content.Context;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/30 16:06
 * @copyright TCL-MIG
 */
public class Util {

    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
