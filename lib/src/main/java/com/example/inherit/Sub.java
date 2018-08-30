package com.example.inherit;

import com.example.Log;

public class Sub extends Parent {
    public Sub() {
        super("HHH");
//        Log.i("--- Sub ---");


        Log.i(getTest());
        Log.i(super.getSuperTest());
    }

    public static void main(String[] args) {
        Sub sub = new Sub();

//        Log.i(sub.getTest());
//        Log.i(sub.getSuperTest());
    }
}
