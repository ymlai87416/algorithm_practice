package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
problem: https://leetcode.com/problems/binary-tree-inorder-traversal/
level: medium
solution:

#tree #dfs #in_order
 **/
public class BinaryTreeInOrderTraversal {
    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.right = new TreeNode(2);
        t.right.left = new TreeNode(3);
        Solution s = new Solution();
        System.out.println(s.inorderTraversal(t));
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
        public List<Integer> inorderTraversal(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<Integer>();

            inorderIter(root, result);

            return result;
        }

        //recursion
        public void inorder(TreeNode node, List<Integer> result){
            if(node == null)
                return;
            else{
                inorder(node.left, result);
                result.add(node.val);
                inorder(node.right, result);
            }
        }

        //non-recursion
        //implementation refer to https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
        public void inorderIter(TreeNode node, List<Integer> result){
            Stack<TreeNode> st = new Stack<>();
            st.push(node);

            Stack<TreeNode> s = new Stack<TreeNode>();
            TreeNode curr = node;

            // traverse the tree
            while (curr != null || s.size() > 0)
            {

                /* Reach the left most Node of the
                curr Node */
                while (curr !=  null)
                {
                /* place pointer to a tree node on
                   the stack before traversing
                  the node's left subtree */
                    s.push(curr);
                    curr = curr.left;
                }

                /* Current must be NULL at this point */
                curr = s.pop();

                result.add(curr.val);

                /* we have visited the node and its
                   left subtree.  Now, it's right
                   subtree's turn */
                curr = curr.right;
            }
        }
    }
}
