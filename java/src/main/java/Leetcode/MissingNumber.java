package Leetcode;

/**
number: 268
problem: https://leetcode.com/problems/missing-number/
level: easy
solution: just mark the number as negative.

 #array #math #bit

 **/

public class MissingNumber {
    public static void main(String[] args){
        int[] a = {9,6,4,2,3,5,7,0,1};
        Solution s = new Solution();
        System.out.println(s.missingNumber(a));
    }

    static
    class Solution {
        public int missingNumber(int[] nums) {
            for(int i=0; i<nums.length; ++i){
                int a = nums[i] & 0x7fffffff;
                if(a < nums.length)
                    nums[a] |= 0x80000000;
            }

            for(int i=0; i<nums.length; ++i){
                if(nums[i] >= 0) return i;
            }
            return nums.length;
        }
    }
}
