package com.pepoc.androidnewtechnique.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

public class FragmentDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);
        LogManager.i("----------------onCreate-----------------");
        init();
    }

    private void init() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, TestFragment.newInstance());
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogManager.i("----------------onStart-----------------");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogManager.i("----------------onRestart-----------------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogManager.i("----------------onResume-----------------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogManager.i("----------------onPause-----------------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogManager.i("----------------onStop-----------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogManager.i("----------------onDestroy-----------------");
    }
}
