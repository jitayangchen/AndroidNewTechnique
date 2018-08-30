package com.pepoc.androidnewtechnique.recyclerview.viewlistener;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pepoc.androidnewtechnique.R;

public class ViewListenerActivity extends AppCompatActivity {

    private RecyclerView rvViewListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_listener);

//        View itemViewLayout = findViewById(R.id.item_view_layout);
//        itemViewLayout.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//                LogManager.i("==================onScrollChanged=============");
//            }
//        });

        rvViewListener = (RecyclerView) findViewById(R.id.rv_view_listener);
        rvViewListener.setLayoutManager(new LinearLayoutManager(this));
        rvViewListener.setAdapter(new ViewListenerAdapter());
    }

    class ViewListenerAdapter extends RecyclerView.Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ItemViewLayout itemViewLayout = new ItemViewLayout(ViewListenerActivity.this);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
            itemViewLayout.setLayoutParams(layoutParams);
            itemViewLayout.setBackgroundColor(Color.RED);

            TextView textView = new TextView(parent.getContext());
            textView.setText("lalala");
            itemViewLayout.addView(textView);
            return new ViewHolder(itemViewLayout);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 100;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);


        }
    }
}
