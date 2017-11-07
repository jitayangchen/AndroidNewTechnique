package com.pepoc.androidnewtechnique.listview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by yangchen on 2017/9/1.
 */

public class MultiTouchRecyclerView extends RecyclerView {


    public MultiTouchRecyclerView(Context context) {
        this(context, null);
    }

    public MultiTouchRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiTouchRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent e) {
//        switch (e.getActionMasked()) {
//            case MotionEvent.ACTION_DOWN:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//
//                break;
//            case MotionEvent.ACTION_POINTER_UP:
//                LogManager.i("onInterceptTouchEvent", "MotionEvent.ACTION_POINTER_UP");
//                super.onInterceptTouchEvent(e);
//                return true;
//        }
//        return super.onInterceptTouchEvent(e);
//    }
}
