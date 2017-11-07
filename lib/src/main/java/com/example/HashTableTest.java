package com.example;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by yangchen on 2017/9/11.
 */

public class HashTableTest {

    private Hashtable<Integer, String> hashtable = new Hashtable<>();

    public static void main(String[] args) {

//        new HashTableTest().testThread();

        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());
    }

    private void testThread() {

        for (int i = 0; i < 100; i++) {
            hashtable.put(i, String.valueOf(i));
        }


        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 100; i < 200; i++) {
                    System.out.println("hashtable put key value");
                    hashtable.put(i, String.valueOf(i));
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                for (Map.Entry<Integer, String> next : hashtable.entrySet()) {
                    System.out.println(next.getKey() + " --- " + next.getValue());
                }

//                for (int i = 0; i < 100; i++) {
//                    System.out.println(hashtable.get(i));
//                }
            }
        }.start();
    }
}
