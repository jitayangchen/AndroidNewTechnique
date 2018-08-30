package com.example.sort;

import com.example.Log;

public class QuickSort {

    private static int[] nums = new int[] {30, 6, 18, 39, 2, 9, 26, 55, 90, 13, 19, 21, 5, 10, 8};

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        quickSort.sort2(nums, 0, nums.length - 1);

        for (int num : nums) {
            Log.i(String.valueOf(num));
        }
    }

    private void sort(int[] nums, int i, int j) {
        if (i < j) {
            int base = realSort(nums, i, j);
            sort(nums, i, base - 1);
            sort(nums, base + 1, j);
        }
    }

    private int realSort(int[] nums, int i, int j) {
        int base = nums[i];
        while (i < j) {
            while (i < j && nums[j] >= base) {
                j--;
            }

            while (i < j && nums[j] < base) {
                nums[i] = nums[j];
                nums[j] = nums[++i];
            }
        }
        nums[i] = base;
        return i;
    }

    private void sort2(int[] nums, int i, int j) {
        if (j < i) {
            return ;
        }

        int base = nums[i];
        int tmpi = i;
        int tmpj = j;

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

        nums[tmpi] = nums[i];
        nums[i] = base;

        sort2(nums, tmpi, i - 1);
        sort2(nums, i + 1, tmpj);
    }
}
