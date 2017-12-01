package com.pepoc.androidnewtechnique.memoryleak;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pepoc.androidnewtechnique.R;

public class MemoryLeakActivity extends AppCompatActivity {

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

//        MemoryLeakPool.addContext(this);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_img);
    }
}
