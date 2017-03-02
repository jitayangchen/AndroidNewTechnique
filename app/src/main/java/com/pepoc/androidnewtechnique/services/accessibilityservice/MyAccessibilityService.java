package com.pepoc.androidnewtechnique.services.accessibilityservice;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

/**
 * @author Arlenyang
 * @Description:
 * @date 2017/2/21 18:04
 * @copyright TCL-MIG
 */
public class MyAccessibilityService extends AccessibilityService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }
}
