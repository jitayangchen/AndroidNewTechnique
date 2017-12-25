package com.pepoc.androidnewtechnique.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

public class SingleTaskDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task_demo);

        LogManager.i("SingleTask --- " + "onCreate");

        findViewById(R.id.btn_test_single_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleTaskDemoActivity.this, FragmentDemoActivity.class));
            }
        });

        //获取SD卡是否存在:mounted
        String storageState = Environment.getExternalStorageState().toLowerCase();
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            LogManager.i("Environment.getExternalStorageState() =: " + storageState);

        //设备的外存是否是用内存模拟的，是则返回true。(API Level 11)
        boolean isEmulated = Environment.isExternalStorageEmulated();
        LogManager.i("Environment.isExternalStorageEmulated() =: " + isEmulated);

        //设备的外存是否是可以拆卸的，比如SD卡，是则返回true。
        boolean isRemovable = Environment.isExternalStorageRemovable();
        LogManager.i("Environment.isExternalStorageRemovable() =: " + isRemovable);

        LogManager.i("Environment.getDataDirectory() = " + Environment.getDataDirectory());

        LogManager.i("Environment.getRootDirectory() = " + Environment.getRootDirectory().toString());

        LogManager.i("Environment.getDownloadCacheDirectory() = " + Environment.getDownloadCacheDirectory().toString());

        LogManager.i("Environment.getExternalStorageDirectory() = " + Environment.getExternalStorageDirectory().toString());

        LogManager.i("Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) = " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString());

        LogManager.i("Environment.getExternalStorageDirectory() = " + Environment.getExternalStorageDirectory().toString());


        //获取当前程序路径 应用在内存上的目录 :/data/data/com.mufeng.toolproject/files
        String filesDir = getFilesDir().toString();
        LogManager.i("context.getFilesDir()=:" + filesDir);

        //应用的在内存上的缓存目录 :/data/data/com.mufeng.toolproject/cache
        String cacheDir = getCacheDir().toString();
        LogManager.i("context.getCacheDir()=:" + cacheDir);

        //应用在外部存储上的目录 :/storage/emulated/0/Android/data/com.mufeng.toolproject/files/Movies
        String externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString();
        LogManager.i("context.getExternalFilesDir()=:" + externalFilesDir);

        //应用的在外部存储上的缓存目录 :/storage/emulated/0/Android/data/com.mufeng.toolproject/cache
        String externalCacheDir = getExternalCacheDir().toString();
        LogManager.i("context.getExternalCacheDir()=:" + externalCacheDir);

        //获取该程序的安装包路径 :/data/app/com.mufeng.toolproject-3.apk
        String packageResourcePath = getPackageResourcePath();
        LogManager.i("context.getPackageResourcePath()=:" + packageResourcePath);

        //获取程序默认数据库路径 :/data/data/com.mufeng.toolproject/databases/mufeng
        String databasePat = getDatabasePath("db_name").toString();
        LogManager.i("context.getDatabasePath()=:" + databasePat);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogManager.i("SingleTask --- " + "onNewIntent");
    }
}
