package com.pepoc.androidnewtechnique.customview.safemobile;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.pepoc.androidnewtechnique.log.LogManager;

public class CircleRipple {

    private float defaultRadius = 150;
    private float cx;
    private float cy;
    private long createTime = -1;

    public CircleRipple() {
//        createTime = System.currentTimeMillis();
    }

    public float getDefaultRadius() {
        return defaultRadius;
    }

    public void setDefaultRadius(float defaultRadius) {
        this.defaultRadius = defaultRadius;
    }

    public float getCx() {
        return cx;
    }

    public void setCx(float cx) {
        this.cx = cx;
    }

    public float getCy() {
        return cy;
    }

    public void setCy(float cy) {
        this.cy = cy;
    }

    public void drawCircleRipple(Canvas canvas, Paint paint) {
        if (createTime == -1) {
            createTime = System.currentTimeMillis();
        }
        long time = System.currentTimeMillis() - createTime;
        if (time >= SafeSimpleViewTest.ANIMATED_DURATION) {
            createTime += SafeSimpleViewTest.ANIMATED_DURATION;
            time = System.currentTimeMillis() - createTime;
        }
        float rate = time * 1.0f / SafeSimpleViewTest.ANIMATED_DURATION;
        LogManager.i("rate = " + rate);
        canvas.drawCircle(cx, cy, defaultRadius + rate * 100, paint);
    }
}
