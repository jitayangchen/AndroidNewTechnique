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
        if (input < 0.5f) {
            return ((float) (Math.cos((input * 2 + 1) * Math.PI) / 2.0f) + 0.5f) / 2.0f;
        } else {
            return ((float) (Math.cos(((1 - input) * 2 + 1) * Math.PI) / 2.0f) + 0.5f) / 2.0f;
        }
    }
}
