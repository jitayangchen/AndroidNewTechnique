package com.example;

/**
 * Created by yangchen on 2017/9/11.
 */

public class ExceptionTest1 {

    void testEx(int i) {
        try {
            Log.i("========== try before ========");
            int a = 1/i;
            Log.i("========== try after ======== a = " + a);

        } catch (ArithmeticException e) {
            e.printStackTrace();
            System.out.println("testEx, catch exception");
            throw new RuntimeException();
        } finally {
            Log.i("====== finally =====");
        }
        Log.i("=========lalala=========");
    }

    public static void main(String[] args) {
        ExceptionTest1 testException1 = new ExceptionTest1();
        testException1.testEx(0);
    }
}
