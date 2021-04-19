package Leetcode;

import java.util.Arrays;

/**
number: 81
problem: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
level: medium
solution: now duplicate is allowed, the previous method cannot be done. because when mid = start / end, then it comes
    to linear search [start, end] to find the pivot.

#array #binarySearch

 **/

public class SearchInRotatedSortedArrayII {
    public static void main(String[] args){
        int[] nums = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        nums = new int[]{1,1};
        int target = 0;
        Solution s = new Solution();
        System.out.println(s.search(nums, target));
    }


    static
    class Solution {


        private int pivotSearchHelper(int[] nums, int start, int end){
            //System.out.format("start: %d end: %d\n", start, end);
            if(start >= end) return -1;

            int mid = (start+end)/2;

            if(mid > 0 && nums[mid-1] > nums[mid]) {
                return mid-1;
            }
            else if(mid < nums.length-1 && nums[mid] > nums[mid+1]){
                return mid;
            }
            else if(nums[start] > nums[mid])
                return pivotSearchHelper(nums, start, mid-1);
            else if(nums[mid] > nums[end])
                return pivotSearchHelper(nums, mid+1, end);
            else if(nums[start] == nums[mid] && nums[mid] == nums[end]){
                //cannot tell which side is it.... may be an iterative linear search is better than correct recursive
                int r = pivotSearchHelper(nums, start, mid-1);
                if(r != -1) return r;
                else{
                    r = pivotSearchHelper(nums, mid+1, end);
                    return r;
                }
            }
            else
                return -1;
        }

        //4ms -> 43.44%
        private boolean recursiveHelper(int[] nums, int target){
            int pivot = pivotSearchHelper(nums, 0, nums.length-1);

            System.out.println("pivot point: " + pivot);

            return binarySearchWithPivot(nums, pivot, target);
        }

        //still 4ms -> 43.44%
        private boolean iterativeHelper(int[] nums, int target){
            int start, end, mid, pivot=0;
            start = 0; end= nums.length-1;

            while(start < end){
                mid = (start+end)/2;

                if(mid > 0 && nums[mid-1] > nums[mid]) {
                    pivot = mid-1;
                    break;
                }
                else if(mid < nums.length-1 && nums[mid] > nums[mid+1]){
                    pivot = mid;
                    break;
                }
                else if(nums[start] == nums[mid] && nums[mid] == nums[end]){
                    pivot = -1;
                    for(int i=start+1; i<end; ++i){
                        if(nums[i-1] > nums[i]) {
                            pivot = i - 1;
                            break;
                        }
                    }
                    break;
                }
                else if(nums[start] > nums[mid])
                    end = mid-1;
                else
                    start = mid+1;

            }

            if(start > end) pivot = -1;

            System.out.println("pivot point: " + pivot);

            return binarySearchWithPivot(nums, pivot, target);
        }

        private boolean binarySearchWithPivot(int[] nums, int pivot, int target){
            if (pivot == -1)
                return Arrays.binarySearch(nums, 0, nums.length, target) >= 0;
            else {
                //find the element in left array and right array
                int a = Arrays.binarySearch(nums, 0, pivot+1, target);
                if (a >= 0) return true;
                a = Arrays.binarySearch(nums, pivot + 1, nums.length, target);
                if (a >= 0) return true;
                return false;
            }
        }

        public boolean search(int[] nums, int target) {
            return iterativeHelper(nums, target);
        }
    }
}
