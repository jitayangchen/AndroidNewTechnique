package com.pepoc.androidnewtechnique.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.util.Util;

public class TestView extends View {

    private Bitmap mainLogoBallBitmap = null;
    private Rect rect = null;

    public TestView(Context context) {
        super(context);

        init();
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mainLogoBallBitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.main_splash_logo_ball), null, null);
        rect = new Rect(0, 0, Util.dip2px(getContext(), 200), Util.dip2px(getContext(), 200));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int resultWidthSize;
        int resultHeightSize;

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            resultWidthSize = widthSize;
            resultHeightSize = heightSize;
        } else {
            resultWidthSize = mainLogoBallBitmap.getWidth();
            resultHeightSize = mainLogoBallBitmap.getHeight();
        }
        setMeasuredDimension(resultWidthSize, resultHeightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mainLogoBallBitmap, null, rect, null);
//        canvas.drawBitmap(mainLogoBallBitmap, 0, 0, null);
    }
}
