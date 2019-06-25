package Leetcode;

public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        int[] prices = new int[]{1,2,3,4,5};
        Solution s = new Solution();
        System.out.println(s.maxProfit(prices));
    }

    //enhance using start, end ptr

    static
    class Solution {
        public int maxProfit(int[] prices) {
            return linearHelper(prices);
        }

        private int dpHelper(int[] prices) {

            if(prices.length == 0 ||prices.length==1) return 0;

            int imin = 0;
            imin=prices[0];
            int r = 0;
            for(int i=1; i<prices.length; ++i){
                imin = Math.min(imin, prices[i]);

                //System.out.format("%d %d\n", imax, imin);
                r = Math.max(r, prices[i]-imin);
            }

            return r;
        }

        private int linearHelper(int[] prices){
            int r = 0;
            //do daytrade
            for(int i=1; i<prices.length; ++i){
                r += Math.max(0, prices[i]-prices[i-1]);
            }

            return r;
        }
    }
}
