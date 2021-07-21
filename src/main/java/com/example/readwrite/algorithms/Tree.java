package com.example.readwrite.algorithms;

import lombok.Data;

public class Tree {
    public static void main(String[] args) {
        System.out.println(3/2);
        treeView(genTree());
        System.out.println();
    }
    public static void treeView(TreeNode root) {
        if(root == null) return;
        treeView(root.left);
        treeView(root.right);
        System.out.print(root.val + "\t");
    }

    /**
     **           3
     **     12           1
     ** 32      21    23    14
     **
     * pre: 3 12 32 21 1 23 14
     * in: 32 12 21 3 23 1 14
     * post: 32 21 12 23 14 1 3
     * @return
     */
    public static TreeNode genTree() {
        TreeNode a = new TreeNode(3);
        a.right = new TreeNode(1);
        a.right.right = new TreeNode(14);
        a.right.left = new TreeNode(23);
        a.left = new TreeNode(12);
        a.left.right = new TreeNode(21);
        a.left.left = new TreeNode(32);
        return a;
    }
}
@Data
class TreeNode {
    public TreeNode(int val) {
        this.val = val;
    }

    int val;
    TreeNode left;
    TreeNode right;
}
