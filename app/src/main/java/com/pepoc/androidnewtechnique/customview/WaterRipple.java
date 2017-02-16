package com.pepoc.androidnewtechnique.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.pepoc.androidnewtechnique.log.LogManager;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/9 10:50
 * @copyright TCL-MIG
 */
public class WaterRipple extends View {

    private double[] positionX;
    private Path path;
    private Paint paint1;
    private Paint paint;
    private double w;
    private float distance = 0;
    private PorterDuffXfermode porterDuffXfermode;

    public WaterRipple(Context context) {
        super(context);

        init();
    }

    public WaterRipple(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public WaterRipple(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        path = new Path();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.rgb(66, 125, 245));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);

        paint1 = new Paint();
        paint1.setAntiAlias(true);
        paint1.setColor(Color.WHITE);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setStrokeWidth(5);

        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogManager.i("--------------------onLayout---------------------getWidth = " + getWidth());
//        positionX = new double[getWidth()];
//        w = Math.PI * 2 / (getWidth() * 1.5);
//        for (int i = 0; i < getWidth(); i++) {
//            positionX[i] = 100 * Math.sin(i * w);
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long startTime = System.currentTimeMillis();
        w = Math.PI * 2 / (getWidth() * 1.5);

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);

            canvas.drawCircle(getWidth() >> 1, getHeight() >> 1, getWidth() >> 1, paint1);

            paint.setXfermode(porterDuffXfermode);
            for (int i = 0; i < getWidth(); i++) {
                canvas.drawLine(i, (float) ((float) 50 * Math.sin(i * w + distance) + getHeight() / 2), i, getHeight(), paint);
            }
            paint.setXfermode(null);

        canvas.restoreToCount(layerId);

        distance += 0.06;
        postInvalidate();

        long endTime = System.currentTimeMillis();
        LogManager.i("time === " + (endTime - startTime));
    }
}
