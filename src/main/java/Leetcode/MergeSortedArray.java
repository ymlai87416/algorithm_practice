package Leetcode;

public class MergeSortedArray {
    public static void main(String[] args){
        int[] nums1  = {1,2,3,0,0,0};
        int[] nums2  = {2,5,6};
        Solution s = new Solution();
        s.merge(nums1, 3, nums2, nums2.length);
        for(int i=0; i<nums1.length; ++i)
            System.out.print(nums1[i] + " ");

    }

    static
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int[] t = new int[m];
            for(int i=0; i<m ; ++i)
                t[i] = nums1[i];

            int ptr1, ptr2, ptr3;
            ptr1 = ptr2 = ptr3=0;

            while(ptr1 < m || ptr2 < n){
                if(ptr1 >= m && ptr2 < n)
                    nums1[ptr3++] = nums2[ptr2++];
                else if(ptr1 < m && ptr2 >= n)
                    nums1[ptr3++] = t[ptr1++];
                else{
                    if(t[ptr1] <= nums2[ptr2])
                        nums1[ptr3++] = t[ptr1++];
                    else
                        nums1[ptr3++] = nums2[ptr2++];
                }
            }
        }
    }
}
