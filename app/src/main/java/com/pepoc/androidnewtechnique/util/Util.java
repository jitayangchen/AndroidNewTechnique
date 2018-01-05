package com.pepoc.androidnewtechnique.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * convert sp to its equivalent px
     *
     * 将sp转换为px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
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

    public static int getScreenWidth(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
//        float density = dm.density;
        int width = dm.widthPixels;
//        int height = dm.heightPixels;
        return width;
    }

    public static int getScreenHeight(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
//        float density = dm.density;
//        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return height + getNavigationBarHeight(context);
    }

    /**
     * 获取navigation bar的高度
     *
     * @param
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    public static int getScreenDensity(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.densityDpi;
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
        return (int) density;
    }





    //系统保存截图的路径
    public static final String SCREENCAPTURE_PATH = "ScreenCapture" + File.separator + "Screenshots" + File.separator;
//  public static final String SCREENCAPTURE_PATH = "ZAKER" + File.separator + "Screenshots" + File.separator;

    public static final String SCREENSHOT_NAME = "Screenshot";

    public static String getAppPath(Context context) {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {


            return Environment.getExternalStorageDirectory().toString();

        } else {

            return context.getFilesDir().toString();
        }

    }


    public static String getScreenShots(Context context) {

        StringBuffer stringBuffer = new StringBuffer(getAppPath(context));
        stringBuffer.append(File.separator);

        stringBuffer.append(SCREENCAPTURE_PATH);

        File file = new File(stringBuffer.toString());

        if (!file.exists()) {
            file.mkdirs();
        }

        return stringBuffer.toString();

    }

    public static String getScreenShotsName(Context context) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");

        String date = simpleDateFormat.format(new Date());

        StringBuffer stringBuffer = new StringBuffer(getScreenShots(context));
        stringBuffer.append(SCREENSHOT_NAME);
        stringBuffer.append("_");
        stringBuffer.append(date);
        stringBuffer.append(".png");

        return stringBuffer.toString();

    }
}
