package com.pepoc.androidnewtechnique.memoryleak;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pepoc.androidnewtechnique.R;

public class MemoryLeakActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private MemoryLeakPool memoryLeakPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

//        MemoryLeakPool.addContext(this);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_img);
        Log.i("Test", bitmap.getWidth() + " - " + bitmap.getHeight());

        memoryLeakPool = MemoryLeakPool.getInstance();
        Toast.makeText(this, String.valueOf(memoryLeakPool.getCount()), Toast.LENGTH_SHORT).show();


        findViewById(R.id.btn_thread_stack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread.dumpStack();
                Toast.makeText(MemoryLeakActivity.this, "Test Thread Stack", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
