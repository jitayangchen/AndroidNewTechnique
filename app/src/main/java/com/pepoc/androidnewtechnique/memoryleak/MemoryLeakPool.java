package com.pepoc.androidnewtechnique.memoryleak;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangchen on 2017/11/27.
 */

public class MemoryLeakPool {

    private final static MemoryLeakPool instance = new MemoryLeakPool();

    private static List<Context> contextList = new ArrayList<>();

    private int count = 0;

    private MemoryLeakPool() {

    }

    public static MemoryLeakPool getInstance() {
        return instance;
    }

    public static void addContext(Context context) {
        contextList.add(context);
    }

    public int getCount() {
        return count++;
    }
}
