package Leetcode;

/**
 problem: https://leetcode.com/problems/permutations-ii/
 level: medium
 solution:

 #backtracking

 **/

import java.util.ArrayList;
import java.util.List;

public class PermutationII {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        Solution s = new Solution();
        System.out.println(s.permuteUnique(nums));
    }

    static
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
            //helper(nums.length, result);
            permute(nums, 0, nums.length - 1, result);

            return result;
        }

        //see  https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
        private boolean shouldSwap(int[] nums, int start, int curr) {
            for (int i = start; i < curr; ++i)
                if (nums[i] == nums[curr])
                    return false;

            return true;
        }


        private void permute(int[] nums, int left, int right, ArrayList<List<Integer>> r) {
            if (left == right) {
                ArrayList<Integer> t = new ArrayList<>();
                for (int i = 0; i < nums.length; ++i) t.add(nums[i]);
                r.add(t);
            } else {
                for (int i = left; i <= right; i++) {
                    boolean check = shouldSwap(nums, left, i);
                    if (check) {
                        swap(nums, left, i);
                        permute(nums, left + 1, right, r);
                        swap(nums, left, i);
                    }
                }
            }
        }

        private void swap(int[] input, int a, int b) {
            int tmp = input[a];
            input[a] = input[b];
            input[b] = tmp;
        }

    }
}
