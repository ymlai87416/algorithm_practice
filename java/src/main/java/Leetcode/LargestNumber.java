package Leetcode;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/*
number: 380
problem: https://leetcode.com/problems/largest-number/
level: medium
solution: sort the array, using a special function which accept 2 string. a+b < b+a => a < b

#sort

 */

public class LargestNumber {
    public static void main(String[] args){
        //int[] nums = new int[]{41,23,87,55,50,53,18,9,39,63,35,33,54,25,26,49,74,61,32,81,97,99,38,96,22,95,35,57,80,80,16,22,17,13,89,11,75,98,57,81,69,8,10,85,13,49,66,94,80,25,13,85,55,12,87,50,28,96,80,43,10,24,88,52,16,92,61,28,26,78,28,28,16,1,56,31,47,85,27,30,85,2,30,51,84,50,3,14,97,9,91,90,63,90,92,89,76,76,67,55};
        int[] nums=new int[]{3,30,34,5,9};
        Solution s = new Solution();
        System.out.println(s.largestNumber(nums));
    }

    static
    class Solution {
        HashMap<String, String> dp;
        public String largestNumber(int[] nums) {
            dp = new HashMap<>();
            String[] strs = new String[nums.length];
            for(int i=0; i<nums.length; ++i) strs[i] = String.valueOf(nums[i]);
            Arrays.sort(strs, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return -(o1+o2).compareTo(o2+o1);
                }
            });

            return helper("", strs);
        }


        //dp not working and result in TLE
        public String helper(String s, String[] strs){
            String r = "";
            for(int i=0; i<strs.length; ++i)
                r += strs[i];
            return removeTrailingZero(r);
        }

        private String removeTrailingZero(String r){
            int i=0;
            for(; i<r.length()-1; ++i){
                if(r.charAt(i) != '0')
                    break;
            }

            return r.substring(i);
        }
    }
}
