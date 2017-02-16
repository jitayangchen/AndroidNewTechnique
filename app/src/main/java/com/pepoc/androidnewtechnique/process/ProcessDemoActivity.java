package com.pepoc.androidnewtechnique.process;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.TApplication;
import com.pepoc.androidnewtechnique.log.LogManager;

public class ProcessDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_demo);
        LogManager.i("Pid = " + android.os.Process.myPid());

        LogManager.i("CONFIG = " + TApplication.CONFIG);

        Intent intent = getIntent();
        String arg = intent.getStringExtra("arg");
        LogManager.i("arg === " + arg);

//        Object<T>[] str = new Object<T>[2];
//        ArrayMap
//        System.arraycopy();
    }

    class Demo<T> {
//        T[] str = new T[4];
//        int[] str = new int[3];
    }
}
