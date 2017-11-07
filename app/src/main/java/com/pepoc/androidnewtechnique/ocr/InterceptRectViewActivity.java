package com.pepoc.androidnewtechnique.ocr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;
import com.pepoc.androidnewtechnique.util.Util;

public class InterceptRectViewActivity extends AppCompatActivity {

    private MediaProjectionManager mMediaProjectionManager;

    private ScreenCapture screenCapture;
    private InterceptRectView interceptRectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intercept_rect_view);

        int screenWidth = Util.getScreenWidth(this);
        int screenHeight = Util.getScreenHeight(this);

        LogManager.i("screenWidth = " + screenWidth);
        LogManager.i("screenHeight = " + screenHeight);

        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        LogManager.i("screenWidth = " + width);
        LogManager.i("screenHeight = " + height);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mMediaProjectionManager = (MediaProjectionManager) getApplication().getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        }

        init();
    }

    private void init() {

        interceptRectView = (InterceptRectView) findViewById(R.id.intercept_rect_view);

        screenCapture = new ScreenCapture(this);

        screenCapture.createImageReader();

        findViewById(R.id.btn_start_capture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    interceptRectView.setVisibility(View.GONE);
                    startActivityForResult(mMediaProjectionManager.createScreenCaptureIntent(), 1000);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        interceptRectView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            screenCapture.toCapture(data, mMediaProjectionManager, interceptRectView.getRect());
        }
    }
}
