package com.pepoc.androidnewtechnique.activitytask;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

public class ATestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atest);

        findViewById(R.id.btn_start_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ATestActivity.this, BTestActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);

//                LogManager.i("getResourceEntryName = " + getResources().getResourceEntryName(R.drawable.leak_canary_icon));

//                startActivity(null);
//                startService(null);


                Intent intent = new Intent();
//                intent.setComponent(new ComponentName("com.qzone", "com.qzonex.module.operation.ui.QZonePublishMoodActivity"));
                intent.setAction("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "分享QQ空间： https://blog.csdn.net/chadeltu/article/details/43450713");
                startActivity(intent);
            }
        });
    }
}
