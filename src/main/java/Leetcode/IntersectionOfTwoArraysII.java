package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
