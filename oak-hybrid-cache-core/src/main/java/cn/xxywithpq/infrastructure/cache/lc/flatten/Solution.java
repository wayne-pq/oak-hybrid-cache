package cn.xxywithpq.infrastructure.cache.lc.flatten;

import cn.xxywithpq.infrastructure.cache.lc.TreeNode;

import java.util.ArrayList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public static void flatten(TreeNode root) {
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        doFlatten(root, treeNodes);
        for (int i = 0; i < treeNodes.size() - 1; i++) {
            TreeNode treeNode = treeNodes.get(i);
            treeNode.left = null;
            treeNode.right = treeNodes.get(i + 1);
        }
        System.out.println();


    }

    public static void doFlatten(TreeNode node, ArrayList<TreeNode> list) {
        if (node != null) {
            list.add(node);
            doFlatten(node.left, list);
            doFlatten(node.right, list);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        flatten(root);
    }
}