package Leetcode;

import java.util.HashMap;

/*
url: https://leetcode.com/problems/house-robber-iii/
level: medium
solution: another dp problem, now it is not array but a tree

#dp #tree #dfs
 */

public class HouseRobberIII {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        Solution s = new Solution();
        System.out.println(s.rob(root));
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
        HashMap<TreeNode, Integer> dpSkip;
        HashMap<TreeNode, Integer> dpNotSkip;
        public int rob(TreeNode root) {
            dpSkip = new HashMap<>();
            dpNotSkip = new HashMap<>();
            int r= helper(root, false);
            return r;
        }

        public int helper(TreeNode root, boolean skip){
            if(root == null) return 0;

            if(skip && dpSkip.containsKey(root)) return dpSkip.get(root);
            if(!skip && dpNotSkip.containsKey(root)) return dpNotSkip.get(root);

            //take the house
            int a = helper(root.left, true) + helper(root.right, true) + root.val;
            //skip this house
            int b = helper(root.left, false) + helper(root.right, false);

            //if(skip)
            dpSkip.put(root, b);
            //else
            dpNotSkip.put(root, Math.max(a,b));

            return skip? dpSkip.get(root) : dpNotSkip.get(root);
        }
    }
}
