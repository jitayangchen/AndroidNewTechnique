package com.pepoc.androidnewtechnique.ocr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pepoc.androidnewtechnique.R;

public class OcrDemoActivity extends AppCompatActivity {

    private static final String TESSBASE_PATH = Environment.getExternalStorageDirectory().getPath();
    private static final String DEFAULT_LANGUAGE = "chi_sim";
    private ImageView ivTestOcr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr_demo);

        init();
    }

    private void init() {
//        final TessBaseAPI tessBaseAPI = new TessBaseAPI();
//        tessBaseAPI.init(TESSBASE_PATH, DEFAULT_LANGUAGE);
//        tessBaseAPI.setPageSegMode(TessBaseAPI.PageSegMode.PSM_SINGLE_LINE);


        ivTestOcr = (ImageView) findViewById(R.id.iv_test_ocr);
        final TextView tvOcrResult = (TextView) findViewById(R.id.tv_ocr_result);
        ivTestOcr.setDrawingCacheEnabled(true);

        findViewById(R.id.btn_start_ocr_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Bitmap bmp = ivTestOcr.getDrawingCache();
//
//                tessBaseAPI.setImage(bmp);
//
//                tvOcrResult.setText(tessBaseAPI.getUTF8Text());
//
//                tessBaseAPI.clear();
//
//                tessBaseAPI.end();


                startActivity(new Intent(OcrDemoActivity.this, InterceptRectViewActivity.class));
            }
        });
    }
}
