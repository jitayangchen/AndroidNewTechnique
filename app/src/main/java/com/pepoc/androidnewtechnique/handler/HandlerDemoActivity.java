package com.pepoc.androidnewtechnique.handler;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

public class HandlerDemoActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_demo);

//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                LogManager.i("--------HAHAHA-------");
//            }
//        });
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 1; i < 1000; i++) {
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    LogManager.i("--------LALALA-------");
//                }
//            }
//        }, 10000);

        init();
    }

    private void init() {
        btnTest = (Button) findViewById(R.id.btn_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtonText();
            }
        });
    }

    private void updateButtonText() {
        new Thread() {
            @Override
            public void run() {
                super.run();
//                btnTest.setText("YYYYYYYYYY");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        btnTest.setText("YYYYYYYYYY");
                        LogManager.i("--------YYYYYYYYYY-------");
                    }
                });
            }
        }.start();
    }
}
