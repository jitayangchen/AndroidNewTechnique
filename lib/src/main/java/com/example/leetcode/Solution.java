package com.example.leetcode;

import com.example.Log;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int a = 0;
        int b = 0;
        ListNode head = null;
        ListNode last = null;

        ListNode tmp1 = l1;
        ListNode tmp2 = l2;

        int value;
        boolean isOver = false;

        while (tmp1 != null || tmp2 != null || isOver) {

            if (tmp1 != null) {
                a = tmp1.val;
            }

            if (tmp2 != null) {
                b = tmp2.val;
            }

            if (isOver) {
                a += 1;
            }

            if (a + b >= 10) {
                value = a + b - 10;
                isOver = true;
            } else {
                value = a + b;
                isOver = false;
            }

            if (head == null) {
                last = head = new ListNode(value);
            } else {
                last.next = new ListNode(value);
                last = last.next;
            }

            if (tmp1 != null) {
                tmp1 = tmp1.next;
            }
            if (tmp2 != null) {
                tmp2 = tmp2.next;
            }

            a = b = 0;
        }

        return head;
    }

    public static void main(String[] args) {
        //TODO
        ListNode listNode = new ListNode(10);
        ListNode last = null;
        listNode.next = last;
        last = new ListNode(20);
        Log.i("last.val = " + last.val);
        Log.i("listNode.next.val = " + listNode.next.val);
    }
}

