package Leetcode;

/**
number: 283
problem: https://leetcode.com/problems/move-zeroes/
level: easy
solution: 2 pointers, 1 move 1 step each time, another if i encounter a non-zero, i copy the number to this location.
            mark the rest of it zero.

 #array #twoPointers

 **/

public class MoveZeros {
    public static void main(String[] args){
        int[] nums = new int[] {0,1,0,3,12};
        Solution s = new Solution();
        s.moveZeroes(nums);
    }


    static
    class Solution {
        public void moveZeroes(int[] nums) {
            int ptr = 0;
            for(int i=0; i<nums.length; ++i){
                if(nums[i] != 0){
                    nums[ptr] = nums[i];
                    ptr++;
                }
            }

            for(; ptr < nums.length; ++ptr)
                nums[ptr] = 0;
        }
    }
}
