package Leetcode;

import java.util.HashMap;

public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution s = new Solution();
        System.out.println(s.maxPathSum(root));
    }

    static
    class Solution {
        HashMap<TreeNode, Integer> dp;

        public int maxPathSum(TreeNode root) {

            return helper(root);
        }

        private int calcMaxPath(TreeNode root){
            if(root == null) return 0;
            int l=0, r=0;
            l = calcMaxPath(root.left);
            r = calcMaxPath(root.right);
            int val = Math.max(0, Math.max(l, r)) + root.val;
            dp.put(root, val);
            return val;
        }

        private int helper(TreeNode root){
            dp = new HashMap<>();
            calcMaxPath(root);

            return helperEx(root);
        }

        private int helperEx(TreeNode root){
            if(root.left == null && root.right == null)
                return root.val;

            int rr = root.val
                    + Math.max(0, (root.left == null ? 0 : dp.get(root.left)))
                    + Math.max(0, (root.right == null ? 0 : dp.get(root.right)));
            if(root.left != null)
                rr = Math.max(rr, helperEx(root.left));
            if(root.right != null)
                rr = Math.max(rr, helperEx(root.right));

            return rr;
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
