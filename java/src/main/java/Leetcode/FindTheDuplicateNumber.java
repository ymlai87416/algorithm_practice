package Leetcode;

import java.util.Arrays;

/**
number: 287
problem: https://leetcode.com/problems/find-the-duplicate-number/
level: easy
solution: 1. sort it, 2. this is a cycle.

#twoPointer #binary_search

 */

public class FindTheDuplicateNumber {

    public static void main(String[] args) {
        int[] nums = new int[]{3,1,3,4,2};
        Solution sol = new Solution();
        int r = sol.findDuplicate(nums);
        System.out.println(r);
    }

    static
    class Solution {
        public int findDuplicate(int[] nums) {
            return floydHelper(nums);
        }

        private int sortHelper(int[] nums){
            Arrays.sort(nums);
            for(int i=1; i<nums.length; ++i){
                if(nums[i] == nums[i-1]) return nums[i];
            }
            return -1;
        }

        private int floydHelper(int[] nums){
            int ptr1, ptr2;
            ptr1 = 0;
            ptr2 = 0;
            do{
                ptr1 = nums[ptr1];
                ptr2 = nums[nums[ptr2]];
            }while(ptr1 != ptr2);

            //find the start of the cycle
            ptr1 = 0;
            while(ptr1 != ptr2) {
                ptr1 = nums[ptr1];
                ptr2 = nums[ptr2];
            }

            return ptr1;
        }
    }
}
