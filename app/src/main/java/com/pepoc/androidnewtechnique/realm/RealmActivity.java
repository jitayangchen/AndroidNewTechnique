package com.pepoc.androidnewtechnique.realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

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
        final Realm realm = Realm.getInstance(config);


        findViewById(R.id.btn_add_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                User user = realm.createObject(User.class);
                user.setId(System.currentTimeMillis());
                user.setName("Yangchen");
                user.setAge(26);
                user.setSex("male");
                realm.commitTransaction();
            }
        });

        findViewById(R.id.btn_modify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User first = realm.where(User.class).findFirst();
//                for (User user : results) {
//                    user.setName("LALALALALA");
//                    Log.i("LALALA", user.getId() + " --- " + user.getName());
//                }
                first.setName("LALALALALA");
                Log.i("LALALA", first.getId() + " --- " + first.getName());
            }
        });
    }

}
