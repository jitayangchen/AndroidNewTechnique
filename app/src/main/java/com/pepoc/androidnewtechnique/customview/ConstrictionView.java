package com.pepoc.androidnewtechnique.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/14 17:24
 * @copyright TCL-MIG
 */
public class ConstrictionView extends View {

    private Paint paint;
    private float radius;
    private PorterDuffXfermode porterDuffXfermode;
    private float value = 1.0f;
    private ValueAnimator valueAnimator;
    private int widthSize;
    private int heightSize;

    public ConstrictionView(Context context) {
        super(context);

        init();
    }

    public ConstrictionView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public ConstrictionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

        paint = new Paint();
        paint.setColor(Color.rgb(66, 155, 255));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);


        valueAnimator = ValueAnimator.ofFloat(1.0f, 0f);
        valueAnimator.setDuration(600);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

    }

    public void startAnimator() {
        valueAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthSize = MeasureSpec.getSize(widthMeasureSpec);
        heightSize = MeasureSpec.getSize(heightMeasureSpec);
        radius = (float) (Math.sqrt(widthSize * widthSize + heightSize * heightSize) / 2);
//        radius = (float) Math.sqrt(widthSize * widthSize + heightSize * heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
            paint.setXfermode(porterDuffXfermode);
            canvas.drawCircle(widthSize / 2, heightSize / 2, radius * value, paint);
            paint.setXfermode(null);

        canvas.restoreToCount(layerId);



//        int layerId = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);
//
//        canvas.drawCircle(50, heightSize - 50, radius, paint);
//        paint.setXfermode(porterDuffXfermode);
//        canvas.drawCircle(50, heightSize - 50, radius * value, paint);
//        paint.setXfermode(null);
//
//        canvas.restoreToCount(layerId);

    }
}
