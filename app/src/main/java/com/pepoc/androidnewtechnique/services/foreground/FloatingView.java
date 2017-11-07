/*
 * The MIT License (MIT)
 * Copyright (c) 2016 baoyongzhang <baoyz94@gmail.com>
 */
package com.pepoc.androidnewtechnique.services.foreground;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.pepoc.androidnewtechnique.R;

/**
 * Created by baoyongzhang on 2016/11/1.
 */
public class FloatingView extends android.support.v7.widget.AppCompatImageView {

    private static final int ANIMATION_DURATION = 500;

    private final WindowManager mWindowManager;
    private final int mMargin;
    private Runnable mDismissTask = new Runnable() {
        @Override
        public void run() {
            dismiss();
        }
    };
    private boolean isShow;
    private String mText;

    public FloatingView(Context context) {
        super(context);

        setImageResource(R.mipmap.ic_launcher);
        mMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("bigBang://?extra_text=" + URLEncoder.encode(mText, "utf-8")));
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    getContext().startActivity(intent);
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                dismiss();
            }
        });
    }

    public void show() {
        if (!isShow) {
            int w = WindowManager.LayoutParams.WRAP_CONTENT;
            int h = WindowManager.LayoutParams.WRAP_CONTENT;
            int flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            int type;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                type = WindowManager.LayoutParams.TYPE_TOAST;
            } else {
                type = WindowManager.LayoutParams.TYPE_PHONE;
            }

            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(w, h, type, flags, PixelFormat.TRANSLUCENT);
            layoutParams.gravity = Gravity.END | Gravity.BOTTOM;
            layoutParams.x = mMargin;
            layoutParams.y = mMargin;

            mWindowManager.addView(this, layoutParams);

            isShow = true;

            postDelayed(mDismissTask, 5000);
        } else {
            removeCallbacks(mDismissTask);
            postDelayed(mDismissTask, 5000);
        }
    }

    public void dismiss() {
        if (isShow) {
            mWindowManager.removeView(FloatingView.this);
            isShow = false;
        }
        removeCallbacks(mDismissTask);
    }

    public void setText(String text) {
        mText = text;
    }
}
