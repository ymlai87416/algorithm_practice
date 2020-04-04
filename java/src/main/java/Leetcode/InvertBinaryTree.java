package Leetcode;

/*
number: 226
url: https://leetcode.com/problems/invert-binary-tree/
level: easy
solution: just flip
 */

public class InvertBinaryTree {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        JumpGame.Solution s = new JumpGame.Solution();
        System.out.println(s.canJump(nums));
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
        public TreeNode invertTree(TreeNode root) {
            if(root == null) return null;

            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            invertTree(root.left);
            invertTree(root.right);

            return root;
        }
    }
}
