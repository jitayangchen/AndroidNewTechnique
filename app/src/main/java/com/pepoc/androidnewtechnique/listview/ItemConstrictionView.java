package com.pepoc.androidnewtechnique.listview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/14 20:23
 * @copyright TCL-MIG
 */
public class ItemConstrictionView extends ViewGroup {

    public ItemConstrictionView(Context context) {
        super(context);
    }

    public ItemConstrictionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemConstrictionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }
}
