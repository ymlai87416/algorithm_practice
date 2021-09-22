package Leetcode;

/**
problem: https://leetcode.com/problems/house-robber/
level: easy
solution: this is a simple dp

#dp

 */

public class HouseRobber {
    public static void main(String[] args){
        int[] input = {2,7,9,3,1};
        Solution s = new Solution();
        System.out.println(s.rob(input));
    }

    static
    class Solution {
        int[] dp;
        public int rob(int[] nums) {
            dp = new int[nums.length];
            for(int i=0; i<nums.length; ++i) dp[i] = -1;
            return robHelper(nums, 0);
        }

        public int robHelper(int[] nums, int pos){
            if(pos >= nums.length) return 0;
            if(dp[pos] != -1) return dp[pos];
            dp[pos] = Math.max(robHelper(nums, pos+1), nums[pos] + robHelper(nums, pos+2));
            return dp[pos];
        }
    }
}
