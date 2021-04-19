package Leetcode;
/*
problem: https://leetcode.com/problems/diameter-of-binary-tree/
level: easy
solution: just recursion.

#tree
 */
public class DiameterOfBinaryTree {
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        Solution s = new Solution();
        int result = s.diameterOfBinaryTree(root);
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
        public int maxPathBinaryTree(TreeNode root){
            if(root == null) return 0;
            else return Math.max(maxPathBinaryTree(root.left), maxPathBinaryTree(root.right))+1;
        }

        public int diameterOfBinaryTree(TreeNode root) {
            if(root == null) return 0;

            int maxLeft = diameterOfBinaryTree(root.left);
            int maxRight = diameterOfBinaryTree(root.right);

            int maxLPath = maxPathBinaryTree(root.left);
            int maxRPath = maxPathBinaryTree(root.right);
            int maxCenter = maxLPath + maxRPath;

            return Math.max(Math.max(maxLeft, maxRight), maxCenter);
        }
    }
}
