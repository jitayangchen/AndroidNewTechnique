package com.example;

public class InnerClass {
    private static int a = 10;
    private class Demo1 {
        private int b = 20;
        Demo2 demo2 = new Demo2();

        private void fun() {

        }
    }

    private static class Demo2 {
        public Demo2() {
            InnerClass.a = 20;
            InnerClass innerClass = new InnerClass();
            Demo1 demo1 = innerClass.new Demo1();
            innerClass.test();

        }

        private void fun() {
            a = 100;
        }

        private static void fun1() {
            a = 200;
        }
    }

    private void test() {
        Demo2 demo2 = new Demo2();
        demo2.fun();

    }

    public static void main(String[] args) {
        InnerClass.Demo1 demo1 = new InnerClass().new Demo1();

        Demo2 demo2 = new Demo2();
        demo2.fun();
    }
}
