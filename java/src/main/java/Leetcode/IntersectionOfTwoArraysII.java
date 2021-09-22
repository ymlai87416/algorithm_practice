package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
number: 350
problem: https://leetcode.com/problems/intersection-of-two-arrays-ii/
level: easy
solution: just sort the array and do it, or store it in hashmap, the naive way is to scan both array O(mn)

        What if the given array is already sorted? How would you optimize your algorithm?
            sorting O(nlogn)
            then just use 2 ptr to match

        What if nums1's size is small compared to nums2's size? Which algorithm is better?
            hash table is better?

        What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
            then just load nums1 and do a scan on num2

#hash_table #two_pointer #binary_search #sort
 */

public class IntersectionOfTwoArraysII {
    public static void main(String[] args){
        int[] nums1= new int[]{1,2,2,1};
        int[] nums2 = new int[]{2,2};

        Solution s = new Solution();
        System.out.println(s.intersect(nums1, nums2));
    }

    static
    class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            List<Integer> r = new ArrayList<>();

            int ptr1, ptr2;
            ptr1 = 0; ptr2 = 0;

            while(ptr1 < nums1.length && ptr2 < nums2.length){
                if(nums1[ptr1] == nums2[ptr2]){
                    r.add(nums1[ptr1]);
                    ptr1++;
                    ptr2++;
                }
                else if(nums1[ptr1] < nums2[ptr2]){
                    ptr1++;
                }
                else if(nums2[ptr2] < nums1[ptr1]){
                    ptr2++;
                }
            }

            int[] rr = new int[r.size()];
            for(int i=0; i<r.size(); ++i)
                rr[i] = r.get(i);
            return rr;
        }
    }
}
