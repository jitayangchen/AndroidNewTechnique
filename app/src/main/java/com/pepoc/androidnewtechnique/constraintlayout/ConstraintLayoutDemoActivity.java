package com.pepoc.androidnewtechnique.constraintlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pepoc.androidnewtechnique.R;

public class ConstraintLayoutDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_constraint_layout_demo);
//
//        final View constraintGroup = findViewById(R.id.constraint_group);
//
//        findViewById(R.id.button_a).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                constraintGroup.setVisibility(View.GONE);
//            }
//        });
//
//        findViewById(R.id.button_c).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                constraintGroup.setVisibility(View.VISIBLE);
//            }
//        });

        setContentView(R.layout.constraint_layout_test);
    }
}
