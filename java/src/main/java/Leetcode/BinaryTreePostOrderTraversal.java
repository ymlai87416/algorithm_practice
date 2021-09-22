package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 problem: https://leetcode.com/problems/binary-tree-postorder-traversal/
 level: easy
 solution: for iterative solution, study the relationship between the prev node and curr node

 #tree #dfs #post_order
 **/


public class BinaryTreePostOrderTraversal {

    public static void main(String[] args) {
        TreeNode t = new TreeNode(5);
        t.left = new TreeNode(1);
        t.right = new TreeNode(4);
        t.right.left = new TreeNode(3);
        t.right.right=  new TreeNode(6);
        Solution s = new Solution();
        System.out.println(s.postorderTraversal(t));
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
        public List<Integer> postorderTraversal(TreeNode root) {

            List<Integer> result = new ArrayList<Integer>();
            if(root == null) return result;

            Stack<TreeNode> st = new Stack<>();
            st.push(root);

            TreeNode prev = null;
            //dfs, consider back edges
            while(!st.isEmpty()){
                TreeNode current = st.peek();

                /* go down the tree in search of a leaf an if so process it
                    and pop stack otherwise move down */
                if (prev == null || prev.left == current ||
                        prev.right == current)
                {
                    if (current.left != null)
                        st.push(current.left);
                    else if (current.right != null)
                        st.push(current.right);
                    else
                    {
                        st.pop();
                        result.add(current.val);
                    }

                /* go up the tree from left node, if the child is right
                push it onto stack otherwise process parent and pop
                stack */
                }
                else if (current.left == prev)
                {
                    if (current.right != null)
                        st.push(current.right);
                    else
                    {
                        st.pop();
                        result.add(current.val);
                    }

                /* go up the tree from right node and after coming back
                from right node process parent and pop stack */
                }
                else if (current.right == prev)
                {
                    st.pop();
                    result.add(current.val);
                }

                prev = current;
            }

            return result;
        }
    }

}
