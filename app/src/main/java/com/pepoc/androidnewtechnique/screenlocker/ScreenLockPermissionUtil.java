package com.pepoc.androidnewtechnique.screenlocker;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScreenLockPermissionUtil {


    /**
     * 是否有悬浮窗的权限
     */
    public static boolean hasWindowPermission(Context context){
        if(Build.VERSION.SDK_INT >= 24)
            return Settings.canDrawOverlays(context);
        return true;
    }


    /**
     * 跳转系统获取悬浮窗权限的页面
     */
    public static void openSystemWindowPermissionSetting(Context context){
        try{
            if(Build.VERSION.SDK_INT >= 23){
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }catch (Exception e){
//            LogManager.printStackTrace(e);
        }
    }


    /**
     * 获取通知栏消息权限是否获得
     */
    public static boolean isNotificationListenerEnabled(Context context) {
        String flat = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        String packageName = context.getPackageName();
        if (!TextUtils.isEmpty(flat) && !TextUtils.isEmpty(packageName)) {
            final String[] names = flat.split(":");
            for (String name : names) {
                ComponentName componentName = ComponentName.unflattenFromString(name);
                if (componentName != null && packageName.equals(componentName.getPackageName()))
                    return true;
            }
        }
        return false;
    }

    /**
     * 打开系统获取通知栏权限页面
     */
    public static void openNotificationListenerSetting(Context context) {
        Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }


    /**
     * 是小米系统
     */
    public static boolean isMIUI(){
        String property = getSystemProperty("ro.miui.ui.version.name");
        return !TextUtils.isEmpty(property);
    }
    private static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        return line;
    }

}
