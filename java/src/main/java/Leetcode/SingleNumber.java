package Leetcode;

/**
number: 136
problem: https://leetcode.com/problems/single-number/
level: easy
solution: just xor all the element, the duplicate element will be xor away.

#hashTable #bit

 **/

public class SingleNumber {
    public static void main(String[] args) {
        int[] prices = new int[]{4,1,2,1,2};
        Solution s = new Solution();
        System.out.println(s.singleNumber(prices));
    }

    //enhance using start, end ptr

    static
    class Solution {
        public int singleNumber(int[] nums) {
            int r = 0;
            for(int i=0; i<nums.length; ++i)
                r = r ^ nums[i];

            return r;
        }
    }
}
