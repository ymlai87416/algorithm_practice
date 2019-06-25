package Leetcode;

public class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        //root.right = new TreeNode(20);
        //root.right.left = new TreeNode(15);
        //root.right.right = new TreeNode(7);


        Solution sol = new Solution();
        System.out.println(sol.minDepth(root));
    }

    static
    class Solution {
        public int minDepth(TreeNode root) {
            return helper(root);
        }

        public int helper(TreeNode root) {
            if(root == null)
                return 0;
            else if(root.left == null && root.right == null)
                return 1;
            else if(root.left == null)
                return helper(root.right)+1;
            else if(root.right == null)
                return helper(root.left)+1;
            else
                return Math.min(helper(root.left), helper(root.right))+1;
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