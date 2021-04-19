package Leetcode;

/*
problem: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
level: medium
solution:

#binarySearch

 */

public class FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args){
        int[] nums = new int[] {};
        int target = 0;
        Solution s = new Solution();
        System.out.println(s.searchRange(nums, target));
    }


    static
    class Solution {

        private int lowerBoundBinarySearch(int[] nums, int target){
            int min = 0;
            int max = nums.length -1;
            int mid = 0;
            while(min < max){
                mid = (max+min)/2;
                if(target <= nums[mid])
                    max = mid;
                else
                    min = mid + 1;
            }
            if (nums[min] == target){
                return min;
            }
            return -1;
        }

        private int upperBoundBinarySearch(int[] nums, int target){
            int min = 0;
            int max = nums.length -1;
            int mid = 0;
            while(min < max){
                mid = (max+min+1)/2;    // add 1 to find mid
                if(target >= nums[mid]) // >= instead of <=
                    min = mid;          // max => min
                else
                    max = mid - 1;      // min => max
            }
            if (nums[max] == target){   // check max
                return max;
            }
            return -1;
        }

        public int[] searchRange(int[] nums, int target) {
            int[] result = new int[2];

            if(nums.length > 0) {
                result[0] = lowerBoundBinarySearch(nums, target);
                result[1] = upperBoundBinarySearch(nums, target);
            }
            else
                result = new int[]{-1, -1};

            return result;
        }
    }
}
