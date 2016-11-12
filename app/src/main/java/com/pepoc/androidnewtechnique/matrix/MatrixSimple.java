package com.pepoc.androidnewtechnique.matrix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/10 12:00
 * @copyright TCL-MIG
 */
public class MatrixSimple extends View {

    private Matrix matrix = new Matrix();
    private Paint bgPaint = new Paint();

    public MatrixSimple(Context context) {
        super(context);

        init();
    }

    public MatrixSimple(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public MatrixSimple(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        matrix.setScale(3f, 3f);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        bgPaint.setColor(Color.RED);
        canvas.concat(matrix);
        canvas.drawRect(0, 0, 100, 100, bgPaint);

//        canvas.save();
//        canvas.concat(matrix);
        canvas.drawRect(50, 100, 200, 200, bgPaint);
//        canvas.restore();

//        canvas.drawRect(400, 400, 500, 500, bgPaint);
    }
}
