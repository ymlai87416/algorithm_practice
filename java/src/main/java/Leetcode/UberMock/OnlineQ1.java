package Leetcode.UberMock;

import java.util.Arrays;

public class OnlineQ1 {

    int[] nums = null;
    int[] dp = null;
    public int rob(int[] nums) {
        this.nums = nums;
        dp = new int[nums.length];
        Arrays.fill(dp, -1);

        return helper(0);
    }

    public int helper(int cur){
        if(cur >= nums.length) return 0;
        if(dp[cur] != -1) return dp[cur];

        int a = nums[cur] + helper(cur+2);
        int b = helper(cur+1);

        dp[cur] = Math.max(a, b);
        return dp[cur];
    }

    public static void main(String[] args){

    }
}
