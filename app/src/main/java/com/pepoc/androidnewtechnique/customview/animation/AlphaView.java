package com.pepoc.androidnewtechnique.customview.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class AlphaView extends View {

    private RectF roundRect = null;
    private Paint paint;
    private float defaultRectWidth = 300;
    private float defaultRectHeight = 200;

    public AlphaView(Context context) {
        super(context);

        init();
    }

    public AlphaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public AlphaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    private void init() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
            }
        });


        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        roundRect = new RectF((getWidth() - defaultRectWidth) / 2,
                (getHeight() - defaultRectHeight) / 2,
                (getWidth() - defaultRectWidth) / 2 + defaultRectWidth,
                (getHeight() - defaultRectHeight) / 2 + defaultRectHeight);
        canvas.drawRoundRect(roundRect, 5, 5, paint);
    }
}
