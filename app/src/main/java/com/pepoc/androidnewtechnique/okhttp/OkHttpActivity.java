package com.pepoc.androidnewtechnique.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {

    private OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        init();
    }

    private void init() {
        findViewById(R.id.btn_get_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        getRequest();
                    }
                }.start();
            }
        });

        findViewById(R.id.btn_post_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRequest();
            }
        });
    }

    public void getRequest() {
        Request request = new Request.Builder()
                .url("http://www.findfine.com.cn/programmerjoke/getjokes.php?page=1&userId=-1")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            LogManager.i(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void postRequest() {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");
    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
