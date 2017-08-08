package com.pepoc.androidnewtechnique.screenlocker;


import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.TApplication;

public class ChargingScreenLock extends ScreenLock{

    private static View mView;


    public static void show(){
        if(mView != null)
            return ;
        mView = LayoutInflater.from(TApplication.getContext()).inflate(R.layout.screenlock_lay_charging, null, false);
        showWindow(mView);
        initViewEvent();
    }

    public static void dismiss(){
        if(mView==null)
            return ;
        dismissWindow(mView);
        mView = null;
    }



    private static float downX;

    private static void initViewEvent(){
        mView.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });
        final View view = mView.findViewById(R.id.layout_main);
        view.post(new Runnable() {
            public void run() {
                view.setOnTouchListener(new View.OnTouchListener() {
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
