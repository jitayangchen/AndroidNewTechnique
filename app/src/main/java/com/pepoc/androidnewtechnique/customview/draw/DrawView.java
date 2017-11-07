package com.pepoc.androidnewtechnique.customview.draw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.pepoc.androidnewtechnique.R;

/**
 * Created by yangchen on 2017/10/27.
 */

public class DrawView extends View {

    private Paint paint;
    private RectF arcRectF;
    private Paint paint1;
    private RectF ovalRectF;
    private int color;

    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.custom);
        color = typedArray.getColor(R.styleable.custom_custom_color, Color.GREEN);
        typedArray.recycle();
        init();
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3);

        paint1 = new Paint(paint);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setColor(color);

        arcRectF = new RectF(200, 500, 700, 1000);
        ovalRectF = new RectF(200, 1100, 700, 1400);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(10, 50, 700, 70, paint);

        canvas.drawCircle(200, 200, 100, paint);

        canvas.drawRect(500, 100, 900, 300, paint);

        canvas.drawRect(arcRectF, paint);
        canvas.drawArc(arcRectF, 0, 220, true, paint1);

        canvas.drawRect(ovalRectF, paint);
        canvas.drawOval(ovalRectF, paint1);
    }
}
