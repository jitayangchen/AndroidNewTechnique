package com.pepoc.androidnewtechnique.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.pepoc.androidnewtechnique.log.LogManager;

/**
 * Created by Yangchen on 2016/5/11.
 */
public class ParentView extends RelativeLayout {

    private final static String TAG = "ParentView";

    public ParentView(Context context) {
        super(context);

        init();
    }

    public ParentView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public ParentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setClickable(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogManager.i(TAG, "--------onMeasure-------");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LogManager.i(TAG, "--------onLayout-------");
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        LogManager.i("ev.getAction() === " + ev.getAction());
//        LogManager.i("ev.getActionMasked() === " + ev.getActionMasked());
//        MotionEventCompat.getActionMasked(ev);

//        ArrayList<View> touchables = getTouchables();
//        for (View view : touchables) {
//            view.setVisibility(View.GONE);
//        }
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                LogManager.i(TAG, "dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
//                ev.setAction(MotionEvent.ACTION_CANCEL);
//                LogManager.i(TAG, "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogManager.i(TAG, "dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogManager.i(TAG, "dispatchTouchEvent ACTION_CANCEL");
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogManager.i(TAG, "onInterceptTouchEvent ACTION_DOWN");
//                return true;
                break;
            case MotionEvent.ACTION_MOVE:
//                LogManager.i(TAG, "onInterceptTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogManager.i(TAG, "onInterceptTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogManager.i(TAG, "onInterceptTouchEvent ACTION_CANCEL");
                break;
        }
        return super.onInterceptTouchEvent(ev);
//        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        ArrayList<View> touchables = getTouchables();
//        for (View view : touchables) {
//            view.setVisibility(View.GONE);
//        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Thread.dumpStack();
                LogManager.i(TAG, "onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogManager.i(TAG, "onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogManager.i(TAG, "onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogManager.i(TAG, "onTouchEvent ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean isTouchPointInView(View view, int x, int y) {
        if (view == null) {
            return false;
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();

        return y >= top && y <= bottom && x >= left && x <= right;
    }
}
