package com.pepoc.androidnewtechnique.customview;

import android.view.animation.Interpolator;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/12 13:18
 * @copyright TCL-MIG
 */
public class MyInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
//        LogManager.i("input === " + input);
        return input * 2;
    }
}
