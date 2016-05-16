package com.pepoc.androidnewtechnique.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.pepoc.androidnewtechnique.log.LogManager;

/**
 * Created by Yangchen on 2016/5/11.
 */
public class ParentView extends RelativeLayout {

    public ParentView(Context context) {
        super(context);
    }

    public ParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogManager.i("ParentView --- dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogManager.i("ParentView --- onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogManager.i("ParentView --- onTouchEvent");
        return super.onTouchEvent(event);
    }
}
