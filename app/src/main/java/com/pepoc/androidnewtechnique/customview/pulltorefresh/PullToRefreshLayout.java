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
    private ViewConfiguration configuration;

    private View headerView = null;
    private View contentView = null;
    private boolean orientation;
    private boolean isMove = true;


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

                float moveY = ev.getY();
                orientation = moveY - startY > 0;


                if (orientation) {
                    // 下滑
                    if (isSildeAllView()) {
                        if (startY != -1) {
                            int moveDis = (int) ((startY - moveY) * getDampedCoefficient());
                            scrollBy(0, moveDis);
                        }
                        startY = moveY;
                        LogManager.i("XXX, 139");
                        return true;
                    } else {
                        startY = moveY;
                        LogManager.i("XXX, 143");
                        LogManager.i("---------return super.dispatchTouchEvent(ev);-------");
                        return super.dispatchTouchEvent(ev);
                    }
                } else {
                    // 上滑
                    if (isSildeAllView() && getScrollY() < 0) {
                        if (startY != -1) {
                            int moveDis = (int) ((startY - moveY) * getDampedCoefficient());
                            scrollBy(0, moveDis);
                        }
                        startY = moveY;
                        LogManager.i("XXX, 152");
                        return true;
                    } else {
                        startY = moveY;
                        LogManager.i("XXX, 156");
                        LogManager.i("---------return super.dispatchTouchEvent(ev);-------");
                        return super.dispatchTouchEvent(ev);
                    }
                }


//                getChildAt(0).offsetTopAndBottom((int) ((moveY - startY) * 0.3));


//                startY = moveY;

//                break;
            case MotionEvent.ACTION_UP:
                mScroller.startScroll(getScrollX(), getScrollY(), 0, -getScrollY(), 300);
                postInvalidate();
                return super.dispatchTouchEvent(ev);

            case MotionEvent.ACTION_POINTER_UP:
                startY = -1;
                break;
        }
        if (isSildeAllView() && getScrollY() < 0) {
            return true;
        }
        LogManager.i("---------return super.dispatchTouchEvent(ev);------- " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    private boolean isSildeAllView() {
        // getScrollY() 往下拉是 负值

//        return contentView.getScrollY() <= 0;
        return !canChildScrollUp(contentView);
    }

    /**
     * 是否能下拉
     *
     * @return
     */
    private boolean canChildScrollUp(View view) {
//        if (android.os.Build.VERSION.SDK_INT < 14) {
//            if (view instanceof AbsListView) {
//                final AbsListView absListView = (AbsListView) view;
//                return absListView.getChildCount() > 0
//                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
//                        .getTop() < absListView.getPaddingTop());
//            }
//        }
        return ViewCompat.canScrollVertically(view, -1);
    }


    /**
     * 是否能上拉
     *
     * @return
     */
    private boolean canChildScrollDown(View view) {
//        if (android.os.Build.VERSION.SDK_INT < 14) {
//            if (view instanceof AbsListView) {
//                final AbsListView absListView = (AbsListView) view;
//                return absListView.getChildCount() > 0
//                        && (absListView.getLastVisiblePosition() < absListView.getChildCount() - 1
//                        || absListView.getChildAt(absListView.getChildCount() - 1).getBottom() > absListView.getHeight() - absListView.getPaddingBottom());
//            }
//        }
        return ViewCompat.canScrollVertically(view, 1);

    }

    private double getDampedCoefficient() {
//        return 1.0d;
        return (1.0d - (Math.abs(getScrollY() * 1.0d) / (headerView.getHeight() * 1.0d))) * 0.3d;
    }
}
