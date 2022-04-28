package Leetcode.Biweek70;

import java.util.Arrays;

/**
 problem: https://leetcode.com/problems/minimum-cost-of-buying-candies-with-discount/
 level: easy
 solution: sorting

 #sort
 **/


public class MinimumCostOfBuyingCandiesWithDiscount {

    public int minimumCost(int[] cost) {
        Arrays.sort(cost);

        int t = 0;

        for(int i=cost.length-1; i>=0; i-=3){
            t += cost[i];
            if (i-1 >= 0) t+=cost[i-1];
        }

        return t;
    }

    public static void main(String[] args){
        int[] c1 = {1,2,3};
        int[] c2 = {6,5,7,9,2,2};
        int[] c3 = {5,5};
        MinimumCostOfBuyingCandiesWithDiscount s =new MinimumCostOfBuyingCandiesWithDiscount();

        System.out.println(s.minimumCost(c1));
        System.out.println(s.minimumCost(c2));
        System.out.println(s.minimumCost(c3));
    }
}
