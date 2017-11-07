package com.example;

import java.util.Calendar;

public class MyClass {

    private static int num = 0;

    public static void main(String[] args) {

//        for (int i = 0; i < 10; i++) {
//            System.out.print("i = " + i);
//        }
//        System.out.print("Yangchen");

//        AndroidAdapter.imgAdapter(461, 510);
//        double sqrt = Math.sqrt(320 * 320 + 480 * 480) / 3.2f;
//        Log.i("sqrt === " + sqrt);

//        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);

//        Object str = null;
//        String lala = (String) null;
//        Log.i("lala === " + lala);



//        MyThread myThread = new MyThread();
//        myThread.start();
//        try {
//            myThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Log.i("num === " + num);


//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = formatter.format(getDayStartTime());
//        Log.i(format);

//        Log.i("== " + ("YYY" == "YYY"));
//        Log.i("== " + "YYY".hashCode());
//        Log.i("== " + ("YYY" == "YYY"));


        try {
            String str = null;
            System.out.print(MyClass.class.getName());
            if (MyClass.class.getName().equals("com.example.MyClass3328")) {
                str = null;
            }
            System.out.print(str.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 5; i++) {
                num++;
                Log.i("MyThread num === " + num);
            }
        }
    }

    private static String getStr() {
        return null;
    }

    public static Long getDayStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }
}
