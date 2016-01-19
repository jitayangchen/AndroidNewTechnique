package com.pepoc.androidnewtechnique.log;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pepoc.androidnewtechnique.R;

public class LogFragment extends Fragment {

    private TextView tvLog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_log, container, false);
        tvLog = (TextView) view.findViewById(R.id.tv_log);
        return view;
    }

    public void println(String msg) {
        tvLog.append(msg + "\n");
    }

    public void clear() {
        tvLog.setText("");
    }

}
