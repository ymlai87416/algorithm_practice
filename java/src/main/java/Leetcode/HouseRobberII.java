package Leetcode;

/**
problem: https://leetcode.com/problems/house-robber-ii/
level: medium
solution: now the house form a circle. but it can easily break it back into a line.
    just run 2 times

#dp
 */

public class HouseRobberII {
    public static void main(String[] args){
        int[] input = {2,1,1,2};
        Solution s = new Solution();
        System.out.println(s.rob(input));
    }


    static
    class Solution {
        int[] dp;
        public int rob(int[] nums) {
            dp = new int[nums.length];
            if(nums.length == 0)
                return 0;
            for(int i=0; i<nums.length; ++i) dp[i] = -1;
            int a = robHelper(nums, 1, nums.length);                    //skip house 0
            for(int i=0; i<nums.length; ++i) dp[i] = -1;
            int b = robHelper(nums, 2, nums.length-1)+nums[0];   //include house 0;
            return Math.max(a,b);
        }

        public int robHelper(int[] nums, int pos, int length){
            if(pos >= length) return 0;
            if(dp[pos] != -1) return dp[pos];
            dp[pos] = Math.max(robHelper(nums, pos+1, length), nums[pos] + robHelper(nums, pos+2, length));
            return dp[pos];
        }
    }
}
