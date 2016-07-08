package com.example;

import java.util.ArrayList;
import java.util.List;

public class MyClass {

    private static int[] num = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {

//        int[] arr = new int[]{78, 2, 56, 51, 12, 4, 69, 34, 95, 8};
//        bubbleSort(arr);


//        List<Integer> arr = new ArrayList<>();
//        arr.add(1);
//        arr.add(5);
//        System.out.println(arr);
//        arr.set(1, 3);
//        System.out.println(arr);

        test();

    }

    private static void bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        for (int i : arr) {
            System.out.print(i + " , ");
        }
    }

    private static void test() {
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 3; c++) {
                    for (int d = 0; d < 3; d++) {
                        for (int e = 0; e < 3; e++) {
                            for (int f = 0; f < 3; f++) {
                                for (int g = 0; g < 3; g++) {
                                    for (int h = 0; h < 3; h++) {
//                                        System.out.println(a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f + ", " + g + ", " + h);
                                        int[] symbol = new int[]{a, b, c, d, e, f, g, h};
                                        test1(symbol);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void test1(int[] symbol) {
        List<Integer> arr = new ArrayList<>();
        arr.add(num[0]);
        for (int i = 1; i < num.length; i++) {
            switch (symbol[i-1]) {
                case 0:
                    arr.add(num[i]);
                    break;
                case 1:
                    arr.add(-num[i]);
                    break;
                case 2:
                    int last = arr.get(arr.size() - 1);
                    last = last * 10 + num[i];
                    arr.set(arr.size() - 1, last);
                    break;
            }
        }

        int sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        if (sum == 100) {
            System.out.println(arr);
        }
    }

}
