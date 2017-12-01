package com.pepoc.androidnewtechnique.realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pepoc.androidnewtechnique.R;


public class RealmActivity extends AppCompatActivity {

//    @Bind(R.id.toolbar)
//    Toolbar toolbar;
//    @Bind(R.id.btn_add_data)
//    Button btnAddData;
//    @Bind(R.id.btn_modify)
//    Button btnModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
//        ButterKnife.bind(this);
//        setSupportActionBar(toolbar);

        init();
    }

    private void init() {
//        RealmConfiguration config = new RealmConfiguration.Builder(this)
//                .name("Person")
//                .build();
//        final Realm realm = Realm.getInstance(config);
//
//
//        btnAddData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                realm.beginTransaction();
//                User user = realm.createObject(User.class);
//                user.setId(System.currentTimeMillis());
//                user.setName("Yangchen");
//                user.setAge(26);
//                user.setSex("male");
//                realm.commitTransaction();
//            }
//        });
//
//        btnModify.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                realm.beginTransaction();
//                User first = realm.where(User.class).findFirst();
////                for (User user : results) {
////                    user.setName("LALALALALA");
////                    Log.i("LALALA", user.getId() + " --- " + user.getName());
////                }
//                first.setName("LALALALALA");
//                realm.commitTransaction();
//                Log.i("LALALA", first.getId() + " --- " + first.getName());
//            }
//        });
    }

}
