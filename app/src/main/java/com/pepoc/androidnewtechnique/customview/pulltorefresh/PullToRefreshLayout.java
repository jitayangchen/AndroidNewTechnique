package com.pepoc.androidnewtechnique.customview.pulltorefresh;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
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
    private boolean isMove = false;
    private int mPagingTouchSlop;
    private boolean isIntercept = false;
    private boolean lalala = false;
    private boolean isCancel = false;


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

        final ViewConfiguration conf = ViewConfiguration.get(getContext());
        mPagingTouchSlop = conf.getScaledTouchSlop() * 2;
    }

    public void setHeaderView(View headerView) {
//        this.headerView = headerView;
        addView(headerView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        final int count = getChildCount();
//        for (int i = 0; i < count; i++) {
//            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
//        }
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() > 0) {
            View childAt0 = getChildAt(0);
            View childAt1 = getChildAt(1);
            if (childAt0 != null && childAt0 instanceof NestedScrollView) {
                contentView = childAt0;
                headerView = childAt1;
            } else if (childAt1 != null && childAt1 instanceof NestedScrollView) {
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
    float startMoveY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogManager.i("getAction === " + ev.getAction());
        LogManager.i("getActionMasked === " + ev.getActionMasked());
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                startMoveY = startY = ev.getY();
                if (mScroller.computeScrollOffset()) {
                    mScroller.forceFinished(true);
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                startY = ev.getY();
                isMove = true;
                if (isSildeAllView() && getScrollY() < 0) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();
                boolean orientation = moveY - startY > 0;

                if (Math.abs(moveY - startMoveY) > mPagingTouchSlop) {
                    isIntercept = true;
                } else {
                    LogManager.i("YYY if (Math.abs(moveY - startMoveY) > mPagingTouchSlop) {");
                }

                if (orientation) {
                    // 下滑
                    if (isSildeAllView()) {
                        if (!isMove) {
                            int moveDis = (int) ((startY - moveY) * getDampedCoefficient());
                            scrollBy(0, moveDis);
                        } else {
                            isMove = false;
                        }
                        startY = moveY;
                        if (!isCancel) {
                            ev.setAction(MotionEvent.ACTION_CANCEL);
                            super.dispatchTouchEvent(ev);
                            isCancel = true;
                        }
                        return true;
                    } else {
                        startY = moveY;
                        return super.dispatchTouchEvent(ev);
                    }
                } else {
                    // 上滑
                    if (isSildeAllView() && getScrollY() < 0) {
                        if (!isMove) {
                            int moveDis = (int) ((startY - moveY) * getDampedCoefficient());
                            if (Math.abs(getScrollY()) < moveDis) {
                                scrollBy(0, Math.abs(getScrollY()));
                            } else {
                                scrollBy(0, moveDis);
                            }
                        } else {
                            isMove = false;
                        }
                        startY = moveY;
                        return true;
                    } else {
                        startY = moveY;
                        if (getScrollY() == 0 && !lalala) {
                            ev.setAction(MotionEvent.ACTION_UP);
                            super.dispatchTouchEvent(ev);
                            ev.setAction(MotionEvent.ACTION_DOWN);
                            super.dispatchTouchEvent(ev);
                            ev.setAction(MotionEvent.ACTION_MOVE);
                            lalala = true;
                        }
                        return super.dispatchTouchEvent(ev);
                    }
                }

            case MotionEvent.ACTION_UP:
                mScroller.startScroll(getScrollX(), getScrollY(), 0, -getScrollY(), 300);
                postInvalidate();
                isIntercept = false;
                lalala = false;
                isCancel = false;
                return super.dispatchTouchEvent(ev);

            case MotionEvent.ACTION_POINTER_UP:
                isMove = true;
                if (isSildeAllView() && getScrollY() < 0) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            LogManager.i("mScroller.getCurrX() === " + mScroller.getCurrX());
            LogManager.i("mScroller.getCurrY() === " + mScroller.getCurrY());
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    private boolean isSildeAllView() {
        return !canChildScrollUp(contentView);
    }

    /**
     * 是否能下拉
     *
     * @return
     */
    private boolean canChildScrollUp(View view) {
        return ViewCompat.canScrollVertically(view, -1);
    }


    /**
     * 是否能上拉
     *
     * @return
     */
    private boolean canChildScrollDown(View view) {
        return ViewCompat.canScrollVertically(view, 1);

    }

    private double getDampedCoefficient() {
//        return 1.0d;
        return (1.0d - (Math.abs(getScrollY() * 1.0d) / (headerView.getHeight() * 1.0d))) * 0.5d;
    }

    private void sendCancelEvent(MotionEvent ev) {
        MotionEvent e = MotionEvent.obtain(ev.getDownTime(), ev.getEventTime() + ViewConfiguration.getLongPressTimeout(), MotionEvent.ACTION_CANCEL, ev.getX(), ev.getY(), ev.getMetaState());
        dispatchTouchEvent(e);
    }

    private void sendDownEvent(MotionEvent ev) {
        MotionEvent e = MotionEvent.obtain(ev.getDownTime(), ev.getEventTime(), MotionEvent.ACTION_DOWN, ev.getX(), ev.getY(), ev.getMetaState());
        dispatchTouchEvent(e);
    }
}
