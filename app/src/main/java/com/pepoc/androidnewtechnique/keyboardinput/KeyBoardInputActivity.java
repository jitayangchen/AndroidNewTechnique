package com.pepoc.androidnewtechnique.keyboardinput;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

public class KeyBoardInputActivity extends AppCompatActivity {

    private RecyclerView rvInputEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board_input_tmp);

        final View decorView = getWindow().getDecorView();

//        findViewById(R.id.btn_get_visible_rect).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Rect rect = new Rect();
//                decorView.getWindowVisibleDisplayFrame(rect);
//                LogManager.i("KeyBoardInputActivity", rect.toString());
//
//            }
//        });

        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                LogManager.i("===============onGlobalLayout==============");

                Rect rect = new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                LogManager.i("KeyBoardInputActivity", rect.toString());
            }
        });

        rvInputEvent = (RecyclerView) findViewById(R.id.rv_input_event);
        rvInputEvent.setLayoutManager(new LinearLayoutManager(this));
        rvInputEvent.setAdapter(new MyAdapter());

    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(new Button(KeyBoardInputActivity.this));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Button itemView = (Button) holder.itemView;
            itemView.setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
