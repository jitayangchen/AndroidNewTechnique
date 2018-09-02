package com.pepoc.androidnewtechnique.customview.safemobile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.util.Pools;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.pepoc.androidnewtechnique.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class RippleView extends View {

    private final static Pools.SimplePool<Circle> SIMPLE_POOL = new Pools.SimplePool<>(2);

    private Context mContext;

    // 画笔对象
    private Paint mPaint;

    // View宽
    private float mWidth;

    // View高
    private float mHeight;

    // 声波的圆圈集合
    private List<Circle> mRipples;

    private int sqrtNumber;

    // 圆圈扩散的速度
    private int mSpeed;

    // 圆圈之间的密度
    private int mDensity;

    // 圆圈的颜色
    private int mColor;

    // 圆圈是否为填充模式
    private boolean mIsFill;

    private final static float CIRCLE_RADIUS = 100;

    private int reuseIndex = -1;
    private Circle removeCircle;

    public RippleView(Context context) {
        this(context, null);
    }

    public RippleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取用户配置属性
        mColor = Color.WHITE;
        mSpeed = 4;
        mDensity = 70;
        mIsFill = false;

        init();
    }

    private void init() {
        mContext = getContext();

        // 设置画笔样式
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(DensityUtil.dip2px(mContext, 6));
        if (mIsFill) {
            mPaint.setStyle(Paint.Style.FILL);
        } else {
            mPaint.setStyle(Paint.Style.STROKE);
        }
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);

        // 添加第一个圆圈
        mRipples = new ArrayList<>();
        Circle c = new Circle(CIRCLE_RADIUS, 255);
        mRipples.add(c);

        mDensity = DensityUtil.dip2px(mContext, mDensity);

        // 设置View的圆为半透明
        setBackgroundColor(Color.TRANSPARENT);






        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                animatedFraction = (float) animation.getAnimatedValue();

                invalidate();
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }
        });

//        valueAnimator.setStartDelay();
        valueAnimator.start();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 内切正方形
        drawInCircle(canvas);

        // 外切正方形
        // drawOutCircle(canvas);
    }

    /**
     * 圆到宽度
     *
     * @param canvas
     */
    private void drawInCircle(Canvas canvas) {
        for (int i = 0; i < mRipples.size(); i++) {
            Circle c = mRipples.get(i);

            if (c.radius <= CIRCLE_RADIUS + 50) {
                c.alpha = (int) ((c.radius - CIRCLE_RADIUS)/50f * 255);
            } else if (c.radius > mWidth / 2 - 50 && c.radius <= mWidth / 2 ) {
                float v = mWidth / 2f - c.radius;
                c.alpha = (int) (v/50f * 255);
            }
            mPaint.setAlpha(c.alpha);
            canvas.drawCircle(mWidth / 2, mHeight / 2, c.radius - mPaint.getStrokeWidth(), mPaint);

            // 当圆超出View的宽度后删除
            if (c.radius > mWidth / 2) {
//                mRipples.remove(i);
                reuseIndex = i;
            } else {
                c.radius += mSpeed;
            }
        }

        if (reuseIndex != -1) {
            removeCircle = mRipples.remove(reuseIndex);
            reuseIndex = -1;
            SIMPLE_POOL.release(removeCircle);
        }

        if (mRipples.size() > 0) {
            if (mRipples.get(mRipples.size() - 1).radius > DensityUtil.dip2px(mContext, mDensity)) {

                Circle acquire = SIMPLE_POOL.acquire();
                if (acquire == null) {
                    mRipples.add(new Circle(CIRCLE_RADIUS, 255));
                } else {
                    acquire.radius = CIRCLE_RADIUS;
                    acquire.alpha = 255;
                    mRipples.add(acquire);
                }
            }
        }


//        invalidate();
    }


    /**
     * 圆到对角线
     *
     * @param canvas
     */
    private void drawOutCircle(Canvas canvas) {
        canvas.save();

        // 使用勾股定律求得一个外切正方形中心点离角的距离
        sqrtNumber = (int) (Math.sqrt(mWidth * mWidth + mHeight * mHeight) / 2);

        // 变大
        for (int i = 0; i < mRipples.size(); i++) {

            // 启动圆圈
            Circle c = mRipples.get(i);
            mPaint.setAlpha(c.alpha);// （透明）0~255（不透明）
            canvas.drawCircle(mWidth / 2, mHeight / 2, c.radius - mPaint.getStrokeWidth(), mPaint);

            // 当圆超出对角线后删掉
            if (c.radius > sqrtNumber) {
                mRipples.remove(i);
            } else {
                // 计算不透明的度数
                double degree = 255 - c.radius * (255 / (double) sqrtNumber);
                c.alpha = (int) degree;
                c.radius += 1;
            }
        }

        // 里面添加圆
        if (mRipples.size() > 0) {
            // 控制第二个圆出来的间距
            if (mRipples.get(mRipples.size() - 1).radius == 50) {
                mRipples.add(new Circle(0, 255));
            }
        }
        invalidate();
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int myWidthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int myWidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int myHeightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int myHeightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        // 获取宽度
        if (myWidthSpecMode == MeasureSpec.EXACTLY) {
            // match_parent
            mWidth = myWidthSpecSize;
        } else {
            // wrap_content
            mWidth = DensityUtil.dip2px(mContext, 120);
        }

        // 获取高度
        if (myHeightSpecMode == MeasureSpec.EXACTLY) {
            mHeight = myHeightSpecSize;
        } else {
            // wrap_content
            mHeight = DensityUtil.dip2px(mContext, 120);
        }

        // 设置该view的宽高
        setMeasuredDimension((int) mWidth, (int) mHeight);
    }


    class Circle {
        Circle(float radius, int alpha) {
            this.radius = radius;
            this.alpha = alpha;
        }

        float radius;

        int alpha;


    }

}