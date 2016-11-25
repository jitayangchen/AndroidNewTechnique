package com.pepoc.androidnewtechnique.fragment;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;

import com.pepoc.androidnewtechnique.R;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/17 16:30
 * @copyright TCL-MIG
 */
public class MyActionProvider extends ActionProvider {
    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public MyActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.menu, null);
        return view;
    }
}
