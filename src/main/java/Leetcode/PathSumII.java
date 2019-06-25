package Leetcode;

import java.lang.reflect.Array;
import java.util.*;

public class PathSumII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        Solution s = new Solution();
        System.out.println(s.pathSum(root, 22));
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
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> a = pathSumHelper(root, sum);
            for(List<Integer> aa : a)
                Collections.reverse(aa);
            return a;
        }

        //2ms => 32.52%
        private List<List<Integer>> pathSumHelper(TreeNode root, int sum){
            //System.out.format("%d %d\n", root.val, sum);
            if(root == null) return new ArrayList<>();
            if(root.left == null && root.right == null && sum == root.val) {
                List<List<Integer>> result =  new ArrayList<>();
                List<Integer> a = new ArrayList<>();
                a.add(root.val);
                result.add(a);
                return result;
            }

            List<List<Integer>> result = new ArrayList<>();
            List<List<Integer>> temp;

            temp = root.left != null ? pathSumHelper(root.left, sum-root.val) : new ArrayList<>();
            for(List<Integer> ti : temp) {
                ti.add(root.val);
                result.add(ti);
            }
            temp = root.right != null ? pathSumHelper(root.right, sum-root.val) : new ArrayList<>();
            for(List<Integer> ti : temp) {
                ti.add(root.val);
                result.add(ti);
            }

            return result;
        }
    }
}
