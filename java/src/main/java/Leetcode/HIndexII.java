package Leetcode;

import java.util.Arrays;

/*
url: https://leetcode.com/problems/h-index-ii/
level: medium
solution: already sort, use the sorted approach, instead of allocating O(n) space.

#binarySearch

 */

public class HIndexII {
    public static void main(String[] args){
        int[] citations = new int[]{3,0,6,1,5};
        Solution s = new Solution();
        System.out.println(s.hIndex(citations));
    }

    static
    class Solution {
        public int hIndex(int[] citations) {
            return helper(citations);
        }

        //O(nlogn)
        public int helper(int[] citations) {
            int start=0;
            int end = citations.length-1;

            //case 1 1 1 1 1
            while(start<= end){
                int mid = (start+end)/2;
                int target= citations.length-mid;
                if(citations[mid] == target)
                    return target;
                if(citations[mid] > target)
                    end=  mid-1;
                else
                    start = mid+1;
            }

            return citations.length-start;
        }
    }
}
