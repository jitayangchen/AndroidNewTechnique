package com.pepoc.androidnewtechnique.customview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.pepoc.androidnewtechnique.R;

public class CanvasDemoActivity extends AppCompatActivity {

    private AntivirusScanView canvasSimple;
    private Handler handler = new Handler();
    private AppCompatSeekBar seekBar1;
    private AppCompatSeekBar seekBar2;
    private AppCompatSeekBar seekBar3;
    private AppCompatSeekBar seekBar4;
    private TextView seekBarValue1;
    private TextView seekBarValue2;
    private TextView seekBarValue3;
    private TextView seekBarValue4;
    private BgWaterRipple waterRipple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_demo);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                canvasSimple.startAnimation();
//            }
//        }, 200);
    }

    @Override
    protected void onPause() {
        super.onPause();

        canvasSimple.cancelAnimation();
    }

    private void init() {
        waterRipple = (BgWaterRipple) findViewById(R.id.water_ripple);
        canvasSimple = (AntivirusScanView) findViewById(R.id.canvas_simple);
        seekBar1 = (AppCompatSeekBar) findViewById(R.id.seek_bar_1);
        seekBar2 = (AppCompatSeekBar) findViewById(R.id.seek_bar_2);
        seekBar3 = (AppCompatSeekBar) findViewById(R.id.seek_bar_3);
        seekBar4 = (AppCompatSeekBar) findViewById(R.id.seek_bar_4);

        seekBarValue1 = (TextView) findViewById(R.id.seek_bar_value_1);
        seekBarValue2 = (TextView) findViewById(R.id.seek_bar_value_2);
        seekBarValue3 = (TextView) findViewById(R.id.seek_bar_value_3);
        seekBarValue4 = (TextView) findViewById(R.id.seek_bar_value_4);


        final ImageView ivIcLauncher = (ImageView) findViewById(R.id.iv_ic_launcher);

        final TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, 100);
        translateAnimation.setDuration(800);
        translateAnimation.setFillAfter(true);

        final Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(800);
        alphaAnimation.setFillAfter(true);


        final AnimationSet set = new AnimationSet(true);
        set.addAnimation(translateAnimation);
        set.addAnimation(alphaAnimation);
        set.setFillAfter(true);



        final TranslateAnimation translateAnimation1 = new TranslateAnimation(0, 0, 100, 200);
        translateAnimation1.setDuration(800);
        translateAnimation1.setFillAfter(true);

        final Animation alphaAnimation1 = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation1.setDuration(800);
        alphaAnimation1.setFillAfter(true);

        final AnimationSet set1 = new AnimationSet(true);
        set1.setFillAfter(true);
        set1.addAnimation(translateAnimation1);
        set1.addAnimation(alphaAnimation1);


        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivIcLauncher.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ivIcLauncher.startAnimation(set1);
                        ivIcLauncher.setVisibility(View.INVISIBLE);

                    }
                }, 200);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivIcLauncher.startAnimation(set);
                ivIcLauncher.setVisibility(View.VISIBLE);
            }
        });


        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivIcLauncher.startAnimation(translateAnimation1);
            }
        });


        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue1.setText(String.valueOf(progress));
                waterRipple.setA(progress);
                waterRipple.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue2.setText(String.valueOf(0.04f * progress));
                waterRipple.setWtemp(0.04f * progress);
                waterRipple.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue3.setText(String.valueOf(progress * 0.1 * -1));
                waterRipple.setB(progress * 0.1 * -1);
                waterRipple.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue4.setText(String.valueOf(progress - 150.0d));
                waterRipple.setH(progress - 150.0d);
                waterRipple.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
