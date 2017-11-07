package com.pepoc.androidnewtechnique.systemlanuage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

import java.util.Locale;

public class SystemLanuageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_lanuage);

        findViewById(R.id.btn_get_system_lanuage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = Locale.getDefault().getCountry();
                LogManager.i("country === " + country);
            }
        });
    }
}
