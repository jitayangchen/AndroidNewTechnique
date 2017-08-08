package com.pepoc.androidnewtechnique.screenlocker;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.pepoc.androidnewtechnique.R;

public class ScreenLocker {


    private static Context context;
    private static WindowManager manager;
    private static View view;
    private static WindowManager.LayoutParams mLayoutParams;
    public static void init(Context context){
        ScreenLocker.context = context.getApplicationContext();
    }



    /**
     *
     * */
    public static void show(Context context){
        init(context);
        if(!hasPermission()){
            return ;
        }
        if(manager==null)
            manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.lay_screen_locker, null);
        if(view.getParent() != null)
            return ;
       if(mLayoutParams == null){
           mLayoutParams = new WindowManager.LayoutParams(-1, -1);
           mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
           mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
           mLayoutParams.windowAnimations = 0;
           mLayoutParams.x = mLayoutParams.y = 0;
           mLayoutParams.format = PixelFormat.RGBA_8888;
           mLayoutParams.flags = WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;
           mLayoutParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
           mLayoutParams.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
           mLayoutParams.flags |= WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
           if(Build.VERSION.SDK_INT >= 19){
               mLayoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
               mLayoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
           }
       }
        manager.addView(view, mLayoutParams);
        initEvent();
    }
    /**
     *
     */
    public static void dismiss(){
        if(manager != null && view != null && view.getParent()!=null)
            manager.removeView(view);
    }
    /**
     *
     * */
    public static boolean hasPermission(){
        if(Build.VERSION.SDK_INT >= 24)
            return Settings.canDrawOverlays(context);
        return true;
    }
    /**
     *
     *  */
    public static void goGetPermission(){
        if(Build.VERSION.SDK_INT >= 23){
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }



    private static float downX;

    private static void initEvent(){
        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });
       view.post(new Runnable() {
           public void run() {
               view.findViewById(R.id.layout).setOnTouchListener(new View.OnTouchListener() {
                   public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN:
                                downX = event.getRawX();
                                break;
                            case MotionEvent.ACTION_MOVE:
                                float dis = event.getRawX() - downX;
                                if(dis>0)
                                    v.setTranslationX(dis);
                                break;
                            case MotionEvent.ACTION_UP:
                                v.setTranslationX(0);
                                break;
                        }
                       return true;
                   }
               });
           }
       });
    }

}
