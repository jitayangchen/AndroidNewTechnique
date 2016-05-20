package com.pepoc.androidnewtechnique.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

public class AsyncTaskDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_demo);

        init();
    }

    private void init() {
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().execute("ggg");
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
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            LogManager.i("-----------onProgressUpdate----------");
        }
    }
}
