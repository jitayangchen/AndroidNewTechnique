package com.pepoc.androidnewtechnique.rxbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.pepoc.androidnewtechnique.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RxBusActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btn_test)
    Button btnTest;
    private RxBus rxBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);
        ButterKnife.bind(this);

        init();

    }

    private void init() {
        setSupportActionBar(toolbar);

        rxBus = new RxBus();

        RxView.clicks(btnTest).subscribe(aVoid -> rxBus.send("Yangchen"));

        rxBus.toObserverable().subscribe(o -> {
            Toast.makeText(RxBusActivity.this, o.toString(), Toast.LENGTH_SHORT).show();
        });

    }

}
