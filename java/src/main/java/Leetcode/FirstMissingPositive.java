package Leetcode;

/**
problem: https://leetcode.com/problems/first-missing-positive/
level: hard
solution:

#array

 */

public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] nums = new int[]{7,8,9,11,12};

        Solution s = new Solution();
        System.out.println(s.firstMissingPositive(nums));
    }

    static
    class Solution {
        public int firstMissingPositive(int[] nums) {
            for(int i=0; i<nums.length; ++i)
                if(nums[i] < 1 || nums[i] > nums.length) nums[i]=0;
            for(int i=0; i<nums.length; ++i){
                int a = nums[i] & 0x7fffffff;
                if(a == 0) continue;
                nums[a-1] |= 0x80000000;
                //System.out.println(nums[nums[i]-1]);
            }
            int result = -1 ;
            for(int i=0; i<nums.length; ++i){
                if((nums[i] & 0x80000000) == 0) {
                    result = i + 1;
                    break;
                }
            }
            if(result == -1)
                result = nums.length+1;
            return result;
        }
    }
}
