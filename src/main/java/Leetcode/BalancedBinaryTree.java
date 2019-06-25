package Leetcode;

public class BalancedBinaryTree {
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution s = new Solution();
        System.out.println(s.isBalanced(root));
    }

    static
    class Solution {
        public boolean isBalanced(TreeNode root) {
            if(root == null) return true;

            int left = treeDepth(root.left);
            int right = treeDepth(root.right);

            if(Math.abs(left-right) > 1)
                return false;
            return isBalanced(root.left) && isBalanced(root.right);
        }

        public int treeDepth(TreeNode root){
            if(root == null) return 0;
            return Math.max(treeDepth(root.left), treeDepth(root.right))+1;
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
