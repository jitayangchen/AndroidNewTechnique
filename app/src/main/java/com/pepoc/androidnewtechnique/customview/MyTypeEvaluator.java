package com.pepoc.androidnewtechnique.customview;

import android.animation.TypeEvaluator;

import com.pepoc.androidnewtechnique.log.LogManager;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/12 13:26
 * @copyright TCL-MIG
 */
public class MyTypeEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        LogManager.i("fraction === " + fraction * 2);
        LogManager.i("startValue === " + startValue);
        LogManager.i("endValue === " + endValue);
        return fraction * 2;
    }
}
