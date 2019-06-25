package Leetcode;

public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        Solution sol = new Solution();
        int[] r = sol.productExceptSelf(nums);
        System.out.println(r);
    }

    static
    class Solution {
        public int[] productExceptSelf(int[] nums) {
            int[] left = new int[nums.length];
            int[] right = new int[nums.length];

            left[0] = nums[0];
            right[nums.length-1] = nums[nums.length-1];
            for(int i=1; i<nums.length; ++i)
                left[i] = left[i-1] * nums[i];
            for(int i=nums.length-2; i>=0; --i)
                right[i] = right[i+1] * nums[i];

            int[] r  = new int[nums.length];

            for(int i=0; i<nums.length; ++i){
                r[i] = (i>0? left[i-1]:1) * (i<nums.length-1?right[i+1]:1);
            }

            return r;
        }
    }
}
