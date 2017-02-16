package com.pepoc.androidnewtechnique.viewstub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.TApplication;
import com.pepoc.androidnewtechnique.log.LogManager;

public class ViewStubDemoActivity extends AppCompatActivity {

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub_demo);

        findViewById(R.id.btn_inflate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (view == null) {
//                    ViewStub viewStub = (ViewStub) findViewById(R.id.view_stub);
//                    LogManager.i("viewStub === " + viewStub);
//                    view = viewStub.inflate();
//                } else {
//                    int status = view.getVisibility() == View.GONE ? View.VISIBLE : View.GONE;
//                    view.setVisibility(status);
//                }

                TApplication.CONFIG = "hahaha";
                LogManager.i("CONFIG = " + TApplication.CONFIG);
            }
        });
    }
}
