package Leetcode;

import java.util.Arrays;

/*
problem: https://leetcode.com/problems/burst-balloons/
level: hard
solution: a dp problem
    assume we pick a k we brust the left part, brust the right part, then when I brust k, I got k * i-1 * j+1

#divide_and_conquer #dp #time_O(n^2)
 */

public class BrustBallons {
    public static void main(String[] args){
        int[] coins = new int[]{3,1,5,8};
        Solution sol= new Solution();
        System.out.println(sol.maxCoins(coins));
    }

    static
    class Solution {
        int[][] dp= new int[501][501];;

        public int maxCoins(int[] nums) {
            for(int i=0; i<dp.length; ++i)
                Arrays.fill(dp[i], 0);
            return dp(nums, 0, nums.length-1);
        }

        private int dp(int[] nums, int i,int j){
            if(i > j) return 0;

            if(dp[i][j] != 0)
                return dp[i][j];

            int maxVal = nums[i];
            for(int k=i; k<=j; ++k) {
                int t = dp(nums, i, k-1) + dp(nums, k+1, j) + getVal(nums,k) * getVal(nums,i-1) * getVal(nums,j+1);
                if(t > maxVal)
                    maxVal = t;
            }
            //System.out.format("%d %d: %d\n", i, j, maxVal);
            dp[i][j] =maxVal;
            return maxVal;
        }

        private int getVal(int[] nums, int i){
            if(i==-1 || i==nums.length) return 1;
            else return nums[i];
        }
    }
}
