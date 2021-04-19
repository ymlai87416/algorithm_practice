package Leetcode;

import java.util.Arrays;

/**
number: 4
problem: https://leetcode.com/problems/median-of-two-sorted-arrays/
level: hard
solution: binary search for i such that. given i, j can be calculated.
        for         A[i-1],  A[i]
                    B[j-1], B[j]

        s.t.        A[i-1] < B[j]
                    B[j-1] < A[i]

 #array #binarySearch #divideAndConquer

 **/

public class MedianOfTwoSortedArrays {
    public static void main(String[] args){
        int[] nums1 = new int[]{4,5};
        int[] nums2 = new int[]{1,2,3};
        Solution s = new Solution();
        System.out.println(s.findMedianSortedArrays(nums1, nums2));
    }


    static
    class Solution {
        int[] nums1;
        int[] nums2;

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if(nums1.length < nums2.length) {
                this.nums1 = nums1;
                this.nums2 = nums2;
            }
            else{
                this.nums1 = nums2;
                this.nums2 = nums1;
            }

            if(this.nums2.length == 0){
                if(this.nums1.length % 2 == 1) return this.nums1[this.nums1.length/2];
                else return (this.nums1[this.nums1.length/2-1] + this.nums1[this.nums1.length/2])/2;
            }
            return solve();
        }

        public double solve(){

            int min=0;
            int max=nums1.length;
            while(min <= max){
                int mid = (min+max)/2;

                /*
                    left side is either equal to right side or 1 element more
                    A[i-1] A[i]
                    B[j-1] B[j]
                 */

                int j = (nums1.length + nums2.length + 1) / 2 - mid;
                // B[j-1] > A[i] => then A[i] is too small to be median
                if(mid < nums1.length && nums2[j-1] > nums1[mid]){
                    min = mid+1;
                }
                // B[j] < A[i-1] => then A[i] is too big to be median
                else if(mid> 0 && nums2[j] < nums1[mid-1]) {
                    max = mid-1;
                }
                else {
                    int maxLeft, minRight;

                    if(mid ==0)
                        maxLeft = nums2[j-1];
                    else if(j == 0)
                        maxLeft = nums1[mid-1];
                    else
                        maxLeft = Math.max(nums1[mid -1], nums2[j-1]);

                    //very compact shit!!!
                    if((nums1.length + nums2.length) % 2 == 1) return maxLeft;

                    if(mid == nums1.length)
                        minRight = nums2[j];
                    else if(j == nums2.length)
                        minRight = nums1[mid];
                    else
                        minRight = Math.min(nums1[mid], nums2[j]);

                    return (maxLeft + minRight) / 2.0;
                }
            }

            return 0;
        }
    }

}
