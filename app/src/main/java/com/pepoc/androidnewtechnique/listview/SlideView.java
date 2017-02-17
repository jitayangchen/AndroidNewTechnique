package com.pepoc.androidnewtechnique.listview;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by yangchen on 2016/10/28.
 */

public class SlideView extends LinearLayout {

    private ViewDragHelper mDragger;
    private float locationY = 0;

    public SlideView(Context context) {
        super(context);
        init();
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        final int count = getChildCount();
//
//        for (int i = 0; i < count; i++) {
//            View child = getChildAt(i);
//            if (child.getVisibility() != GONE) {
//                child.layout(l, t, r, b);
//            }
//        }
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                locationY = event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                setTranslationY(event.getY() - locationY);
//                break;
//            case MotionEvent.ACTION_UP:
//
//                break;
//        }
        mDragger.processTouchEvent(event);
        return true;
    }

    private void init() {
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback()
        {
            @Override
            public boolean tryCaptureView(View child, int pointerId)
            {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx)
            {
                return 0;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy)
            {
                return top;
            }
        });
    }
}
