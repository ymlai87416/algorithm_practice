package Leetcode;

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
