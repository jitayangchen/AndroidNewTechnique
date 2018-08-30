package com.pepoc.androidnewtechnique.recyclerview.viewlistener;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.pepoc.androidnewtechnique.log.LogManager;

/**
 * Created by yangchen on 2018/3/19.
 */

public class ItemViewLayout extends RelativeLayout {
    public ItemViewLayout(Context context) {
        super(context);
        init();
    }

    public ItemViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ItemViewLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                LogManager.i("==================onScrollChanged=============");
            }
        });
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
//        LogManager.i("=================onVisibilityChanged==================");
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
//        LogManager.i("=================onWindowVisibilityChanged==================");
    }
}
