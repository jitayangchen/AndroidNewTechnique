package com.example;

public class MyClass {
    public static void main(String[] args) {

//        for (int i = 0; i < 10; i++) {
//            System.out.print("i = " + i);
//        }
//        System.out.print("Yangchen");

        String[] str = new String[3];
        str[0] = "hahaha";
        str[1] = "xixixi";
        str[2] = "gagaga";

//        function(str[1]);
        for (int i = 0; i < str.length; i++) {
            str[i] = null;
        }

        for (int i = 0; i < str.length; i++) {
            Log.i("--- " + str[i]);
        }
    }

    private static void function(String str) {
        str = null;
    }
}
