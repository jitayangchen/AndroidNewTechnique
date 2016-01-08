package com.pepoc.androidnewtechnique.realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.pepoc.androidnewtechnique.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
    }

    private void init() {
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name("School")
                .build();
        Realm realm = Realm.getInstance(config);
        realm.beginTransaction();;

    }

}
