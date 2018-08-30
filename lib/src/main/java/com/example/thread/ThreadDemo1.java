package com.example.thread;

import com.example.Log;

public class ThreadDemo1 {

    public static void addI(int flag) {
        try {
            int num;
            if (flag == 0) {
                num = 100;
                Log.i("0 set over! time = " + System.currentTimeMillis());
                Thread.sleep(2000);
            } else {
                num = 200;
                Log.i("1 set over! time = " + System.currentTimeMillis());
            }
            Log.i(flag + " num = " + num + " time = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadDemo1 threadDemo1 = new ThreadDemo1();
        ThreadDemo1 threadDemo2 = new ThreadDemo1();
        Thread0 thread0 = new Thread0(threadDemo1);
        Thread1 thread1 = new Thread1(threadDemo2);
        thread0.start();
        thread1.start();

//        threadDemo1.addI(0);
//        threadDemo1.addI(1);
    }
}

class Thread0 extends Thread {

    private ThreadDemo1 threadDemo1;

    public Thread0(ThreadDemo1 threadDemo1) {
        this.threadDemo1 = threadDemo1;
    }

    @Override
    public void run() {
        super.run();
        ThreadDemo1.addI(0);
    }
}

class Thread1 extends Thread {

    private ThreadDemo1 threadDemo1;

    public Thread1(ThreadDemo1 threadDemo1) {
        this.threadDemo1 = threadDemo1;
    }

    @Override
    public void run() {
        super.run();
        ThreadDemo1.addI(1);
    }
}

