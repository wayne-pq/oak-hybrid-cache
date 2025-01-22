package cn.xxywithpq.infrastructure.cache.lc.kthSmallest;

import cn.xxywithpq.infrastructure.cache.lc.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> result = new ArrayList<Integer>();
        doKthSmallest(root, k, result);
        return result.getFirst();
    }

    private static void doKthSmallest(TreeNode node, int k, List<Integer> result) {
        if (node != null) {
            doKthSmallest(node.left, k, result);
            --k;
            if (k == 0) {
                result.add(node.val);
                return;
            }
            doKthSmallest(node.right, k, result);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);

        kthSmallest(root, 3);
    }
}