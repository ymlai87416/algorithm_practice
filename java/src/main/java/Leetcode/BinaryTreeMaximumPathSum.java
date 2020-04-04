package Leetcode;

import java.util.HashMap;

/*
url: https://leetcode.com/problems/binary-tree-maximum-path-sum/
level: hard
solution: return 2 numbers at once, one it the max path containing the root, one is not, just within the tree.
 */
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

            return helper2(root);
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

        private int helper2(TreeNode root){
            return helper2Ex(root)[0];
        }

        private int[] helper2Ex(TreeNode root){
            if(root == null) return new int[]{0, 0};

            if(root.left == null && root.right == null)
                return new int[]{root.val,root.val};

            int[] left, right, result;
            left = null; right = null;
            if(root.left != null)
                left = helper2Ex(root.left);

            if(root.right != null)
                right = helper2Ex(root.right);

            result = new int[2];
            //path which from root to left / right tree
            result[1] = Math.max(0, Math.max(left == null ? 0: left[1], right==null? 0: right[1])) + root.val;

            //path which pass thru the root. this path can only exists in the subtree.
            result[0] = root.val + Math.max(0, left == null ? 0:left[1]) + Math.max(0, right == null ? 0:right[1]);
            if(left != null && left[0] > result[0])
                result[0] = left[0];
            if(right != null && right[0] > result[0])
                result[0] = right[0];

            return result;
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
