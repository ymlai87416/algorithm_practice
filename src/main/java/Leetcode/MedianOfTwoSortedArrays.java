package Leetcode;

import java.util.Arrays;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args){
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{2,3,4,5};
        Solution s = new Solution();
        System.out.println(s.findMedianSortedArrays(nums1, nums2));
    }


    static
    class Solution {
        int[] nums1;
        int[] nums2;

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            this.nums1 = nums1;
            this.nums2 = nums2;

            if(nums1.length + nums2.length <=4)
                return solveSmall(0, nums1.length, 0, nums2.length);
            else
                return solve(0, nums1.length, 0, nums2.length);
        }

        //[start1, end1)
        public double solve(int start1, int end1, int start2, int end2){

            System.out.format("Solve: %d, %d, %d, %d\n", start1, end1, start2, end2);

            if(start1 == end1){
                int[] median = median(nums2, start2, end2);
                return (median[0]+median[1])/2;
            }
            else if (start2 == end2) {
                int[] median = median(nums1, start1, end1);
                return (median[0] + median[1]) / 2;
            } else {
                int[] median1 = median(nums1, start1, end1);
                int[] median2 = median(nums2, start2, end2);

                if (median1[1] < median2[0]) {
                    return solve(median1[0], end1, start2, median2[1]+1);
                } else if (median1[0] > median2[1]) {
                    return solve(start1, median1[1]+1, median2[0], end1);
                }
                else {
                    return solveSmall(median1[0], median1[1]+1, median2[0], median2[0]+1);
                }
            }
        }

        private double solveSmall(int start1, int end1, int start2, int end2){
            System.out.format("Solve small: %d, %d, %d, %d\n", start1, end1, start2, end2);

            int total_length = (end1-start1) + (end2-start2);

            int[] array = new int[total_length];
            for (int i = 0; i < end1-start1; ++i) array[i] = nums1[i+start1];
            for (int i = 0; i < end2-start2; ++i) array[i + end1-start1] = nums2[i+start2];
            Arrays.sort(array);
            int[] t1 = median(array, 0, total_length);
            return (array[t1[0]] + array[t1[1]]) / 2.0;
        }

        public int[] median(int[] nums, int start1, int end1){
            if(nums.length % 2 == 0)
                return new int[]{nums.length / 2-1, nums.length / 2};
            else
                return new int[]{nums.length /2, nums.length/2};
        }
    }

}
