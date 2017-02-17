package com.pepoc.androidnewtechnique.textview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by yangchen on 2016/10/29.
 */

public class CustomTextView extends TextView {

    private Paint paint;
    private Path path, path2;
    private ValueAnimator valueAnimator;
    private float animatedValue;
    private RectF rectF;

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        path = new Path();



        valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(100);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatedValue = (Float) animation.getAnimatedValue();
//                LogManager.i("animatedValue === " + animatedValue);
                invalidate();
            }
        });
//        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle((getWidth() + 100) * animatedValue - 50, getHeight()/2, 50, paint);

        path.rewind();
        rectF = new RectF(0, 0, getHeight(), getHeight());
        path.moveTo(0, 0);
        path.addArc(rectF, 90, 180);
        path.lineTo(getWidth(), 0);
        path.lineTo(getWidth() - 50, getHeight());
        path.lineTo(rectF.width()/2, getHeight());

//        path2 = new Path();
//        path2.
//        path.op(Path.Op.INTERSECT);
        canvas.drawPath(path, paint);
        super.onDraw(canvas);
    }
}
