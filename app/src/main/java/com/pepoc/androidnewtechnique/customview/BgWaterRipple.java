package com.pepoc.androidnewtechnique.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/9 10:50
 * @copyright TCL-MIG
 */
public class BgWaterRipple extends View {

    private Paint paint;
    private double wtemp = 1;
    private double w;
    private int a = 100;
    private double b = 0;
    private double h = 0;

    public BgWaterRipple(Context context) {
        super(context);

        init();
    }

    public BgWaterRipple(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public BgWaterRipple(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width, height;

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            width = widthSize;
            height = heightSize;
        } else {
            width = 400;
            height = 400;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.rgb(66, 125, 245));

        paint.setColor(Color.argb(50, 54, 110, 249));
        w = Math.PI * 2 / (getWidth() * wtemp);
        for (int i = 0; i < getWidth(); i++) {
            canvas.drawLine(i, (float) ((float) a * Math.sin(i * w + b) + getHeight() - 400 + h + (100)), i, getHeight(), paint);
        }

//        paint.setColor(Color.parseColor("#33125eff"));
//        w = Math.PI * 2 / (getWidth() * 1.71d);
//        for (int i = 0; i < getWidth(); i++) {
//            canvas.drawLine(i, (float) (93.0f * Math.sin(i * w -5.0d) + getHeight() - 400), i, getHeight(), paint);
//        }

        paint.setColor(Color.parseColor("#333954ff"));
        w = Math.PI * 2 / (getWidth() * 1.4d);
        for (int i = 0; i < getWidth(); i++) {
            canvas.drawLine(i, (float) (72.0f * Math.sin(i * w -9.0d) + getHeight() - 350 + (100)), i, getHeight(), paint);
        }

        paint.setColor(Color.parseColor("#332c48ff"));
        w = Math.PI * 2 / (getWidth() * 1.76d);
        for (int i = 0; i < getWidth(); i++) {
            canvas.drawLine(i, (float) (85.0f * Math.sin(i * w -6.0d) + getHeight() - 400 + (100)), i, getHeight(), paint);
        }

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    public void setWtemp(double wtemp) {
        this.wtemp = wtemp;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void setH(double h) {
        this.h = h;
    }
}
