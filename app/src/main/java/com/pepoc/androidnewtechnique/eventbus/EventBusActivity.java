package com.pepoc.androidnewtechnique.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.pepoc.androidnewtechnique.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventBusActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        setSupportActionBar(toolbar);
    }

}
