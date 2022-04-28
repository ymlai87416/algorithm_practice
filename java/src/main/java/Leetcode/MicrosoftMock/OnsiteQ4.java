package Leetcode.MicrosoftMock;

import java.util.Arrays;

public class OnsiteQ4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int k = (m+n)/2;

        //because it is sorted, we can take a pivot

        int start = 0;
        int end = nums1.length;


        while(start< end){
            int mid = (start+end)/2;
            int m1 = nums1[mid];

            int mx = nums2[k-mid-1];
            int mx2 = nums2[k-mid];

            if(mx > m1)
                end = mid; //this is too small
            else if(mx2 < m1)
                end = mid; //this is too big
        }
    return 0;
    }



    public static void main(String[] args){

    }
}
