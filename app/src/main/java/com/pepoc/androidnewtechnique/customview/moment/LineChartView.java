package com.pepoc.androidnewtechnique.customview.moment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class LineChartView extends View {

    private Paint pathPaint, textPaint;
    private Path path, strokePath;
    private LinearGradient linearGradient;
    private int mWidth, mHeight;
    private List<PointF> pointFS;
    private final static float LINE_CHART_MAX_X = 1300;
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

    public LineChartView(Context context) {
        super(context);

        init();
    }

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mWidth = 1000;
        mHeight = 1000;

        pathPaint = new Paint();
        pathPaint.setColor(Color.RED);
        pathPaint.setAntiAlias(true);
        pathPaint.setStrokeWidth(4);
        pathPaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(30);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextAlign(Paint.Align.CENTER);

        pathEffect = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);


        linearGradient = new LinearGradient(0, 1100, 0, 2300, Color.GRAY, 0xffffff, Shader.TileMode.CLAMP);

        pointFS = new ArrayList<>();
        pointFS.add(new PointF(0, 1600));
        pointFS.add(new PointF(100, 1900));
        pointFS.add(new PointF(200, 1600));
        pointFS.add(new PointF(300, 1400));
        pointFS.add(new PointF(400, 1100));
        pointFS.add(new PointF(500, 1800));
        pointFS.add(new PointF(600, 1600));
        pointFS.add(new PointF(700, 2300));
        pointFS.add(new PointF(800, 1600));
        pointFS.add(new PointF(900, 1300));
        pointFS.add(new PointF(1000, 2200));
        pointFS.add(new PointF(1100, 1700));
        pointFS.add(new PointF(1200, 1600));
        pointFS.add(new PointF(1300, 2300));

        path = new Path();
        strokePath = new Path();

        textRect = new Rect();

        xAxisData = new ArrayList<>();
        xAxisData.add("周一");
        xAxisData.add("周二");
        xAxisData.add("周三");
        xAxisData.add("周四");
        xAxisData.add("周五");
        xAxisData.add("周六");
        xAxisData.add("周日");

        yAxisData = new ArrayList<>();
        yAxisData.add("1000");
        yAxisData.add("2000");
        yAxisData.add("3000");
        yAxisData.add("4000");
        yAxisData.add("5000");
        yAxisData.add("6000");
        yAxisData.add("7000");


        textPaint.getTextBounds(xAxisData.get(0), 0, xAxisData.get(0).length(), textRect);
        xRightOffset = textRect.width() / 2.0f;
        yOffset = textRect.height() / 2.0f;

        textPaint.getTextBounds(yAxisData.get(0), 0, yAxisData.get(0).length(), textRect);
        xTextRightOffset = textRect.height() / 2.0f;
        xLeftOffset = textRect.width() + xTextRightOffset;
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
        if (chartAreaWidth == -1) {
            chartAreaWidth = getWidth() - xLeftOffset - xRightOffset;
        }
        if (chartAreaHeight == -1) {
            chartAreaHeight = getHeight() - textRect.height();
        }

        path.reset();
        path.moveTo(xLeftOffset, 0);
        path.lineTo(xLeftOffset, chartAreaHeight - yOffset * 2);
        path.lineTo(getWidth() - xRightOffset, chartAreaHeight - yOffset * 2);

        canvas.drawPath(path, pathPaint);

        /**********************************************/


        drawLineChart(canvas);
//        drawHistogramChart(canvas);
        /**********************************************/

//        pathPaint.setStyle(Paint.Style.FILL);
//        pathPaint.setShader(linearGradient);
//        canvas.drawPath(path, pathPaint);

        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setShader(null);
        canvas.drawPath(strokePath, pathPaint);

//        pathPaint.setColor(Color.RED);
//        pathPaint.setStrokeWidth(2);
//        canvas.drawLine(0, getLineChartValueY(1100), getWidth(), getLineChartValueY(1100), pathPaint);
//        canvas.drawLine(0, getLineChartValueY(1600), getWidth(), getLineChartValueY(1600), pathPaint);
//        canvas.drawLine(0, getLineChartValueY(2300), getWidth(), getLineChartValueY(2300), pathPaint);

        float xAxisWidth = (chartAreaWidth) / (xAxisData.size() - 1);
        for (int i = 0; i < xAxisData.size(); i++) {

            canvas.drawText(xAxisData.get(i), xAxisWidth * i + xLeftOffset, getHeight() - yOffset, textPaint);
        }

//        pathPaint.setPathEffect(pathEffect);
        textPaint.setTextAlign(Paint.Align.RIGHT);
        float yAxisWidth = chartAreaHeight / yAxisData.size();
        for (int i = 0; i < yAxisData.size(); i++) {
            canvas.drawText(yAxisData.get(i), xLeftOffset - xTextRightOffset, getHeight() - (yAxisWidth * (i + 1)) + xTextRightOffset, textPaint);
//            canvas.drawLine(xLeftOffset, getHeight() - (yAxisWidth * (i + 1)), getWidth() - xRightOffset, getHeight() - (yAxisWidth * (i + 1)), pathPaint);
        }
//        pathPaint.setPathEffect(null);

    }

    /**
     * 折线图
     * @param canvas
     */
    private void drawLineChart(Canvas canvas) {
        PointF prePointF = pointFS.get(0);
        path.reset();
        path.moveTo(getLineChartValueX(prePointF.x), getLineChartValueY(prePointF.y));
        for (int i = 1; i < pointFS.size(); i++) {
            PointF curPointF = pointFS.get(i);
            float middlePointX = (curPointF.x - prePointF.x) / 2.0f;
            path.cubicTo(getLineChartValueX(prePointF.x + middlePointX),
                    getLineChartValueY(prePointF.y),
                    getLineChartValueX(prePointF.x + middlePointX),
                    getLineChartValueY(curPointF.y),
                    getLineChartValueX(curPointF.x),
                    getLineChartValueY(curPointF.y));

            prePointF = curPointF;
        }

        strokePath.reset();
        strokePath.set(path);

        path.lineTo(0, getLineChartValueY(2300));
        path.close();
    }

    private void drawHistogramChart(Canvas canvas) {
        pathPaint.setStrokeWidth(40);
        for (int i = 1; i < pointFS.size(); i++) {
            PointF curPointF = pointFS.get(i);
            canvas.drawLine(getLineChartValueX(curPointF.x), getLineChartValueY(curPointF.y), getLineChartValueX(curPointF.x), chartAreaHeight - yOffset * 2, pathPaint);
        }
        pathPaint.setStrokeWidth(4);
    }

    private float getLineChartValueX(float realValue) {
        return realValue / LINE_CHART_MAX_X * chartAreaWidth + xLeftOffset;
    }

    private float getLineChartValueY(float realValue) {
        realValue -= LINE_CHART_MIN_Y;
        return realValue / (LINE_CHART_MAX_Y - LINE_CHART_MIN_Y) * chartAreaHeight + yOffset;
    }
}
