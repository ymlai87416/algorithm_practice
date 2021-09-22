package Leetcode;

/**
problem: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
level: medium
solution: the index of the List<List<>> represent the level, just have to keep track what level curr node is.

#stack #tree #bfs

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode (3);
        root.left = new TreeNode(9);
        root.right = new TreeNode (20);
        root.right.left = new TreeNode (15);
        root.right.right = new TreeNode (7);
        Solution sol = new Solution();
        List<List<Integer>> r = sol.zigzagLevelOrder(root);
        System.out.println(r);
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
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> r = new ArrayList<>();

            traverse(root, r, 0);

            for(int i=1; i<r.size(); i+=2){
                List<Integer> t = r.get(i);
                Collections.reverse(t);
            }

            return r;
        }

        private void traverse(TreeNode root, List<List<Integer>> r, int level){
            if(root == null) return;
            if(r.size() == level) r.add(new ArrayList<>());

            r.get(level).add(root.val);
            traverse(root.left, r, level+1);
            traverse(root.right, r, level+1);
        }
    }
}
