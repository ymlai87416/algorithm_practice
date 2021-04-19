package Leetcode;

/**
number: 617
problem: https://leetcode.com/problems/merge-two-binary-trees/
level: easy
solution: just merge the tree

#tree

 **/

public class MergeTwoBinaryTree {
    static
    class Solution {
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            return helper(t1, t2);
        }

        private TreeNode helper(TreeNode t1, TreeNode t2){
            if(t1 == null && t2 == null) return null;
            int v = (t1 == null? 0: t1.val) + (t2 == null? 0: t2.val);
            TreeNode r = new TreeNode(v);
            r.left = helper(t1 == null? null: t1.left, t2 == null? null: t2.left);
            r.right = helper(t1 == null? null: t1.right, t2 == null? null: t2.right);

            return r;
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
