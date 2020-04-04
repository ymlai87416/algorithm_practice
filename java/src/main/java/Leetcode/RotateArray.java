package Leetcode;

/*
number: 189
url: https://leetcode.com/problems/rotate-array/
level: easy
solution: reverse the whole array, reverse the first k, reverse the n-k elements done
 */

public class RotateArray {
    public static void main(String[] args){
        int[] input = new int[]{1,2,3,4,5,6,7};
        Solution s = new Solution();
        s.rotate(input, 3);
        for(int i=0; i<input.length; ++i)
            System.out.print(input[i] + " ");
        System.out.println();
    }

    static
    class Solution {
        public void rotate(int[] nums, int k) {
            //O(1) space complexity
            k = k % nums.length;

            //reverse the array 1
            reverse(nums, 0, nums.length-1);

            //reverse the first part of the array
            reverse(nums, 0, k-1);

            //reverse the second part of the array
            reverse(nums, k, nums.length-1);
        }

        private void reverse(int[] nums, int i, int j){
            int t;
            while(i<j){
                t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
                i++;
                j--;
            }
        }
    }
}
