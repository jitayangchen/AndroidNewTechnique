package com.pepoc.androidnewtechnique.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

public class AsyncTaskDemoActivity extends AppCompatActivity {

    private Button btnTest;
    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_demo);

        init();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                btnTest.setText("Modify");
//            }
//        }).start();
    }

    private void init() {
//        myAsyncTask = new MyAsyncTask();
        btnTest = (Button) findViewById(R.id.btn_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new MyAsyncTask().execute("ggg");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        btnTest.setText("Modify");
//                        new MyAsyncTask().execute("ggg");
                    }
                }).start();

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        new MyAsyncTask().execute("ggg");
                    }
                }.start();
            }
        });
    }

    class MyAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            LogManager.i("-----------onPreExecute----------");
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            LogManager.i("-----------doInBackground----------");
            return "ddd";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            LogManager.i("-----------onPostExecute----------");
            btnTest.setText("Modify");
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            LogManager.i("-----------onProgressUpdate----------");
        }
    }
}
