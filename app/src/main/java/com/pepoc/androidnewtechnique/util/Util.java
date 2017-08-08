package com.pepoc.androidnewtechnique.util;

import android.content.ComponentName;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

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

    /**
     * 检测是否开启了通知栏监听权限
     *
     * @param context
     * @return
     */
    public static boolean isNotificationListenerEnabled(Context context) {
        boolean enable = false;
        String flat = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        String packageName = context.getPackageName();

        if (!TextUtils.isEmpty(flat) && !TextUtils.isEmpty(packageName)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                ComponentName componentName = ComponentName.unflattenFromString(names[i]);
                if (componentName != null && packageName.equals(componentName.getPackageName())) {
                    enable = true;
                    break;
                }
            }
        }

        return enable;
    }
}
