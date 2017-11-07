package com.pepoc.androidnewtechnique.andfix;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.pepoc.androidnewtechnique.R;

public class AndfixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andfix);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testAndfix();
            }
        });
    }

    public void testAndfix() {
//        Toast.makeText(this, "我是个错误 啦啦啦", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "把你解决了  哦哦哦  耶！！！", Toast.LENGTH_SHORT).show();
    }

}
