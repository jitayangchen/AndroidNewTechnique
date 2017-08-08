package com.pepoc.androidnewtechnique.customview.pulltorefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.pepoc.androidnewtechnique.R;

public class PullToRefreshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);

        PullToRefreshLayout pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.pull_to_refresh_layout);
        ImageView headerView = new ImageView(this);
        headerView.setBackgroundResource(R.drawable.test_img);
        pullToRefreshLayout.setHeaderView(headerView);
    }
}
