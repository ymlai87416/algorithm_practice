package Leetcode;

/*
problem: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
level: Easy

#tree #dfs

 */
public class ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args){
        int[] input = {2,2,1,1,1,2,2};
        Solution s = new Solution();
        System.out.println(s.sortedArrayToBST(input));
    }

    static
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    static
    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return helper(nums, 0, nums.length-1);
        }

        public TreeNode helper(int[] nums, int start, int end){
            if(start > end) return null;
            int mid =(start+end)/2;
            TreeNode r = new TreeNode(nums[mid]);
            r.left = helper(nums, start, mid-1);
            r.right = helper(nums, mid+1, end);

            return r;
        }
    }
}
