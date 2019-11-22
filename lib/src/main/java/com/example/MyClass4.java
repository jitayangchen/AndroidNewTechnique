package com.example;

public class MyClass4 {

    public static void main(String[] args) {

//        String xxx = "hahaha";
//        String[] split = xxx.split("&");
//
//        Log.i("size = " + split.length);
//
//        for (String tmp : split) {
//            Log.i(tmp);
//        }
//
//        int num = 10_000_0;
//        Log.i("num = " + num);



//        String str = "/sdcard/zzz_hhh.txt";
//        int i = str.lastIndexOf("/");
//        Log.i("i = " + i);
//        String substring = str.substring(i + 1);
//        Log.i("substring = " + substring);
//
//        Log.i("str.endsWith(\"t\") = " + str.endsWith("x"));

        String url = "edge://com.edge.smallapp/game/123456?icon=xxx";
        url = url.replace("edge://", "");

        int i = url.indexOf("?");
        Log.i("? i = " + i);
        String substring = url.substring(0, i);
        Log.i("substring = " + substring);

        int i1 = substring.lastIndexOf("/");
        Log.i("gid = " + substring.substring(i1 + 1));


        Log.i(url.substring(i + 1));
    }
}
