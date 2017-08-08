package com.pepoc.androidnewtechnique.customview.pulltorefresh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.pepoc.androidnewtechnique.log.LogManager;

/**
 * Created by yangchen on 2017/8/7.
 */

public class TouchView extends View {

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogManager.i("====================dispatchTouchEvent==========================");

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogManager.i("====================ACTION_DOWN==========================");
                break;
            case MotionEvent.ACTION_MOVE:
                LogManager.i("====================ACTION_MOVE==========================");
                break;
            case MotionEvent.ACTION_UP:
                LogManager.i("====================ACTION_UP==========================");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogManager.i("====================onTouchEvent==========================");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogManager.i("====================ACTION_DOWN==========================");
                break;
            case MotionEvent.ACTION_MOVE:
                LogManager.i("====================ACTION_MOVE==========================");
                break;
            case MotionEvent.ACTION_UP:
                LogManager.i("====================ACTION_UP==========================");
                break;
        }
        return super.onTouchEvent(event);
    }
}
