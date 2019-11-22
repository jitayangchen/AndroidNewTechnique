package com.pepoc.androidnewtechnique.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
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

        findViewById(R.id.btn_upload_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
    }

    private void uploadFile() {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", "file")
                .addFormDataPart("file", "fileName",
                        RequestBody.create(MediaType.parse("multipart/form-data"), new File("/sdcard/zzz_hhh.txt")))
                .build();

        Request request = new Request.Builder()
                .url("http://test.findfine.com.cn/uploads/")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
//            response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getRequest() {
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
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
