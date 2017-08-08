package com.pepoc.androidnewtechnique.leakcanary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.TApplication;
import com.pepoc.androidnewtechnique.log.LogManager;

public class LeakCanaryTestActivity extends AppCompatActivity {

    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary_test);

        str = TApplication.CONFIG;
        LogManager.i(str);

//        TApplication.contextList.add(this);
//        LogManager.i("Context count === " + TApplication.contextList.size());

    }
}
