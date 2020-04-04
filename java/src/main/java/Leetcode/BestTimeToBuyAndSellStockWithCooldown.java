package Leetcode;

/*
url: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
level: medium
solution: can only be dp
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    public static void main(String[] args){
        int[] prices = new int[]{1};
        Solution sol = new Solution();
        System.out.println(sol.maxProfit(prices));
    }

    static
    class Solution {
        int[][] dp;
        public int maxProfit(int[] prices) {
            if(prices.length == 0) return 0;

            dp = new int[prices.length][2];

            return maxProfit(prices, prices.length-1, 0);
        }

        private int maxProfit(int[] prices, int d, int hold){
            //if I hold it, either I keep holding it or I sell it day before yesterday and i buy it today
            //if I not holding any, either I have it now, and sell it or I don't have it yesterday
            if(dp[d][hold] != 0)
                return dp[d][hold];

            if(d == 0){
                if(hold > 0)
                    dp[d][hold] = -prices[0];
                else
                    dp[d][hold] = 0;
            }
            else if(d == 1){
                if(hold > 0)
                    dp[d][hold] = Math.max(maxProfit(prices, 0, 1), maxProfit(prices, 0, 0) - prices[1]);
                else
                    dp[d][hold] = Math.max(maxProfit(prices, 0, 1) + prices[1], maxProfit(prices, 0, 0));
            }
            else {
                if (hold > 0) {
                    dp[d][hold] = Math.max(maxProfit(prices, d - 1, 1), maxProfit(prices, d - 2, 0) - prices[d]);
                } else {
                    dp[d][hold] = Math.max(maxProfit(prices, d - 1, 1) + prices[d], maxProfit(prices, d - 1, 0));
                }
            }

            return dp[d][hold];
        }

    }
}
