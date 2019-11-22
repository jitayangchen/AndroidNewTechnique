package com.pepoc.androidnewtechnique.customview.moment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


public class AnnulusView extends View {

    private Paint paint;
    private int mWidth, mHeight;
    private Path path;
    private RectF rectFOuter, rectFInner;
    private int paddingSize = 50;
    private int innerRingMargin = 100;

    public AnnulusView(Context context) {
        super(context);

        init();
    }

    public AnnulusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnnulusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mWidth = 700;
        mHeight = 700;

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);
        paint.setStrokeCap(Paint.Cap.ROUND);

        path = new Path();
        rectFOuter = new RectF();
        rectFInner = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int size = 0;

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            size = Math.min(Math.min(widthSize, mWidth), Math.min(heightSize, mHeight));
        } else if (widthMode == MeasureSpec.AT_MOST) {
            size = Math.min(Math.min(widthSize, mWidth), heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            size = Math.min(widthSize, Math.min(heightSize, mHeight));
        }

        if (size != 0) {
            setMeasuredDimension(size, size);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        rectFOuter.set(paddingSize, paddingSize, getWidth() - paddingSize, getHeight() - paddingSize);
        rectFInner.set(paddingSize + innerRingMargin, paddingSize + innerRingMargin, getWidth() - paddingSize - innerRingMargin, getHeight() - paddingSize - innerRingMargin);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.reset();
        path.addArc(rectFOuter, 0, 270);
        canvas.drawPath(path, paint);
//        canvas.drawArc(300, 300, 1000, 1000, 0, 270, false, paint);

        paint.setColor(Color.BLUE);
        path.reset();
        path.addArc(rectFOuter, 0, 145);
        canvas.drawPath(path, paint);
//        canvas.drawArc(300, 300, 1000, 1000, 0, 145, false, paint);

        paint.setColor(Color.GREEN);
        path.reset();
        path.addArc(rectFInner, 0, 90);
        canvas.drawPath(path, paint);
//        canvas.drawArc(400, 400, 900, 900, 0, 90, false, paint);

        paint.setColor(Color.BLACK);
        path.reset();
        path.addArc(rectFInner, 180, 180);
        canvas.drawPath(path, paint);
//        canvas.drawArc(400, 400, 900, 900, 180, 180, false, paint);

    }
}
