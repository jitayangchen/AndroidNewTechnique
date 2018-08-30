package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangchen on 2018/4/8.
 */

public class TextList {

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arr.add(i + " - lala");
        }

        Test test = new Test(arr);
        test.print();

        arr = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            arr.add("haha - " + i);
        }

        test.print();
    }


}

class Test {
    List<String> arr = null;

    public Test(List<String> arr) {
        this.arr = arr;
    }

    public void print() {
        for (String s : arr) {
            Log.i(s);
        }
    }
}
