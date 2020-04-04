package Leetcode;

/*
problem: https://leetcode.com/problems/convert-bst-to-greater-tree/
level: Easy
 */

public class ConvertBSTToGreaterTree {
    public static void main(String[] args){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);

        Solution s = new Solution();
        TreeNode result = s.convertBST(root);
        System.out.println(result);
    }

    static
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    static
    class Solution {
        int curSum  = 0;
        public TreeNode convertBST(TreeNode root) {
            //traverse the tree using right->center->left, and then add it
            curSum = 0;
            return convertBSTHelper(root);
        }

        public TreeNode convertBSTHelper(TreeNode root){
            if(root == null) return null;
            convertBSTHelper(root.right);
            int temp = root.val;
            root.val += curSum;
            curSum += temp;
            convertBSTHelper(root.left);

            return root;
        }
    }
}
