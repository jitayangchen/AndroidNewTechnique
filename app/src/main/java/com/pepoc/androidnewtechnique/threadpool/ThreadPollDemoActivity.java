package com.pepoc.androidnewtechnique.threadpool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPollDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_poll_demo);
        
        init();
    }

    private void init() {

        findViewById(R.id.btn_new_cached).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newCachedDemo();
            }
        });

        findViewById(R.id.btn_new_fixed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFixedDemo();
            }
        });

        findViewById(R.id.btn_scheduled).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduledThreadPoll();
            }
        });

    }

    private void newCachedDemo() {
        ExecutorService cachedThreadPoll = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPoll.execute(new MyThread(i));
        }
    }

    private void newFixedDemo() {
        ExecutorService fixedThreadPoll = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            fixedThreadPoll.execute(new MyThread(i));
        }
    }

    private void scheduledThreadPoll() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleAtFixedRate(new MyThread(10), 1, 2, TimeUnit.SECONDS);
    }

    class MyThread extends Thread {

        private int index;

        public MyThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            super.run();
            LogManager.i(index + " ----- Thread name: " + Thread.currentThread().getName());
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
