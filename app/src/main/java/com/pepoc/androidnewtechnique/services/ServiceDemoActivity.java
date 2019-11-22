package com.pepoc.androidnewtechnique.services;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

import cn.com.findfine.servicedemo.IMyAidlInterface;

public class ServiceDemoActivity extends AppCompatActivity {

    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        LogManager.i("------------ServiceDemoActivity----------- Pid = " + android.os.Process.myPid());
    }

    private void init() {
        findViewById(R.id.btn_start_other).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceDemoActivity.this, ServiceDemo2Activity.class));
            }
        });
        findViewById(R.id.btn_start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDemoActivity.this, ServicesDemo.class);
                startService(intent);
            }
        });

        findViewById(R.id.btn_stop_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDemoActivity.this, ServicesDemo.class);
                stopService(intent);
            }
        });

        findViewById(R.id.btn_bind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDemoActivity.this, ServicesDemo.class);
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

//                Intent intent = new Intent();
//                intent.setAction("cn.com.findfine.aidl.MyService");
//                intent.setPackage("cn.com.findfine.servicedemo");
//                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.btn_unbind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mServiceConnection);

//                try {
//                    Toast.makeText(ServiceDemoActivity.this, iMyAidlInterface.getName(), Toast.LENGTH_SHORT).show();
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            ServicesDemo.LocalBinder binder = (ServicesDemo.LocalBinder) service;
//            ServicesDemo mService = binder.getService();
//            mService.print();

            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                String name1 = iMyAidlInterface.getName();
                LogManager.i("name = " + name1);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
