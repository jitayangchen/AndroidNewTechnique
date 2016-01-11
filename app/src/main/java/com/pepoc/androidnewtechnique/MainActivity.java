package com.pepoc.androidnewtechnique;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.pepoc.androidnewtechnique.eventbus.EventBusActivity;
import com.pepoc.androidnewtechnique.realm.RealmActivity;
import com.pepoc.androidnewtechnique.rxbinding.RxBindingActivity;
import com.pepoc.androidnewtechnique.rxbus.RxBusActivity;
import com.pepoc.androidnewtechnique.rxjava.RxJavaActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ll_main_content)
    LinearLayout llMainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        setSupportActionBar(toolbar);
        List<Class<? extends Activity>> classList = getData();

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Observable.from(classList).subscribe(clazz -> {
            Button button = new Button(MainActivity.this);
            button.setLayoutParams(params);
            button.setText(clazz.getSimpleName());
            llMainContent.addView(button);
            RxView.clicks(button).subscribe(aVoid -> {startActivity(new Intent(MainActivity.this, clazz));});
        });
    }

    private List<Class<? extends Activity>> getData() {
        List<Class<? extends Activity>> classList = new ArrayList<>();
        classList.add(EventBusActivity.class);
        classList.add(RealmActivity.class);
        classList.add(RxBindingActivity.class);
        classList.add(RxBusActivity.class);
        classList.add(RxJavaActivity.class);
        return classList;
    }

}
