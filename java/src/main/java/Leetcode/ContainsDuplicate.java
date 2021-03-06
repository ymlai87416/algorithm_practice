package Leetcode;

/*
problem: https://leetcode.com/problems/contains-duplicate/
level: easy
solution:

#hashTable

 */

import java.util.Arrays;
import java.util.HashSet;

public class ContainsDuplicate {

    public static void main(String[] args){
        int[] a = {1,1,1,3,3,4,3,2,4,2};
        Solution s = new Solution();
        System.out.println(s.containsDuplicate(a));
    }

    static
    class Solution {

        public boolean containsDuplicate(int[] nums) {
            return helper2(nums);
        }

        //time: O(n) complexity: O(n)
        // 6ms => 82.16% and 43.7mb 9.80%
        public boolean helper1 (int[] nums) {
            HashSet<Integer> a = new HashSet<Integer>();

            for(int i=0; i<nums.length; ++i)
                if(!a.add(nums[i])) return true;

            return false;
        }

        //time: O(nlogn) complexity: O(1)
        // 5ms => 93.9% and 41.6mb 46.59%
        public boolean helper2(int[] nums){
            if(nums.length == 0 || nums.length==1) return false;
            Arrays.sort(nums);

            for(int i=1; i<nums.length; ++i)
                if(nums[i] == nums[i-1]) return true;

                return false;
        }

    }
}
