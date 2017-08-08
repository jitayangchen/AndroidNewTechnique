package com.pepoc.androidnewtechnique.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pepoc.androidnewtechnique.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangchen on 2017/3/2.
 */

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ItemViewHolder> {

    private List<String> datas = new ArrayList<>();

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_demo, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.textView.setText("item -- " + datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addData(String data) {
        datas.add(data);
    }

    public List<String> getDatas() {
        return datas;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public final TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item_test);
        }
    }
}
