package Leetcode;

/*
problem: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
level: hard
solution: straight forward is dp,
    almost 2 time, which means for a time
 */

public class BestTimeToBuyAndSellStockIII {
    public static void main(String[] args){
        int[] prices = new int[]{3,3,5,0,0,3,1,4};
        Solution sol = new Solution();
        System.out.println(sol.maxProfit(prices));
    }

    static
    class Solution {
        int[][] dp;
        public int maxProfit(int[] prices) {
            return helper2(prices);
        }

        //simple O(n^2)
        public int helper(int[] prices){
            int r = 0;

            for(int i=0; i<prices.length; ++i){
                int a1 = helperOneTime(prices, 0, i+1);
                int a2 = helperOneTime(prices, i+1, prices.length);
                if(r < a1+a2){
                    //System.out.format("i: %d, %d+%d\n", i, a1, a2);
                    r = a1+a2;
                }
            }

            return r;
        }

        public int helperOneTime(int[] prices, int start, int end){
            if(end-start == 0 ||end-start==1) return 0;

            int imin ;
            imin=prices[start];
            int r = 0;
            for(int i=start+1; i<end; ++i){
                imin = Math.min(imin, prices[i]);

                r = Math.max(r, prices[i]-imin);
            }

            return r;
        }

        //O(n) solution
        public int helper2(int[] prices){
            int[] left = helperOneTimeStart(prices);
            int[] right = helperOneTimeEnd(prices);

            int result =0;
            for(int i=0; i<prices.length; ++i){
                int l = left[i];
                int r = i+1 == prices.length ? 0 : right[i+1];
                result = Math.max(result, l + r);
            }

            return result;
        }

        public int[] helperOneTimeStart(int[] prices){
            if(prices.length == 0 ||prices.length==1){
                return new int[prices.length];
            }

            int imin ;
            imin=prices[0];
            int[] r = new int[prices.length];
            for(int i=1; i<prices.length; ++i){
                imin = Math.min(imin, prices[i]);

                r[i] = Math.max(r[i-1], prices[i]-imin);
            }

            return r;
        }

        public int[] helperOneTimeEnd(int[] prices){
            if(prices.length == 0 ||prices.length==1){
                return new int[prices.length];
            }

            int imax ;
            imax=prices[prices.length-1];
            int[] r = new int[prices.length];
            for(int i=prices.length-2; i>=0; --i){
                imax = Math.max(imax, prices[i]);

                r[i] = Math.max(r[i+1], imax-prices[i]);
            }

            return r;
        }

    }
}
