package com.pepoc.androidnewtechnique.customview.safemobile;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class SafeSimpleView extends View {

    private Paint paint, paint2;
    private static final float SCAN_ARC_ANGLE = 120f;//扫描扇形的角度
    private int[] mFanColors = {Color.RED, Color.TRANSPARENT};
    private float[] mPositions = {0, SCAN_ARC_ANGLE / 360f, 1.0f};


    public SafeSimpleView(Context context) {
        super(context);

        init();
    }

    public SafeSimpleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SafeSimpleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        SweepGradient sweepGradient = new SweepGradient(180, 180, mFanColors, null);
        SweepGradient sweepGradient2 = new SweepGradient(180, 540, Color.RED, Color.TRANSPARENT);

        paint2 = new Paint(paint);
        paint.setShader(sweepGradient);
        paint2.setShader(sweepGradient2);

//        paint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(0, 0, 360, 360, 290, 90, true, paint);
//        canvas.drawArc(0, 360, 360, 720, 90, 80, true, paint2);

        canvas.drawCircle(180, 540, 180, paint2);
    }
}
