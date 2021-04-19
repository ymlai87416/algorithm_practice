package Leetcode;

/**
number: 380
problem : https://leetcode.com/problems/path-sum-iii/
level: easy
solution:

#tree

 **/

public class PathSumIII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        Solution s = new Solution();
        System.out.println(s.pathSum(root, 8));
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
        //can be done in prefix sum?
        public int pathSum(TreeNode root, int sum) {
            if(root == null) return sum==0?1:0;
            return pathSumHelper(root, sum, false);
        }

        public int pathSumHelper(TreeNode root, int sum, boolean start) {
            int left, right, left2, right2, curr;
            left=right=left2=right2=0;
            if(!start){
                left2 = root.left == null ? 0 : pathSumHelper(root.left, sum, false);
                right2 = root.right == null ? 0 : pathSumHelper(root.right, sum, false);
            }
            left = root.left == null ? 0 : pathSumHelper(root.left, sum-root.val, true);
            right = root.right == null ? 0 : pathSumHelper(root.right, sum-root.val, true);
            curr = (sum-root.val == 0) ? 1: 0;
            //System.out.format("%d %d: %d %d %d %d %d\n", root.val, sum, left, right, left2, right2, curr);
            return left+right+left2+right2+curr;
        }
    }
}
