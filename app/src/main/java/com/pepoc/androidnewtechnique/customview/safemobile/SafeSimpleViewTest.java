package com.pepoc.androidnewtechnique.customview.safemobile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.pepoc.androidnewtechnique.R;

public class SafeSimpleViewTest extends View {

    public final static long ANIMATED_DURATION = 3000;
    private Paint paint, paint2;
    private float animatedFraction;
    private CircleRipple circleRipple;
    private Bitmap bitmap;
    private Rect mSrcRect;
    private Rect mDestRect;
    private Paint mPaintCircle;


    public SafeSimpleViewTest(Context context) {
        super(context);

        init();
    }

    public SafeSimpleViewTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SafeSimpleViewTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);


        mPaintCircle = new Paint();
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStrokeWidth(10);
        mPaintCircle.setStrokeCap(Paint.Cap.ROUND);
        mPaintCircle.setColor(Color.parseColor("#4d7aad"));
        mPaintCircle.setPathEffect(new DashPathEffect(new float[]{5, 10}, 0));



        circleRipple = new CircleRipple();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(ANIMATED_DURATION);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatedFraction = (float) animation.getAnimatedValue();

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

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        mSrcRect = new Rect(0, 0, bitmap.getWidth()/2, bitmap.getHeight());
        mDestRect = new Rect(400, 400, bitmap.getWidth() + 400, bitmap.getHeight() + 400);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        circleRipple.setCx(getMeasuredWidth()/2);
        circleRipple.setCy(getMeasuredHeight()/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        circleRipple.drawCircleRipple(canvas, paint);

//        canvas.rotate(360f * animatedFraction, bitmap.getWidth()/2 + 400, bitmap.getHeight()/2 + 400);
//        canvas.drawBitmap(bitmap, null, mDestRect, null);




        //绘制长度为4的实线后再绘制长度为4的空白区域，0位间隔
        canvas.drawCircle(getWidth()/2, getHeight()/2, 200, mPaintCircle);
    }
}
