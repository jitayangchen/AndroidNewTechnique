package com.pepoc.androidnewtechnique.wifi;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WifiTestActivity extends AppCompatActivity {

    private OkHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_test);

        init();
    }

    private void init() {
        client = new OkHttpClient();
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        login();
                    }
                }.start();
            }
        });
    }

    public void login() {
        getRequest();
    }

    private void startLogin(String url) {
        RequestBody body = new FormBody.Builder()
                .add("PtUser", "yangchen")
                .add("PtPwd", "yangchen)(*")
                .add("PtButton", "(unable to decode value)")
                .build();

        Request request = new Request.Builder()
//                                .addHeader("Connection", "close")


                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
                .addHeader("Cache-Control", "max-age=0")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Length", "59")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Host", "10.240.202.10")
                .addHeader("Origin", "http://10.240.202.10")
                .addHeader("Referer", "http://10.240.202.10/portal/logon.htm?userip=" + getCurrentIp())
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                .url(url)
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            LogManager.i(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCurrentIp() {
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        LogManager.i("ip === " + ip);
        return ip;
    }

    private String intToIp(int i) {

        return (i & 0xFF ) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ( i >> 24 & 0xFF) ;
    }


    public void getRequest() {
        Request request = new Request.Builder()
                .url("http://oa.jiayuan.com")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
//            \b(http).*\S("<)
            String regex = "\\b(http).*\\S(\"<)";
            String result = response.body().string();
            LogManager.i(result);
            Pattern compile = Pattern.compile(regex);
            Matcher matcher = compile.matcher(result);
            if (matcher.find()) {
                String group = matcher.group();
                if (!TextUtils.isEmpty(group)) {
                    group = group.substring(0, group.length() - 2).replace("htm", "cgi");
                }
                LogManager.i(group);
                startLogin(group);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
