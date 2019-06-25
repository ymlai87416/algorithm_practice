package Leetcode;

public class BestTimeToBuyAndSellStockII {
    public static void main(String[] args){
        int[] i = new int[]{1,2,3,4,5};
        Solution s = new Solution();
        int r = s.maxProfit(i);
        System.out.println(r);
    }

    //dp, but can be done in greedy
    static
    class Solution {
        int[][] dp;
        public int maxProfit(int[] prices) {
            if(prices.length == 0) return 0;
            dp = new int[prices.length][2];
            for(int i=0; i<prices.length; ++i)
                dp[i][0]= dp[i][1] = -1;

            return helper(prices, 0, 0);
        }

        public int helper(int[] prices, int ptr, int hold){
            if(ptr==prices.length-1)
                if(hold > 0) return prices[ptr]; else return 0;
            if(dp[ptr][hold] != -1) return dp[ptr][hold];
            if(hold> 0)
                return dp[ptr][hold] = Math.max(helper(prices, ptr+1, 0)+prices[ptr], helper(prices, ptr+1, hold));
            else
                return dp[ptr][hold] = Math.max(helper(prices, ptr+1, 1)-prices[ptr], helper(prices, ptr+1, hold));
        }
    }
}
