package com.pepoc.androidnewtechnique.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.pepoc.androidnewtechnique.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDemo;
    private ItemTouchHelper mItemTouchHelper;
    private RecyclerListAdapter adapter;
    private boolean refreshing = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String data = (String) msg.obj;
                    adapter.addData(data);
                    if (!refreshing) {
                        refreshing = true;
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 300);
                    }

                    break;
                case 1:
                    adapter.notifyDataSetChanged();
                    if (recyclerViewDemo.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                        recyclerViewDemo.smoothScrollToPosition(adapter.getDatas().size() - 1);
                    }
                    refreshing = false;
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerViewDemo = (RecyclerView) findViewById(R.id.recycler_view_demo);
        recyclerViewDemo.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerListAdapter();
        recyclerViewDemo.setAdapter(adapter);

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerViewDemo);
    }

    private void loadData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                int i = 0;
                for (;;) {
                    Message message = handler.obtainMessage(0, String.valueOf(i));
                    try {
                        sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendMessage(message);
                    i++;
                }
            }
        }.start();
    }
}
