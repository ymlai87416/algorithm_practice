package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
problem: https://leetcode.com/problems/binary-tree-level-order-traversal/
level: medium
solution:

#tree #bfs
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

            inorder(root, result);

            return result;
        }

        public void inorder(TreeNode node, List<Integer> result){
            if(node == null)
                return;
            else{
                inorder(node.left, result);
                result.add(node.val);
                inorder(node.right, result);
            }
        }
    }
}
