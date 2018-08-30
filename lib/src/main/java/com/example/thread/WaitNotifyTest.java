package com.example.thread;

import com.example.Log;

public class WaitNotifyTest {

    private final static Object object = new Object();

    public static void main(String[] args) {

        Runnable runnable = new Runnable(){
            @Override
            public void run(){
                System.out.println("Thread-1");
                try {
                    synchronized (object) {
                        Log.i("synchronized Thread 1");
//                        object.wait();
                        Thread.sleep(2000);
                        System.out.println("Thread-1 after wait");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t2=new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread-----2");
                synchronized (object) {
//                    object.notifyAll();
                    System.out.println("Thread-----2 after notify");
                }
            }
        });

        Thread t1=new Thread(runnable);
        Thread t3=new Thread(runnable);
        t1.start();
        t3.start();

        t2.start();
    }

}
