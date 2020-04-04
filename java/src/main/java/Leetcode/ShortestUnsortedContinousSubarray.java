package Leetcode;

/*
number: 581
url: https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
level: easy
solution: find the first index of inversion, and the last index of inversion. then [first...last] is the answer.
 */

import java.util.Arrays;
import java.util.Stack;

public class ShortestUnsortedContinousSubarray {
    public static void main(String[] args){

        Solution s = new Solution();
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        int[] nums1 = {1,2,3,4,5,6};
        int[] nums2 = {6,5,4,3,2,1};
        int result = s.findUnsortedSubarray(nums);
        System.out.println(result);
    }

    static
    class Solution {
        public int findUnsortedSubarray(int[] nums) {
            return sortHelper(nums);
        }

        //7ms
        public int sortHelper(int[] nums) {
            int[] nums2 = new int[nums.length];
            for(int i=0; i<nums.length; ++i) nums2[i] = nums[i];
            Arrays.sort(nums2);
            int a = nums.length;
            for(int i=0; i<nums.length; ++i){
                if(nums[i] == nums2[i]) --a; else break;
            }
            if (a == 0) return 0;
            for(int i=nums.length-1; i>=0; --i){
                if(nums[i] == nums2[i]) --a; else break;
            }
            return a;
        }

        //run 2 ms
        public int minmaxHelper(int[] nums) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            Stack<Integer> a = new Stack<>();

            //search from left, find min when inversion is detected
            boolean search  = false;
            for(int i=0; i<nums.length-1; ++i){
                if(nums[i] > nums[i+1])
                    search = true;
                if(search) min = Math.min(min, nums[i]);
            }
            if(search) min = Math.min(min, nums[nums.length-1]);

            if(!search) return 0;

            //search from right, find max when inversion is detected
            search  = false;
            for(int i=nums.length-1; i>=1; --i){
                if(nums[i] < nums[i-1])
                    search = true;
                if(search) max = Math.max(max, nums[i]);
            }
            if(!search) max = Math.max(max, nums[0]);

            int first = 0;
            for(int i=0; i<nums.length-1; ++i)
                if(min < nums[i]) {
                    first = i;
                    break;
                }

            int last = 0;
            for(int i=nums.length-1; i>=0; --i)
                if(max > nums[i]) {
                    last = i;
                    break;
                }

            //System.out.println(first + " " + last);

            return last-first+1;
        }

        //run in 14ms
        public int stackHelper(int[] nums) {
            int first = Integer.MAX_VALUE;
            int last = Integer.MAX_VALUE;
            Stack<Integer> a = new Stack<>();
            for(int i=0; i<nums.length; ++i){
                if(!a.empty() && a.peek() > nums[i]){
                    while(!a.empty() && a.peek() > nums[i]){
                        a.pop();
                    }
                    a.push(nums[i]);
                    first = Math.min(first, a.size()-1);
                }
                else
                    a.push(nums[i]);
            }

            a.clear();
            for(int i=nums.length-1; i>=0; --i){
                if(!a.empty() && a.peek() < nums[i]){
                    while(!a.empty() && a.peek() < nums[i]){
                        a.pop();
                    }
                    a.push(nums[i]);
                    last = Math.min(last, a.size()-1);
                }
                else
                    a.push(nums[i]);
            }

            //length is first...last => length-first+last
            //System.out.println(first + " " + last);

            if(first == Integer.MAX_VALUE && last == Integer.MAX_VALUE) return 0;
            return nums.length-first-last;
        }
    }
}
