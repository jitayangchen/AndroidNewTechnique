package com.pepoc.androidnewtechnique.beziercurve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/9 10:50
 * @copyright TCL-MIG
 */
public class WaterRipple extends View {

    private Paint paintBezierCurve;
    private Path pathBezierCurve1;
    private Path pathBezierCurve2;
    private Path pathBezierCurve3;

    private final static PointD startPoint1 = new PointD(0.0d, 0.21135995d);
    private final static PointD firstPoint1 = new PointD(0.37730196d, 0.3372543d);
    private final static PointD secondPoint1 = new PointD(0.63527703d, 0.15144902d);
    private final static PointD endPoint1 = new PointD(1.0d, 0.12671529d);

//    startPoint = 0.0 --- 0.25471446139753356
//    firstPoint = 0.3412409464518229 --- 0.30636735395951703
//    secondPoint = 0.5881203121609158 --- -0.11479123958871384
//    endPoint = 1.0 --- 0.040006148913675106
    private final static PointD startPoint2 = new PointD(0.0d, 0.25471446139753356d);
    private final static PointD firstPoint2 = new PointD(0.3412409464518229d, 0.30636735395951703d);
    private final static PointD secondPoint2 = new PointD(0.5881203121609158d, -0.11479123958871384d);
    private final static PointD endPoint2 = new PointD(1.0d, 0.040006148913675106d);

//    startPoint = 0.0 --- 0.24645643
//    firstPoint = 0.4105891 --- -0.13345236
//    secondPoint = 0.6713381 --- 0.30835125
//    endPoint = 1.0 --- 0.35793966
    private final static PointD startPoint3 = new PointD(0.0d, 0.24645643d);
    private final static PointD firstPoint3 = new PointD(0.4105891d, -0.13345236d);
    private final static PointD secondPoint3 = new PointD(0.6713381d, 0.30835125d);
    private final static PointD endPoint3 = new PointD(1.0d, 0.35793966d);

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
        paintBezierCurve = new Paint();
        paintBezierCurve.setStyle(Paint.Style.FILL);
        paintBezierCurve.setAntiAlias(true);

        pathBezierCurve1 = new Path();
        pathBezierCurve2 = new Path();
        pathBezierCurve3 = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        pathBezierCurve1.moveTo((float) (startPoint1.x * getWidth()), (float) (startPoint1.y * getHeight()));
        pathBezierCurve1.cubicTo((float) (firstPoint1.x * getWidth()), (float) (firstPoint1.y * getHeight()), (float) (secondPoint1.x * getWidth()), (float) (secondPoint1.y * getHeight()), (float) (endPoint1.x * getWidth()), (float) (endPoint1.y * getHeight()));
        pathBezierCurve1.lineTo(getWidth(), getHeight());
        pathBezierCurve1.lineTo(0, getHeight());
        pathBezierCurve1.close();
        paintBezierCurve.setColor(Color.parseColor("#332c48ff"));
        canvas.drawPath(pathBezierCurve1, paintBezierCurve);

        pathBezierCurve2.moveTo((float) (startPoint2.x * getWidth()), (float) (startPoint2.y * getHeight()));
        pathBezierCurve2.cubicTo((float) (firstPoint2.x * getWidth()), (float) (firstPoint2.y * getHeight()), (float) (secondPoint2.x * getWidth()), (float) (secondPoint2.y * getHeight()), (float) (endPoint2.x * getWidth()), (float) (endPoint2.y * getHeight()));
        pathBezierCurve2.lineTo(getWidth(), getHeight());
        pathBezierCurve2.lineTo(0, getHeight());
        pathBezierCurve2.close();
        paintBezierCurve.setColor(Color.parseColor("#33125eff"));
        canvas.drawPath(pathBezierCurve2, paintBezierCurve);

        pathBezierCurve3.moveTo((float) (startPoint3.x * getWidth()), (float) (startPoint3.y * getHeight()));
        pathBezierCurve3.cubicTo((float) (firstPoint3.x * getWidth()), (float) (firstPoint3.y * getHeight()), (float) (secondPoint3.x * getWidth()), (float) (secondPoint3.y * getHeight()), (float) (endPoint3.x * getWidth()), (float) (endPoint3.y * getHeight()));
        pathBezierCurve3.lineTo(getWidth(), getHeight());
        pathBezierCurve3.lineTo(0, getHeight());
        pathBezierCurve3.close();
        paintBezierCurve.setColor(Color.parseColor("#333954ff"));
        canvas.drawPath(pathBezierCurve3, paintBezierCurve);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setLayerType(View.LAYER_TYPE_HARDWARE,null);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setLayerType(View.LAYER_TYPE_NONE, null);
    }
}
