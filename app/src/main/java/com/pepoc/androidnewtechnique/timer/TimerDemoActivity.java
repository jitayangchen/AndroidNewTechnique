package com.pepoc.androidnewtechnique.timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

public class TimerDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_demo);


        init();
    }

    private void init() {
        View btnCountdowntimerTest = findViewById(R.id.btn_countdowntimer_test);

        btnCountdowntimerTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDownTimer countDownTimer = new CountDownTimer(300 * 10, 300) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        LogManager.i("onTick ------ millisUntilFinished = " + millisUntilFinished);
                    }

                    @Override
                    public void onFinish() {
                        LogManager.i("===========onFinish=============");
                    }
                };
                countDownTimer.start();
            }
        });
    }
}
