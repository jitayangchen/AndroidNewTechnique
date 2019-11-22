package com.pepoc.androidnewtechnique.customview.moment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SleepTimeView extends View {

    private Paint paint;
    private RectF rectF;
    private Path path;

    public SleepTimeView(Context context) {
        super(context);

        init();
    }

    public SleepTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SleepTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(6);


        rectF = new RectF();

        path = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 根据三个不在一条直线上的点确定一个圆，更好的方式是用贝塞尔曲线做，弧线
         */

        rectF.set(100, 500, getRight() - 100, 800);

        float a = rectF.width();
        float b = rectF.height();
        float c = (float) Math.sqrt(a * a / 4 + b * b);

        b = c;

        float r = (float) ((a * b * c) / Math.sqrt((a + b + c) * (a + b - c) * (a - b + c) * (b + c - a)));

        float p = (a + b + c) / 2.0f;
        float r2 = (float) ((a * b * c) / (4 * Math.sqrt(p * (p - a) * ( p - b) * (p - c))));

        double asin = Math.asin((r - b) / r);
        Log.i("Yangchen", "asin = " + Math.toDegrees(asin));
        Log.i("Yangchen","r = " + r + " - r2 = " + r2);

        canvas.save();
        canvas.clipRect(rectF.left, rectF.top - 10, rectF.right, rectF.bottom);
//        canvas.drawRect(rectF, paint);
//        canvas.drawArc(100, 500, getRight() - 100, 1000, -0, -180, false, paint);
        path.addCircle(getWidth() / 2.0f, 500 + r, r, Path.Direction.CW);
        canvas.drawPath(path, paint);
//        canvas.drawCircle(getWidth() / 2.0f, 500 + r, r, paint);
        canvas.restore();

        canvas.drawLine(rectF.left - 24, rectF.bottom, rectF.left + 30, rectF.bottom, paint);
        canvas.drawLine(rectF.right - 30, rectF.bottom, rectF.right + 24, rectF.bottom, paint);

    }
}
