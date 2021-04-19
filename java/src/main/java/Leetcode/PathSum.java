package Leetcode;

/**
number: 112
problem: https://leetcode.com/problems/path-sum/
level: easy
solution: dfs done.

#tree #dfs

 **/

public class PathSum {
    public static void main(String[] args){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        Solution sol = new Solution();
        System.out.println(sol.hasPathSum(root, 22));
    }

    static
    class Solution {
        public boolean hasPathSum(TreeNode root, int sum) {
            if(root == null) return false;
            if(root.left== null && root.right==null)
                return sum == root.val;

            return (root.left == null ? false: hasPathSum(root.left, sum -root.val)) || (root.right == null ? false: hasPathSum(root.right, sum - root.val));
        }
    }

    static
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
