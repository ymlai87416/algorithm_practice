package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 problem: https://leetcode.com/problems/binary-tree-postorder-traversal/
 level: easy
 solution: pure dfs

 #tree #bfs #pre_order
 **/


public class BinaryTreePreOrderTraversal {
    public static void main(String[] args) {
        TreeNode t = new TreeNode(5);
        t.left = new TreeNode(1);
        t.right = new TreeNode(4);
        t.right.left = new TreeNode(3);
        t.right.right=  new TreeNode(6);
        Solution s = new Solution();
        System.out.println(s.preorderTraversal(t));
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
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            if(root == null) return result;

            Stack<TreeNode> st = new Stack<>();
            st.push(root);

            //pure dfs
            while(!st.isEmpty()) {
                TreeNode current = st.pop();
                result.add(current.val);

                if(current.right != null) st.push(current.right);
                if(current.left != null) st.push(current.left);   //because we want left to process first.
            }

            return result;
        }

    }
}
