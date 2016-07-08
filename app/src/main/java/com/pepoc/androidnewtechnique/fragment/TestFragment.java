package com.pepoc.androidnewtechnique.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

public class TestFragment extends Fragment {

    private static TestFragment instance = null;

    public TestFragment() {
    }

    public static TestFragment newInstance() {
        if (instance == null) {
            instance = new TestFragment();
        }
        return instance;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogManager.i("----------------onAttach-----------------");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogManager.i("----------------onCreate-----------------");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogManager.i("----------------onCreateView-----------------");
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogManager.i("----------------onActivityCreated-----------------");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogManager.i("----------------onStart-----------------");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogManager.i("----------------onResume-----------------");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogManager.i("----------------onPause-----------------");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogManager.i("----------------onStop-----------------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogManager.i("----------------onDestroyView-----------------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogManager.i("----------------onDestroy-----------------");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogManager.i("----------------onDetach-----------------");
    }

}
