package com.pepoc.androidnewtechnique.bitmap;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;
import com.pepoc.androidnewtechnique.util.Util;

public class SplashAnimatorView extends View {

    private Bitmap mainLogoBallBitmap;
    private Bitmap mainLogoBallLeafBitmap;
    private Bitmap mainLogoPlusBitmap;
    private Bitmap mainLogoTextBitmap;
    private ValueAnimator splashAnimator;
    private float animatorValue;
    private int spacing, bottomSpacing;
    private Paint paint;
    private float moveDistance;
    private float rotateScale;

    public SplashAnimatorView(Context context) {
        super(context);

        init();
    }

    public SplashAnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SplashAnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int resultWidthSize;
        int resultHeightSize;

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            resultWidthSize = widthSize;
            resultHeightSize = heightSize;
        } else {
            resultWidthSize = mainLogoTextBitmap.getWidth();
            resultHeightSize = mainLogoBallBitmap.getHeight() + mainLogoTextBitmap.getHeight() + bottomSpacing;
        }
        setMeasuredDimension(resultWidthSize, resultHeightSize);
    }

    private void init() {
        BitmapFactory.Options options1 = new BitmapFactory.Options();
        options1.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.main_splash_logo_ball, options1);
        LogManager.i("options.outWidth === " + options1.outWidth);
        LogManager.i("options.outHeight === " + options1.outHeight);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
//        options.inDensity = options1.inDensity;
//        options.inSampleSize = 2;

//        mainLogoBallBitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.main_splash_logo_ball), null, options);
//        LogManager.i("options.outWidth === " + options.outWidth);
//        LogManager.i("options.outHeight === " + options.outHeight);
//
//        LogManager.i("mainLogoBallBitmap.getWidth() = " + mainLogoBallBitmap.getWidth());
//        LogManager.i("mainLogoBallBitmap.getHeight() = " + mainLogoBallBitmap.getHeight());
//        mainLogoBallLeafBitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.main_splash_logo_ball_leaf), null, options);
//        mainLogoPlusBitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.main_splash_logo_plus), null, options);
//        mainLogoTextBitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.main_splash_logo_text), null, options);

        mainLogoBallBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.main_splash_logo_ball, options);
        LogManager.i("options.outWidth === " + options.outWidth);
        LogManager.i("options.outHeight === " + options.outHeight);

        LogManager.i("mainLogoBallBitmap.getWidth() = " + mainLogoBallBitmap.getWidth());
        LogManager.i("mainLogoBallBitmap.getHeight() = " + mainLogoBallBitmap.getHeight());
        mainLogoBallLeafBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.main_splash_logo_ball_leaf, options);
        mainLogoPlusBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.main_splash_logo_plus, options);
        mainLogoTextBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.main_splash_logo_text, options);



        paint = new Paint();

        spacing = Util.dip2px(getContext(), 20);
        bottomSpacing = Util.dip2px(getContext(), 40);

        splashAnimator = ValueAnimator.ofFloat(0f, 1f);
//        splashAnimator.setInterpolator(new LinearInterpolator());
        splashAnimator.setDuration(1200);
        splashAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatorValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

    }

    public void start() {
        splashAnimator.start();
    }

    public void stop() {
        if (splashAnimator != null && splashAnimator.isRunning()) {
            splashAnimator.cancel();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 球体开始落下，并且开始渐渐出现
        if (animatorValue <= 0.4f) {
            moveDistance = bottomSpacing * (animatorValue / 0.4f);
            paint.setAlpha((int) (animatorValue / 0.4f * 255));
        }else {
            paint.setAlpha(255);
        }
        canvas.drawBitmap(mainLogoBallBitmap, (getWidth() - mainLogoBallBitmap.getWidth())/2f, moveDistance, paint);

        if (animatorValue > 0.3f) {
            // 显示Logo里面的叶子（飘带）
            if (animatorValue <= 0.7f) {
                rotateScale = (animatorValue - 0.3f) / 0.4f;
                paint.setAlpha((int) (rotateScale * 255));
            } else {
                paint.setAlpha(255);
            }
            canvas.save();
            canvas.rotate(-20f + rotateScale * 20f, getWidth() / 2f, moveDistance + mainLogoBallLeafBitmap.getWidth() / 2f);
            canvas.drawBitmap(mainLogoBallLeafBitmap, (getWidth() - mainLogoBallLeafBitmap.getWidth()) / 2f, moveDistance, paint);
            canvas.restore();


            // 显示Logo里面的+号
            if (animatorValue >= 0.4f) {
                if (animatorValue <= 0.7f) {
                    paint.setAlpha((int) ((animatorValue - 0.4f) / 0.3f * 255));
                } else {
                    paint.setAlpha(255);
                }
                canvas.drawBitmap(mainLogoPlusBitmap, (getWidth() - mainLogoPlusBitmap.getWidth()) / 2f, (mainLogoBallBitmap.getHeight() - mainLogoPlusBitmap.getHeight()) / 2f + moveDistance, paint);
            }
        }

        if (animatorValue >= 0.2f) {
            paint.setAlpha((int) ((animatorValue - 0.2f) / 0.8f * 255));
            canvas.drawBitmap(mainLogoTextBitmap, 0, mainLogoBallBitmap.getHeight() + bottomSpacing, paint);
        }
    }
}
