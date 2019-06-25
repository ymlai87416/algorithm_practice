package Leetcode;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.TreeSet;

public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{-5,2,4,1,-2,2};

        Solution sol = new Solution();
        int r = sol.maxProduct(nums);

        System.out.println(r);
    }

    static
    class Solution {

        public int maxProduct(int[] nums) {
            TreeSet<Integer> zeros = new TreeSet<Integer>();
            BigInteger aProd[] = new BigInteger[nums.length+1];

            aProd[0] = BigInteger.ONE;
            for(int i=0; i<nums.length; ++i){
                aProd[i+1] = aProd[i].multiply(nums[i] == 0 ? BigInteger.ONE: BigInteger.valueOf(nums[i]));
                if(nums[i] == 0) zeros.add(i+1);
            }

            BigInteger max = BigInteger.valueOf(Integer.MIN_VALUE);
            for(int i=1; i<aProd.length; ++i){
                for(int j=0; j<i; ++j){
                    BigInteger subArrayProd = aProd[i].divide(aProd[j]);
                    boolean arrayContainsZeros = zeros.contains(i) || (zeros.lower(i) != null && zeros.lower(i) > j);

                    if(arrayContainsZeros)
                        subArrayProd = BigInteger.ZERO;

                    if(subArrayProd.compareTo(max) > 0){
                        max = subArrayProd;
                    }
                }
            }

            return max.intValue();
        }
    }
}
