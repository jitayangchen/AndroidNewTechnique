package com.pepoc.androidnewtechnique.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;

/**
 * Created by yangchen on 2017/3/2.
 */

public class NotificationMsgItemView extends RelativeLayout {

    private GestureDetector gestureDetector;
    private float downX;
    private int scaledTouchSlop;
    private boolean isFlingHandleExitEvent = false;

    public NotificationMsgItemView(Context context) {
        super(context);

        init();
    }

    public NotificationMsgItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public NotificationMsgItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                float distance = e2.getRawX() - downX;
                setTranslationX(distance);
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = ev.getRawX();
                isFlingHandleExitEvent = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float distance = ev.getRawX() - downX;
                if(distance>scaledTouchSlop)
                    return true;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
