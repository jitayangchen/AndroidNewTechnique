package com.pepoc.androidnewtechnique.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity {

//    @Bind(R.id.toolbar)
//    Toolbar toolbar;
//    @Bind(R.id.btn_android_eventbus)
//    Button btnAndroidEventbus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
//        ButterKnife.bind(this);

        init();
    }

    private void init() {
//        setSupportActionBar(toolbar);
//
        EventBus.getDefault().register(this);
//
//        RxView.clicks(btnAndroidEventbus).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//                EventBus.getDefault().post("HAHAHA HEIHEIHEI");
//            }
//        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    private void lalala(Object object) {
        LogManager.i("GAGAGA");
    }

}
