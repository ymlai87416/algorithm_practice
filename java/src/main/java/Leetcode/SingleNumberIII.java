package Leetcode;

import java.util.Random;

/**
number: 260
problem: https://leetcode.com/problems/single-number-iii/
level: medium
solution: there are 2 numbers here, xor will give a ^ b.
        Now we have to seperate the number into 2 set, and xor it again.

 #bit

 **/

public class SingleNumberIII {
    public static void main(String[] args){
        int[] arr = new int[] {1,2,1,3,2,5};
        Solution sol = new Solution();
        int[] result= sol.singleNumber(arr);
        System.out.println(result[0] + " " + result[1]);
    }

    static
    class Solution {
        Random r = new Random();

        public int[] singleNumber(int[] nums) {
            int result = nums[0];
            for(int i=1; i<nums.length; ++i)
                result = result ^ nums[i];

            //now we got a ^ b;
            //xor property, 1 for bin(a)_i != bin(b)_i
            int sepBit = 0;
            for(int i=0; i<32; ++i){
                if((result & 1 << i) != 0)
                    sepBit = i;
            }

            int xor1=0, xor2=0;
            for(int i=0; i<nums.length; ++i){
                if((nums[i] & 1 << sepBit) != 0)
                    xor1 = xor1 ^ nums[i];
                else
                    xor2 = xor2 ^ nums[i];
            }

            return new int[]{xor1, xor2};
        }
    }
}
