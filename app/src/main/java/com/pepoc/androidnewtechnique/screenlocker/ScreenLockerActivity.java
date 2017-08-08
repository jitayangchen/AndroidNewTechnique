package com.pepoc.androidnewtechnique.screenlocker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepoc.androidnewtechnique.R;

public class ScreenLockerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_locker);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ScreenLocker.show(ScreenLockerActivity.this);

                ScreenLockService.startService(ScreenLockerActivity.this);
            }
        });
    }
}
