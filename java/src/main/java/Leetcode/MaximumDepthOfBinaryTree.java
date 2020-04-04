package Leetcode;

/*
number: 104
url: https://leetcode.com/problems/maximum-depth-of-binary-tree/
level: easy
solution: recursion.
 */

public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode t = new TreeNode(5);
        t.left = new TreeNode(1);
        t.right = new TreeNode(4);
        t.right.left = new TreeNode(3);
        t.right.right=  new TreeNode(6);
        Solution s = new Solution();
        System.out.println(s.maxDepth(t));
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

    static
    class Solution {
        public int maxDepth(TreeNode root) {
            if(root == null) return 0;
            return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
        }
    }
}
