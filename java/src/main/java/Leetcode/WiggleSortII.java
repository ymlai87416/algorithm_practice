package Leetcode;

import java.util.Arrays;
import java.util.Random;

/**
number: 324
url: https://leetcode.com/problems/wiggle-sort-ii/
level: medium
solution: 1. the easy solution is to sort it and [a -> ... -> b -> c -> ... -> d ]
            now we need to use color sort algorithm....
          2. find the median, and then perform the color sort.

 #sort

 **/

public class WiggleSortII {
    public static void main(String[] args){

    }

    static
    class Solution {
        Random r = new Random();

        public void wiggleSort(int[] nums) {
            sortHelper(nums);
        }

        private void sortHelper(int[] nums){
            Arrays.sort(nums);
            int j = nums.length%2==0 ? nums.length-2 : nums.length-1;
            for(int i=1 ; i<j; i+=2, j-=2){
                swap(nums, i, j);
            }
        }


        private void linearHelper(int[] nums){
            //find median
            int k = nums.length/2;
            int m = findKthLargest(nums, k, 0, nums.length);
        }

        private int findKthLargest(int[] nums, int k, int low, int high){
            int p = partition(nums, 0, nums.length-1);

            if(p == k)
                return nums[p];
            if(p < k)
                return findKthLargest(nums, k-p, p+1, nums.length-1);
            else
                return findKthLargest(nums, k, 0, p-1);
        }

        private int partition(int[] nums, int low, int high){
            int ri = r.nextInt();
            swap(nums, ri, high);

            int ptr1 = low-1;
            int ptr2;
            for(ptr2 = low; ptr2 < high; ++ptr2){
                if (ptr1 > nums[high]) {
                    ptr1++;
                    swap(nums, ptr1, ptr2);
                }
            }
            swap(nums, ++ptr1, high);

            return ptr1;
        }

        private void swap(int[] nums, int a, int b){
            int t= nums[a];
            nums[a] = nums[b];
            nums[b] = t;
        }
    }

}
