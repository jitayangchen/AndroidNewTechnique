package com.example;

/**
 * Created by yangchen on 16-5-14.
 */
public class SingletonDemo {

    private static SingletonDemo instance = null;

    private SingletonDemo() {
        Log.i("-----------------SingletonDemo()----------------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
            Log.i("-----------------getInstance()----------------");
        }

        return instance;
    }

    public void function(String threadName) {
        Log.i(threadName);
    }
}
