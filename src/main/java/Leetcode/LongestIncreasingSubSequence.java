package Leetcode;

public class LongestIncreasingSubSequence {
    public static void main(String[] args) {
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        //nums = new int[0];
        Solution sol = new Solution();
        int r = sol.lengthOfLIS(nums);
        System.out.println(r);
    }

    static
    class Solution {
        int[] dp;

        public int lengthOfLIS(int[] nums) {
            dp = new int[nums.length];
            for(int i=0; i<nums.length; ++i) {
                helper(nums, i);  //calculate the LIS ending at index i.
            }

            int r = 0;
            for(int i=0; i<nums.length; ++i){
                r = Math.max(r, dp[i]);
            }
            return r;
        }

        private int helper(int[] nums, int k){
            if(k < 0) return 0;
            if(dp[k] > 0) return dp[k];
            int r = 1;
            for(int i=0; i<k; ++i){
                if(nums[i] < nums[k]){
                    r = Math.max(r, helper(nums, i)+1);
                }
            }

            dp[k] = r;
            return r;
        }
    }
}
