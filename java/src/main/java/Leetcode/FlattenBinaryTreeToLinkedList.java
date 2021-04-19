package Leetcode;

/*
number: 114
problem: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
level: medium
solution: traverse the tree with a linked list

#tree #dfs
 */

public class FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        Solution s = new Solution();
        s.flatten(root);
        System.out.println();
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

    //enhance using start, end ptr

    static
    class Solution {
        public void flatten(TreeNode root) {
            helper(root);
        }

        private TreeNode helper(TreeNode root){
            if(root == null) return null;

            TreeNode left = helper(root.left);
            TreeNode right = helper(root.right);
            TreeNode result = root;
            root.left = null;
            root.right = left;  //left tree start
            while(root.right != null)
                root = root.right;
            root.right = right;

            return result;
        }
    }
}
