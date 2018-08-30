package com.pepoc.androidnewtechnique.recyclerview.snaphelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pepoc.androidnewtechnique.R;

import java.util.ArrayList;

/**
 * description：
 * author：zhangguiyou
 * date: 2018/6/28.
 */

public class ImageAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<String> datas;

    public ImageAdapter2(Context context, ArrayList<String> datas) {
        this.context = context;
        this.datas = datas;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("uu", "onCreateViewHolder");
        ImageHolder2 holder = new ImageHolder2(LayoutInflater.from(context).inflate(R.layout.item_image, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.i("uu", "onBindViewHolder: "+position);
        ((ImageHolder2)holder).setData(position, datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private class ImageHolder2 extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ImageHolder2(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);
        }

        public void setData(int position, String pic) {
            Log.i("uu", "ImageView的size: "+imageView.getWidth()+"   "+imageView.getHeight());
            textView.setText(position+"");
            Glide.with(imageView).load(pic).into(imageView);
//        preload(position+1);
        }
    }
}
