package com.pepoc.androidnewtechnique.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.pepoc.androidnewtechnique.R;

public class SqliteDemoActivity extends AppCompatActivity {

    private DBOpenHelper dbHelper;
    private TextView tvShowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqllite_demo);

//        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("test.db", 1, null);

        init();
    }

    private void init() {
        dbHelper = new DBOpenHelper(this);

        tvShowData = (TextView) findViewById(R.id.tv_show_data);

        findViewById(R.id.btn_insert_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

//        List<Person> personList = dbHelper.findAll();
//        for (Person person : personList) {
//            LogManager.i(person.toString());
//        }
    }

    private void insertData() {
        Person person = new Person();
        person.setName("Yangchen");
        person.setAge(25);
        person.setSex(1);
        dbHelper.insert(person);
    }
}
