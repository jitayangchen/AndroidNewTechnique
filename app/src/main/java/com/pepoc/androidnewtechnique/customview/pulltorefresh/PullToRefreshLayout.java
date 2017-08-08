package com.pepoc.androidnewtechnique.customview.pulltorefresh;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.pepoc.androidnewtechnique.log.LogManager;

/**
 * Created by yangchen on 2017/8/7.
 */

public class PullToRefreshLayout extends ViewGroup {

    private Scroller mScroller;

    private View headerView = null;
    private View contentView = null;
    private boolean orientation;



    public PullToRefreshLayout(Context context) {
        super(context);

        init();
    }

    public PullToRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public PullToRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setClickable(true);

        mScroller = new Scroller(getContext());
    }

    public void setHeaderView(View headerView) {
//        this.headerView = headerView;
        addView(headerView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }

//        if (getChildCount() > 0) {
//            View childAt0 = getChildAt(0);
//            View childAt1 = getChildAt(1);
//            if (childAt0 instanceof NestedScrollView) {
//                contentView = childAt0;
//                headerView = childAt1;
//            } else if (childAt1 instanceof NestedScrollView) {
//                contentView = childAt1;
//                headerView = childAt0;
//            }
//
//            if (contentView != null) {
//                contentView.measure(widthMeasureSpec, heightMeasureSpec);
//            }
//            if (headerView != null) {
//                measureChildWithMargins(headerView, widthMeasureSpec, 0, heightMeasureSpec, 0);
//            }
//        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() > 0) {
            View childAt0 = getChildAt(0);
            View childAt1 = getChildAt(1);
            if (childAt0 instanceof NestedScrollView) {
                contentView = childAt0;
                headerView = childAt1;
            } else if (childAt1 instanceof NestedScrollView) {
                contentView = childAt1;
                headerView = childAt0;
            }

            if (contentView != null) {
                contentView.layout(l, t, r, b);
            }
            if (headerView != null) {
                headerView.layout(l, t - headerView.getMeasuredHeight(), r, t);
            }
        }
    }

    float startY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogManager.i("contentView.getScrollY() === " + contentView.getScrollY());
        LogManager.i("getScrollY() === " + getScrollY());

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                if (mScroller.computeScrollOffset()) {
                    mScroller.forceFinished(true);
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
//                LogManager.i("====================ACTION_MOVE=============== startY === " + startY);

                float moveY = ev.getY();
//                LogManager.i("==================== moveY === " + moveY);



                if (moveY - startY > 0) {
                    LogManager.i("orientation ++++++++++++++++++++++++");
                    orientation= true;
                } else {
                    LogManager.i("orientation ************************");
                    orientation = false;
                }


//                getChildAt(0).offsetTopAndBottom((int) ((moveY - startY) * 0.3));
                if (startY != -1) {
                    if (isShowHeader() && orientation || getScrollY() < 0) {
                        int moveDis = (int) ((startY - moveY) * (1.0f - (Math.abs(getScrollY() * 1.0f) / (headerView.getHeight() / 2 * 1.0f))) * 0.3);
                        scrollBy(0, moveDis);
                        startY = moveY;
                        return true;
                    } else {
                        LogManager.i("Scroll ---------------------------------");
                        startY = moveY;
                        return super.dispatchTouchEvent(ev);
                    }
                }
                startY = moveY;

                break;
            case MotionEvent.ACTION_UP:
//                LogManager.i("====================ACTION_UP==========================");
//                mScroller.startScroll(getScrollX(), getScrollY(), 0, -(getBottom() + getScrollY()), 300);
                mScroller.startScroll(getScrollX(), getScrollY(), 0, -getScrollY(), 300);
                postInvalidate();
                break;

            case MotionEvent.ACTION_POINTER_UP:
                startY = -1;
//                LogManager.i("====================ACTION_POINTER_UP==========================");
                break;
        }
        if (isShowHeader() && orientation || getScrollY() < 0) {
            return true;
        } else {
            LogManager.i("getScrollY #####################################");
            return super.dispatchTouchEvent(ev);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    private boolean isShowHeader() {
        return contentView.getScrollY() <= 0;
//        return !contentView.canScrollVertically(-1);
    }
}
