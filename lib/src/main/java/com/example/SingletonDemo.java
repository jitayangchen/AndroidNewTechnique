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
//            instance = new SingletonDemo();
            instance = InstanceCreate.instance;
            Log.i("-----------------getInstance()----------------");
        }

        return instance;
    }

    public static SingletonDemo get() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }

        return instance;
    }

    private static class InstanceCreate {
        final static SingletonDemo instance = new SingletonDemo();
    }

    public void function(String threadName) {
        Log.i(threadName);
    }
}
