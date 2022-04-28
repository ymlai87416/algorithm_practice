package Leetcode.BloombergMock;

import java.util.Arrays;

public class OnlineQ2 {

    int[] cost = null;
    int[] dp = null;
    public int minCostClimbingStairs(int[] cost) {
        this.cost= cost;
        this.dp = new int[cost.length];
        Arrays.fill(dp, -1);

        int from0 = helper(0);
        int from1 = helper(1);

        return Math.min(from0, from1);
    }

    private int helper(int cur){
        if(cur >= cost.length) return 0;
        if(dp[cur] != -1) return dp[cur];
        int a = helper(cur+1);
        int b = helper(cur+2);

        dp[cur]=  cost[cur] + Math.min(a, b);
        return dp[cur];
    }

    public static void main(String[] args){

    }
}
