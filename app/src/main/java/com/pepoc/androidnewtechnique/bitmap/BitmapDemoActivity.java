package com.pepoc.androidnewtechnique.bitmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;
import com.pepoc.androidnewtechnique.util.Util;

public class BitmapDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_demo);

        findViewById(R.id.btn_px2dp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogManager.i("width = " + Util.px2dp(BitmapDemoActivity.this, 461 /  2));
                LogManager.i("height = " + Util.px2dp(BitmapDemoActivity.this, 510 / 2));
            }
        });
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }
}
