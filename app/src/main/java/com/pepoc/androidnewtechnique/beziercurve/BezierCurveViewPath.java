package com.pepoc.androidnewtechnique.beziercurve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/24 17:19
 * @copyright TCL-MIG
 */
public class BezierCurveViewPath extends View {

    private Paint paintBezierCurve;
    private Paint paintLine;
    private Path pathBezierCurve;
    private Path pathLine;
    private float startX;
    private float startY;
    private float moveX;
    private float moveY;

    private RectF firstPoint;
    private RectF secondPoint;
    private RectF startPoint;
    private RectF endPoint;

    private RectF currentPoint;
    private int rectSize = 100;

    private PointUpdateListener pointUpdateListener;

    public BezierCurveViewPath(Context context) {
        super(context);

        init();
    }

    public BezierCurveViewPath(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public BezierCurveViewPath(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        startPoint = new RectF(0, getMeasuredHeight() - rectSize, rectSize, getMeasuredHeight());
        endPoint = new RectF(getMeasuredWidth() - rectSize, getMeasuredHeight() - getMeasuredWidth() - rectSize, getMeasuredWidth(), getMeasuredHeight() - getMeasuredWidth());




        firstPoint.left = getMeasuredWidth() * 0.42f;
        firstPoint.top = getMeasuredHeight() - (getMeasuredWidth() * 0.1f);
        firstPoint.right = firstPoint.left + rectSize;
        firstPoint.bottom = firstPoint.top + rectSize;

        secondPoint.left = getMeasuredWidth() * 0.58f;
        secondPoint.top = getMeasuredHeight() - getMeasuredWidth();
        secondPoint.right = secondPoint.left + rectSize;
        secondPoint.bottom = secondPoint.top + rectSize;
    }

    private void init() {
        paintBezierCurve = new Paint();
        paintBezierCurve.setStyle(Paint.Style.FILL);
        paintBezierCurve.setAntiAlias(true);
        paintBezierCurve.setColor(Color.GREEN);
        paintBezierCurve.setStrokeWidth(5);

        paintLine = new Paint();
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setAntiAlias(true);
        paintLine.setColor(Color.BLUE);
        paintLine.setStrokeWidth(2);

        pathBezierCurve = new Path();
        pathLine = new Path();

        firstPoint = new RectF();
        secondPoint = new RectF();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                currentPoint = getRectFScope(startX, startY);
                break;
            case MotionEvent.ACTION_MOVE:
                if (currentPoint != null) {
                    moveX = event.getX();
                    moveY = event.getY();

                    if (currentPoint == startPoint || currentPoint == endPoint) {
                        moveRectF(currentPoint, currentPoint.left, moveY);
                    } else {
                        moveRectF(currentPoint, moveX, moveY);
                    }

                    if (pointUpdateListener != null) {
                        float[] firstPoint = getFirstPoint();
                        float[] secondPoint = getSecondPoint();
                        int width = getMeasuredWidth();
                        pointUpdateListener.updatePointInfo("(" + String.format("%1.2f", firstPoint[0]/width) + ", " + String.format("%1.2f", firstPoint[1]/width) + ") ("+ String.format("%1.2f", secondPoint[0]/width) + ", " + String.format("%1.2f", secondPoint[1]/width) + ")");
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        pathLine.reset();
        pathBezierCurve.reset();
        pathBezierCurve.moveTo(startPoint.left, startPoint.bottom);
        pathBezierCurve.cubicTo(firstPoint.left + rectSize / 2, firstPoint.top + rectSize / 2, secondPoint.left + rectSize / 2, secondPoint.top + rectSize / 2, endPoint.right, endPoint.bottom);
        pathBezierCurve.lineTo(getWidth(), getHeight());
        pathBezierCurve.lineTo(0, getHeight());
        pathBezierCurve.close();
        canvas.drawPath(pathBezierCurve, paintBezierCurve);

        canvas.drawRect(firstPoint, paintLine);
        canvas.drawRect(secondPoint, paintLine);
//        canvas.drawRect(startPoint, paintLine);
//        canvas.drawRect(endPoint, paintLine);
        pathLine.moveTo(startPoint.left, startPoint.bottom);
        pathLine.lineTo(firstPoint.left + rectSize / 2, firstPoint.top + rectSize / 2);
        pathLine.lineTo(secondPoint.left + rectSize / 2, secondPoint.top + rectSize / 2);
        pathLine.lineTo(endPoint.right, endPoint.bottom);
        canvas.drawPath(pathLine, paintLine);
    }

    private RectF getRectFScope(float x, float y) {
        if (x >= firstPoint.left && x <= firstPoint.right && y >= firstPoint.top && y <= firstPoint.bottom) {
            return firstPoint;
        } else if (x >= secondPoint.left && x <= secondPoint.right && y >= secondPoint.top && y <= secondPoint.bottom) {
            return secondPoint;
        } else if (x >= startPoint.left && x <= startPoint.right && y >= startPoint.top && y <= startPoint.bottom) {
            return startPoint;
        } else if (x >= endPoint.left && x <= endPoint.right && y >= endPoint.top && y <= endPoint.bottom) {
            return endPoint;
        }
        return null;
    }

    private void moveRectF(RectF currentPoint, float x, float y) {
        currentPoint.left = x;
        currentPoint.top = y;
        currentPoint.right = x + rectSize;
        currentPoint.bottom = y + rectSize;
    }

    public float[] getStartPoint() {
        float[] point = new float[2];
        float startLeft = startPoint.left;
        float startBottom = getHeight() - startPoint.top;
        point[0] = startLeft;
        point[1] = startBottom;
        return point;
    }

    public float[] getFirstPoint() {
        float[] point = new float[2];
        float firstLeft = firstPoint.left + rectSize / 2;
        float firstBottom = getHeight() - (firstPoint.top + rectSize / 2);
        point[0] = firstLeft;
        point[1] = firstBottom;
        return point;
    }

    public float[] getSecondPoint() {
        float[] point = new float[2];
        float secondLeft = secondPoint.left + rectSize / 2;
        float secondBottom = getHeight() - (secondPoint.top + rectSize / 2);
        point[0] = secondLeft;
        point[1] = secondBottom;
        return point;
    }

    public float[] getEndPoint() {
        float[] point = new float[2];
        float endLeft = endPoint.right;
        float endBottom = getHeight() - endPoint.top;
        point[0] = endLeft;
        point[1] = endBottom;
        return point;
    }

    interface PointUpdateListener {
        void updatePointInfo(String info);
    }

    public void setPointUpdateListener(PointUpdateListener pointUpdateListener) {
        this.pointUpdateListener = pointUpdateListener;
    }
}
