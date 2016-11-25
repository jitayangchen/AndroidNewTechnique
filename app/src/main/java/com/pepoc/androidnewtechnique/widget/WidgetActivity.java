package com.pepoc.androidnewtechnique.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.pepoc.androidnewtechnique.R;

public class WidgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }
}
