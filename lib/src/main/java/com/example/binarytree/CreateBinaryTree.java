package com.example.binarytree;

import com.example.Log;

public class CreateBinaryTree {

    public static void main(String[] args) {
        int[] array = new int[]{10, 30, 4, 71, 11, 9, 25, 17, 28, 12, 21};

        CreateBinaryTree createBinaryTree = new CreateBinaryTree();

        Node rootNode = new Node(array[0]);
        for (int i = 1; i < array.length; i++) {

            createBinaryTree.createNode(rootNode, array[i]);
        }

        Log.i(rootNode.toString());
    }

    private void createNode(Node node, int data) {
        if (node == null) {
            return ;
        }

        if (data < node.data) {
            if (node.lchild == null)
                node.lchild = new Node(data);
            else
                createNode(node.lchild, data);
        } else if (data > node.data) {
            if (node.rchild == null)
                node.rchild = new Node(data);
            else
                createNode(node.rchild, data);
        }
    }

    private static class Node {
        int data;
        Node lchild = null;
        Node rchild = null;

        Node(int data) {
            this.data = data;
        }
    }
}
