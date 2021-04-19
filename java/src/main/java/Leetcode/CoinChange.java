package Leetcode;

/*
url: https://leetcode.com/problems/coin-change/
level: medium
solution: fewest number of coin change means a classic dp

#dp
 */

public class CoinChange {
    public static void main(String[] args) {
        int[] nums = new int[]{1};
        //nums = new int[0];
        Solution sol = new Solution();
        int r = sol.coinChange(nums, 0);
        System.out.println(r);
    }

    static
    class Solution {
        int[] dp;
        public int coinChange(int[] coins, int amount) {
            dp = new int[amount+1];
            dp[0] = 0;
            for(int i=0; i<coins.length; ++i)
                if(coins[i] <= amount)
                    dp[coins[i]] = 1;

            int r= helper(coins, amount);
            return r;
        }

        private int helper(int[] coins, int amount){
            //System.out.println(amount);
            if(amount == 0) return 0;
            if(dp[amount] != 0) return dp[amount];

            int r = Integer.MAX_VALUE;
            for(int i=0; i<coins.length; ++i){
                if(amount >= coins[i]) {
                    int t = helper(coins, amount - coins[i]);
                    if(t != -1)
                        r = Math.min(r,  t + 1);
                }
            }

            if(r == Integer.MAX_VALUE)
                r = -1;
            dp[amount] = r;
            return r;
        }
    }
}
