package com.pepoc.androidnewtechnique.activitytask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepoc.androidnewtechnique.R;

public class DTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtest);

        final int a = 10;

        findViewById(R.id.btn_final_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int b = a * a;
//                a = 20;
            }
        });
    }
}
