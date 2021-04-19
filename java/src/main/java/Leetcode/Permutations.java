package Leetcode;

/**
 problem: https://leetcode.com/problems/permutations/
 level: medium
 solution:

 #backtracking

 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {
    public static void main(String[] args){
        int[] nums  = new int[]{1,2,3};
        Permutations.Solution s = new Permutations.Solution();
        System.out.println(s.permute(nums));
    }

    static
    class Solution {
        int[] nums;

        public List<List<Integer>> permute(int[] nums) {
            this.nums = nums;

            ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
            //helper(nums.length, result);
            permute(nums,0, nums.length-1, result);

            return result;
        }

        //Heap's algorithm
        private void helper(int n, ArrayList<List<Integer>> r){
            if(n == 1){
                ArrayList<Integer> t  = new ArrayList<>();
                for(int i=0; i<nums.length; ++i) t.add(nums[i]);
                r.add(t);
            }
            else{
                for(int i=0; i<n-1; ++i){
                    helper(n - 1, r);
                    if(n % 2 == 0) {
                        swap(nums, i, n-1);
                    } else {
                        swap(nums, 0, n-1);
                    }
                }
                helper(n - 1, r);
            }
        }

        private void permute(int[] nums, int left, int right, ArrayList<List<Integer>> r)
        {
            if (left == right) {
                ArrayList<Integer> t  = new ArrayList<>();
                for(int i=0; i<nums.length; ++i) t.add(nums[i]);
                r.add(t);
            }
            else
            {
                for (int i = left; i <= right; i++)
                {
                    swap(nums,left,i);
                    permute(nums, left+1, right, r);
                    swap(nums,left,i);
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
