package Leetcode;

import java.util.Arrays;

/**
number: 33
problem: https://leetcode.com/problems/search-in-rotated-sorted-array/
level: medium
solution: I find the inversion in O(logn), and then I can find the value in O(logn)

#array #binarySearch

 **/

public class SearchInRotatedSortedArray {
    public static void main(String[] args){
        int[] nums = new int[]{3,1};
        int target = 1;
        Solution s = new Solution();
        System.out.println(s.search(nums, target));
    }


    static
    class Solution {


        public int search(int[] nums, int target) {
            //search for inversion

            //start and end are inclusive
            int start, end, mid, inv=-1;
            start= 0;
            end = nums.length-1;
            while(start < end){
                //System.out.println("debug " + start + " " + end);
                mid = (start+end)/2;
                if(mid > 0 && nums[mid-1] > nums[mid]) {
                    inv = mid - 1;
                    break;
                }
                else if(mid < nums.length-1 && nums[mid] > nums[mid+1]) {
                    inv = mid;
                    break;
                }
                else if(nums[start] >= nums[mid])  //start->mid-1 has inversion
                    end = mid-1;
                else //mid+1 -> end has inversion
                    start = mid+1;
            }

            if(start == end) inv = start;
            if(start > end) inv = -1;

            System.out.println("pivot point: " + inv);

            int retVal = -1;

            if(inv == -1){
                retVal =Arrays.binarySearch(nums, target);
            }
            else {
                //search for index within [0...inv-1]
                retVal = Arrays.binarySearch(nums, 0, inv+1, target);
                //search for index within [inv...end]
                if(retVal < 0) {
                    retVal = Arrays.binarySearch(nums, inv+1, nums.length, target);
                }
            }

            return retVal < 0 ? -1 : retVal;
        }
    }
}
