package com.pepoc.androidnewtechnique.java;

import android.os.Bundle;
import android.support.annotation.Size;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.TApplication;
import com.pepoc.androidnewtechnique.log.LogManager;

public class JavaBaseActivity extends AppCompatActivity {

    private String str = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_base);

        final String temp;
        temp = str;

        findViewById(R.id.btn_text_java).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                str = "HAHAHA";
//                LogManager.i("temp === " + temp);

                LogManager.i("CONFIG = " + TApplication.CONFIG);
            }
        });

        test(new String[]{"ddd"});
    }

    private void test(@Size(2) String[] str) {
        LogManager.i(str[0]);
    }


}
