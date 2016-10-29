package com.pepoc.androidnewtechnique.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pepoc.androidnewtechnique.R;

public class RecyclerViewDemoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);

        recyclerViewDemo = (RecyclerView) findViewById(R.id.recycler_view_demo);

        recyclerViewDemo.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDemo.setAdapter(new AdapterDemo());
    }

    class AdapterDemo extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(RecyclerViewDemoActivity.this);

            return new ViewHolder(textView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            TextView textView = ((TextView)holder.itemView);
            textView.setText("item --- " + position);
            textView.setTextSize(30);
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}
