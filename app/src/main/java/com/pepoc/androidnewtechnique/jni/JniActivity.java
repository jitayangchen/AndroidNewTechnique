package com.pepoc.androidnewtechnique.jni;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.pepoc.androidnewtechnique.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class JniActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btn_jni_test)
    Button btnJniTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        setSupportActionBar(toolbar);
        RxView.clicks(btnJniTest).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Toast.makeText(JniActivity.this, helloJni(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    static {
        System.loadLibrary("hello");
    }

    public native String helloJni();

}
