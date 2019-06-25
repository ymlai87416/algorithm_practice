package Leetcode;

public class MaximumSubarray {
    public static void main(String[] args){
        int[] nums = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        Solution s = new Solution();
        System.out.println(s.maxSubArray(nums));
    }


    static
    class Solution {
        public int maxSubArray(int[] nums) {
            int[] numSum = new int[nums.length];
            numSum[0] = nums[0];
            for(int i=1; i<nums.length; ++i){
                numSum[i] = nums[i] + numSum[i-1];
            }

            int result=  numSum[0];
            int min = numSum[0] < 0 ? numSum[0] : 0;

            for(int i=1; i<nums.length; ++i){
                int t = numSum[i] - min;
                //System.out.format("%d %d\n", min, t);
                if (t > result) result = t;
                min = Math.min(numSum[i] < 0 ? numSum[i] : 0 , min);
            }

            return result;
        }
    }
}
