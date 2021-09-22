package Leetcode;

/**
number: 114
problem: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
level: medium
solution: traverse the tree with a linked list
    for O(1), we just move left to the right and right to the end of the rightmost of left tree.

#tree #dfs #constant_space
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
            //helper(root);
            helperO1Space(root);
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

        private TreeNode helperO1Space(TreeNode root){
            if(root == null) return null;

            TreeNode curr, temp, next;
            curr = root;

            while(curr!=null){
                if(curr.left != null) {
                    temp = curr.right;
                    curr.right = curr.left;
                    curr.left = null;

                    next=curr;

                    //now move the right to the right most
                    while(next.right != null)
                        next = next.right;
                    next.right = temp;
                }

                //now move it to next level
                curr = curr.right;
            }

            return root;
        }
    }
}
