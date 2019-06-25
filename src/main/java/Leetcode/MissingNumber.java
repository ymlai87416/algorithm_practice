package Leetcode;

public class MissingNumber {
    public static void main(String[] args){
        int[] a = {9,6,4,2,3,5,7,0,1};
        Solution s = new Solution();
        System.out.println(s.missingNumber(a));
    }

    static
    class Solution {
        public int missingNumber(int[] nums) {
            boolean nE = false;
            for(int i=0; i<nums.length; ++i){
                int a = nums[i] & 0x7fffffff;
                if(a < nums.length)
                    nums[a] |= 0x80000000;
                else
                    nE = true;
            }

            for(int i=0; i<nums.length; ++i){
                if(nums[i] >= 0) return i;
            }
            return nums.length;
        }
    }
}
