package com.pepoc.androidnewtechnique.collection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pepoc.androidnewtechnique.R;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueueActivity extends AppCompatActivity {

    private Button btnAdd;
    private LinearLayout scrollView;
    private ArrayBlockingQueue<String> arrayBlockingQueue;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                TextView textView = new TextView(BlockingQueueActivity.this);
                textView.setText((String) msg.obj);
                scrollView.addView(textView);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocking_queue);

//        arrayBlockingQueue.take()

        init();
    }

    private void init() {

        btnAdd = (Button) findViewById(R.id.btn_add);
        scrollView = (LinearLayout) findViewById(R.id.scroll_view);

        arrayBlockingQueue = new ArrayBlockingQueue<>(10, true);
        try {
            arrayBlockingQueue.put("HAHAHA");
            arrayBlockingQueue.put("LALALA");
            arrayBlockingQueue.put("XIXIXI");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    arrayBlockingQueue.put("NEW_YOYOYO");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        new MyThread().start();
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();

            while (true) {
                try {
                    String str = arrayBlockingQueue.take();
                    Message msg = handler.obtainMessage();
                    msg.what = 1;
                    msg.obj = str;
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
