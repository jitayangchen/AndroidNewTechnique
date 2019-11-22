package com.pepoc.androidnewtechnique.customview.moment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.pepoc.androidnewtechnique.R;

import java.util.List;

public class HistogramChartView extends View {

    private Paint paint;
    private int mWidth, mHeight;
    private List<PointF> pointFS;
    private final static float LINE_CHART_MAX_X = 1400;
    private final static float LINE_CHART_MAX_Y = 2500;
    private final static float LINE_CHART_MIN_Y = 1000;
    private Rect textRect;
    private List<String> xAxisData = null;
    private List<String> yAxisData = null;
    private float chartAreaWidth = -1;
    private float chartAreaHeight = -1;
    private float xLeftOffset, xRightOffset, yOffset = 0;
    private float xTextRightOffset = 0;
    private PathEffect pathEffect;

    public HistogramChartView(Context context) {
        super(context);

        init();
    }

    public HistogramChartView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public HistogramChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mWidth = 1000;
        mHeight = 1000;

        paint = new Paint();
        paint.setColor(getContext().getColor(R.color.colorAccent));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setStrokeWidth(6);
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

        canvas.drawRect(20, 20, 60, getHeight(), paint);
        canvas.drawRect(80, 200, 120, getHeight(), paint);
        canvas.drawRect(140, 140, 180, getHeight(), paint);
    }
}
