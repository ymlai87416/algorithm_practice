package Leetcode;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.TreeSet;

public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{0};
        //nums = new int[]{-2};

        Solution sol = new Solution();
        int r = sol.maxProduct(nums);

        System.out.println(r);
    }

    static
    class Solution {

        public int maxProduct(int[] nums) {
            return maxProductWithZeros(nums, 0, nums.length-1);
        }

        private int maxProductWithZeros(int[] nums, int i, int j){
            int prev = -1;
            int r = Integer.MIN_VALUE;
            for(int u=i; u<=j; ++u){
                if(nums[u] == 0){
                    r = Math.max(0, r);
                    if(prev != -1) {
                        r = Math.max(r, maxProductNonZeros(nums, prev, u - 1));
                        prev = -1;
                    }
                }
                else if(prev == -1)
                    prev = u;
            }
            if(prev != -1)
                r = Math.max(r, maxProductNonZeros(nums, prev, j));

            return r;
        }

        private int maxProductNonZeros(int[] nums, int i, int j){

            int numNeg = 0;
            int first = -1;
            int last = -1;

            if(i == j) return nums[i];

            for(int u=i; u<=j; ++u){
                if(nums[u] < 0){
                    numNeg++;
                    if(first == -1)
                        first= u;
                    last = u;
                }
            }

            if(numNeg % 2 == 0){
                int a = 1;
                for(int u=i; u<=j; ++u)
                    a *= nums[u];
                return a;
            }
            else{
                int a = 1;
                int b = 1;

                for(int u=i; u<last; ++u)
                    a *= nums[u];
                for(int u=first+1; u<=j; ++u)
                    b *= nums[u];

                return Math.max(a, b);
            }
        }
    }
}
