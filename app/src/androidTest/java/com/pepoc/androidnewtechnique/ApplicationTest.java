package com.pepoc.androidnewtechnique;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.pepoc.androidnewtechnique.log.LogManager;
import com.pepoc.androidnewtechnique.util.Util;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest  {
    @Test
    public void ApplicationTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int i = Util.px2dp(appContext, 120);
        LogManager.i("i === " + i);
    }
}