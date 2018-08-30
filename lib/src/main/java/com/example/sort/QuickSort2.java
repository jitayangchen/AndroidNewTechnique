package com.example.sort;

import com.example.Log;

public class QuickSort2 {

    private static int[] nums = new int[] {30, 6, 18, 39, 2, 9, 26, 55, 90, 13, 19, 21, 5, 10, 8};
//    private static int[] nums = new int[] {30, 90, 55, 39};

    public static void main(String[] args) {
        QuickSort2 quickSort = new QuickSort2();
//        quickSort.bubbleSort(nums);
//        quickSort.selectionSort(nums);
        quickSort.quickSort4(0, nums.length - 1);

        for (int num : nums) {
            Log.i(String.valueOf(num));
        }
    }

    private void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j+1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                }
            }
        }
    }

    private void bubbleSort() {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] < nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                }
            }
        }
    }

    private void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
    }

    private void selectionSort() {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
    }

    private void quickSort(int[] nums, int i, int j) {
        if (i >= j) {
            return ;
        }
        int base = nums[i];
        int base_i = i;
        int base_j = j;

        while (i < j) {
            while (i < j && nums[j] >= base) {
                j--;
            }

            while (i < j && nums[i] <= base) {
                i++;
            }

            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }

        nums[base_i] = nums[i];
        nums[i] = base;

        quickSort(nums, base_i, i - 1);
        quickSort(nums, i + 1, base_j);
    }

    private void quickSort3(int[] nums, int i, int j) {
        if (i > j) {
            return ;
        }

        int base = nums[i];
        int base_i = i;
        int base_j = j;

        while (i < j) {
            while (i < j && nums[j] >= base) {
                j--;
            }

            while (i < j && nums[i] <= base) {
                i++;
            }

            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }

        nums[base_i] = nums[i];
        nums[i] = base;

        quickSort3(nums, base_i, i - 1);
        quickSort3(nums, i + 1, base_j);
    }

    private void quickSort4(int i, int j) {
        if (i > j) {
            return ;
        }

        int base = nums[i];
        int base_i = i;
        int base_j = j;

        while (i < j) {
            while (i < j && nums[j] >= base)
                j--;

            while (i < j && nums[i] <= base)
                i++;

            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }

        nums[base_i] = nums[i];
        nums[i] = base;

        quickSort4(base_i, i - 1);
        quickSort4(i + 1, base_j);
    }

}
