package com.pepoc.androidnewtechnique.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.pepoc.androidnewtechnique.R;

public class ServiceDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
    }

    private void init() {
        findViewById(R.id.btn_service_dmeo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDemoActivity.this, ServicesDemo.class);
                startService(intent);
            }
        });

        findViewById(R.id.btn_intent_service_dmeo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDemoActivity.this, IntentServiceDemo.class);
                startService(intent);
            }
        });
    }

}
