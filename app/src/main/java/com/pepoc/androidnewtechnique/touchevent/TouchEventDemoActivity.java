package com.pepoc.androidnewtechnique.touchevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

public class TouchEventDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event_demo);

        Thread.dumpStack();
    }

    public void lalala(View view) {
        LogManager.i("lalala");
    }

    public void hahaha(View view) {
        LogManager.i("hahaha");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        LogManager.i("-------------Activity onTouchEvent--------------");
        return super.onTouchEvent(event);
    }
}
