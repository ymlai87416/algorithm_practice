package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
problem: https://leetcode.com/problems/binary-tree-level-order-traversal/
level: medium
solution: the index of the List<List<>> represent the level, just have to keep track what level curr node is.

#bfs

 **/
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode t = new TreeNode(5);
        t.left = new TreeNode(1);
        t.right = new TreeNode(4);
        t.right.left = new TreeNode(3);
        t.right.right=  new TreeNode(6);
        Solution s = new Solution();
        System.out.println(s.levelOrder(t));
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
        public List<List<Integer>> levelOrder(TreeNode root) {
            ArrayList<List<Integer>> r = new ArrayList<>();

            levelOrderHelper(root, r, 1);

            return r;
        }

        private void levelOrderHelper(TreeNode root, List<List<Integer>> r, int level){
            if(root == null) return;

            if(r.size() < level)
                r.add(new ArrayList<Integer>());

            r.get(level-1).add(root.val);

            levelOrderHelper(root.left, r, level+1);
            levelOrderHelper(root.right, r, level+1);
        }
    }
}
