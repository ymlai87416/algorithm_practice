package Leetcode;

/**
problem: https://leetcode.com/problems/diameter-of-binary-tree/
level: easy
solution: split recursion = O(n^2), combine to O(n)

#tree #diameter
 */
public class DiameterOfBinaryTree {
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        Solution s = new Solution();
        int result = s.maxPathBinaryTree(root);
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
        public int diameterOfBinaryTree(TreeNode root) {
            //return solution_ON2(root);
            return solution_On(root);
        }

        public int maxPathBinaryTree(TreeNode root){
            if(root == null) return 0;
            else return Math.max(maxPathBinaryTree(root.left), maxPathBinaryTree(root.right))+1;
        }

        //this is O(n^2) solution.
        public int solution_ON2(TreeNode root){
            if(root == null) return 0;

            int maxLeft = solution_ON2(root.left);
            int maxRight = solution_ON2(root.right);

            int maxLPath = maxPathBinaryTree(root.left);
            int maxRPath = maxPathBinaryTree(root.right);
            int maxCenter = maxLPath + maxRPath;

            return Math.max(Math.max(maxLeft, maxRight), maxCenter);
        }

        //this is O(n) solution
        public int solution_On(TreeNode root){
            length = Integer.MIN_VALUE;
            height(root);

            return length-1;
        }

        int length;
        public int height(TreeNode root){
            if(root == null) return 0;

            int leftH = height(root.left);
            int rightH = height(root.right);

            length = Math.max(length, 1+leftH + rightH);
            return 1+Math.max(leftH, rightH);
        }
    }
}
