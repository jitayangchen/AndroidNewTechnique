package com.pepoc.androidnewtechnique.recyclerview.snaphelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

import java.util.ArrayList;

public class RemoveSnapActivity extends AppCompatActivity {

    ArrayList<String> datas = new ArrayList<>();
    RecyclerView recyclerView;
    PagerSnapHelper pagerSnapHelper;

    public static String[] pics = new String[] {
            "http://friendq-10009900.image.myqcloud.com/fateship/44/2b/cc3a4b4ad774f93cb7476ad027f5/pic/1528788338_35987737.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/9e/3d/30440ef39acf3ea3b0a8445d0a26/pic/1528788276_35987717.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/6a/73/29b7fedc64821c0db08070f5a5c9/pic/1528787868_35987514.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/11/49/7c886017577940822b9f4ef13934/pic/1528785849_35986543.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/5b/d4/55d6ac4f09e7c982d43e35e2f470/pic/1528785318_35986283.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/28/f7/bca6006572132e30ee1728172879/pic/1528785245_35986258.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/60/11/293db06995e12464ad0f742b05ca/pic/1528783462_35985369.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/89/21/e2068c37dfda0be94ce25176a0c3/pic/1528783199_35985203.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/f5/27/5204db193c90bd54d95aa770d6de/pic/1528780771_35983944.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/28/f7/bca6006572132e30ee1728172879/pic/1528780613_35983870.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/a6/f1/71a120f586e81181b8e8feae5d4b/pic/1528780326_35983735.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/c0/e6/2a0b8580503987cb63cd93ffea6a/pic/1528779027_35982852.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/fa/be/7daed7b0e812c7a0ce283f8dd27e/pic/1528778978_35982824.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/fa/be/7daed7b0e812c7a0ce283f8dd27e/pic/1528778764_35982681.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/67/31/9d23a0a7b6d25f0434cf9acba658/pic/1528778702_35982605.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/34/b8/d88e1933538dda4254a7a9600b07/pic/1528777968_35982094.jpg",
            "http://friendq-10009900.image.myqcloud.com/fateship/22/14/fd38e5858ee4f731adc3293b37d1/pic/1528777967_35982093.jpg",
            "http://friendq-10009900.image.myqcloud.com/fateship/ab/f2/1f698481fc4990d583cab8b0c0a8/pic/1528777864_35982033.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/42/44/4f9e4256c1eccbfea341db15596e/pic/1528777610_35981891.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/a1/76/c80c955b4b62df9be0bdf5c518cf/pic/1528777367_35981785.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/d4/b1/afe079338a90759b57a8e7eea363/pic/1528777131_35981660.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/2a/3e/54aa0a3d618c7f5cce3f566a3460/pic/1528776849_35981488.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/a6/f1/71a120f586e81181b8e8feae5d4b/pic/1528776526_35981323.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/fc/b2/c018988834e48692639776d9e9c7/pic/1528776134_35981098.jpeg",
            "http://friendq-10009900.image.myqcloud.com/fateship/a7/91/b249f4a788e828700fc3ab485a81/pic/1528774947_35980297.jpg",
            "http://friendq-10009900.image.myqcloud.com/fateship/70/38/e9514f5e144e6ca3128a7770581d/pic/1528774168_35979691.jpg",
            "http://friendq-10009900.image.myqcloud.com/fateship/40/44/4ecb78698644d3d6a8189526528c/pic/1528774018_35979620.jpeg"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_snap);
        initData();
        initView();

    }


    private void initData() {
        for (String pic : pics) {
            datas.add(pic);
        }
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);

        final ImageAdapter2 adapter = new ImageAdapter2(this, datas);
        recyclerView.setAdapter(adapter);


        Button btnRemove = (Button) findViewById(R.id.btn_remove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View centerView = pagerSnapHelper.findSnapView(linearLayoutManager);
                int centerPosition = linearLayoutManager.getPosition(centerView);
                if (centerPosition > 0) {
                    datas.remove(centerPosition -1);
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(centerPosition -1);
                }
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LogManager.i("scroll", "onScrollStateChanged newState = " + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LogManager.i("scroll", "onScrolled");
            }
        });
    }
}
