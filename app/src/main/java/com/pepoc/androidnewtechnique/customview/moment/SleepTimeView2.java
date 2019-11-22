package com.pepoc.androidnewtechnique.customview.moment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SleepTimeView2 extends View {

    private Paint paint;
    private RectF rectF;
    private Path path;
    private int mWidth, mHeight;

    public SleepTimeView2(Context context) {
        super(context);

        init();
    }

    public SleepTimeView2(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SleepTimeView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mWidth = 1200;
        mHeight = 300;

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(6);


        rectF = new RectF();

        path = new Path();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(Math.min(widthSize, mWidth), Math.min(heightSize, mHeight));
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(Math.min(widthSize, mWidth), heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, Math.min(heightSize, mHeight));
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        rectF.set(24, 0, getWidth() - 24, getHeight() - 10);




        path.moveTo(rectF.left, rectF.bottom);
        path.quadTo(rectF.centerX(), rectF.top - rectF.height() * 0.9f, rectF.right, rectF.bottom);

        canvas.drawPath(path, paint);

        canvas.drawLine(rectF.left - 24, rectF.bottom, rectF.left + 30, rectF.bottom, paint);
        canvas.drawLine(rectF.right - 30, rectF.bottom, rectF.right + 24, rectF.bottom, paint);
    }
}
