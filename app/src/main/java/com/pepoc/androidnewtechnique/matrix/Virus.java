package com.pepoc.androidnewtechnique.matrix;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/26 11:12
 * @copyright TCL-MIG
 */
public class Virus {

    private Bitmap virusBitmap;
    private float parentViewWidth;
    private float parentViewHeight;
    private float parentViewLeft;
    private float parentViewTop;
    private float scale;
    private float leftPercent;
    private float topPercent;
    private float degrees;

    public Virus() {

    }

    public Virus(Bitmap virusBitmap, float parentViewWidth, float parentViewHeight, float parentViewLeft, float parentViewTop, float scale, float leftPercent, float topPercent, float degrees) {
        this.virusBitmap = virusBitmap;
        this.parentViewWidth = parentViewWidth;
        this.parentViewHeight = parentViewHeight;
        this.parentViewLeft = parentViewLeft;
        this.parentViewTop = parentViewTop;
        this.scale = scale;
        this.leftPercent = leftPercent;
        this.topPercent = topPercent;
        this.degrees = degrees;
    }

    public void setVirusBitmap(Bitmap virusBitmap) {
        this.virusBitmap = virusBitmap;
    }

    public void setParentViewWidth(float parentViewWidth) {
        this.parentViewWidth = parentViewWidth;
    }

    public void setParentViewHeight(float parentViewHeight) {
        this.parentViewHeight = parentViewHeight;
    }

    public void setParentViewLeft(float parentViewLeft) {
        this.parentViewLeft = parentViewLeft;
    }

    public void setParentViewTop(float parentViewTop) {
        this.parentViewTop = parentViewTop;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setLeftPercent(float leftPercent) {
        this.leftPercent = leftPercent;
    }

    public void setTopPercent(float topPercent) {
        this.topPercent = topPercent;
    }

    public void setDegrees(float degrees) {
        this.degrees = degrees;
    }

    public void onDraw(Canvas canvas) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        matrix.postRotate(degrees, (virusBitmap.getWidth() * scale) / 2.0f, (virusBitmap.getHeight() * scale) / 2.0f);
        float x = parentViewLeft + parentViewWidth * leftPercent;
        float y = parentViewTop + parentViewHeight * topPercent;
        matrix.postTranslate(x, y);
        canvas.drawBitmap(virusBitmap, matrix, null);
    }

    public void destroy() {

    }
}
