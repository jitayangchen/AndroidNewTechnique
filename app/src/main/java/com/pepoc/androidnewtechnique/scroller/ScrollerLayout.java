package com.pepoc.androidnewtechnique.scroller;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/10 14:45
 * @copyright TCL-MIG
 */
public class ScrollerLayout extends ViewGroup {

    private Camera mCamera;
    private Matrix mMatrix;
    private Scroller scroller;
    private int mTouchSlop;
    private float mXLastMove;
    private float mXMove;

    public ScrollerLayout(Context context) {
        super(context);

        init();
    }

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public ScrollerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mCamera = new Camera();
        mMatrix = new Matrix();
        scroller = new Scroller(getContext());
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                child.layout(i * child.getMeasuredWidth(), 0, i * child.getMeasuredWidth() + child.getMeasuredWidth(), child.getMeasuredHeight());
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXLastMove = event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
//                if (getScrollX() < 0) {
//                    scrollTo(0, 0);
//                    return true;
//                }
                mXMove = event.getRawX();
                scrollBy((int)(mXLastMove - mXMove), 0);
                mXLastMove = event.getRawX();
                break;
            case MotionEvent.ACTION_UP:
//                scroller.startScroll(getScrollX(), 0, 200, 0);
//                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
//        super.dispatchDraw(canvas);
        View view = getChildAt(0);
        canvas.save();
        mCamera.save();
//        mCamera.rotateX(30);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();

//        mMatrix.preTranslate(-view.getWidth() / 2, -view.getHeight() / 2);
//        mMatrix.postTranslate(view.getWidth() / 2, view.getHeight() / 2);
        canvas.concat(mMatrix);
        drawChild(canvas, view, getDrawingTime());
        canvas.restore();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
//        if (scroller.computeScrollOffset()) {
//            scrollTo(scroller.getCurrX(), scroller.getCurrY());
//            invalidate();
//        }
    }
}
