package Leetcode;

import DataStructure.JavaPriorityQueue.UVA1203;

public class KthSmallestElementInABst {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        int k=3;

        Solution s = new Solution();
        System.out.println(s.kthSmallest(root, k));
    }


    static
    class Solution {
        public int kthSmallest(TreeNode root, int k) {
            int leftSize = findTreeSize(root.left);
            if (leftSize == k-1)
                return root.val;
            else if (leftSize > k-1)
                return kthSmallest(root.left, k);
            else
                return kthSmallest(root.right, k-1-leftSize);
        }

        private int findTreeSize(TreeNode root){
            if(root == null) return 0;
            return findTreeSize(root.left) + 1 + findTreeSize(root.right);
        }
    }

    static
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
