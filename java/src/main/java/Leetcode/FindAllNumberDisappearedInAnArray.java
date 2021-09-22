package Leetcode;

/**
problem: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
level: easy
solution: use the negative bit for storing information

#array

 */

import java.util.ArrayList;
import java.util.List;

public class FindAllNumberDisappearedInAnArray {
    public static void main(String[] args){
        int[] nums = {4,3,2,7,8,2,3,1};

        Solution s = new Solution();
        System.out.println(s.findDisappearedNumbers(nums));
    }


    static
    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            for(int i=0; i<nums.length; ++i){
                int a = nums[i] & 0x7fffffff;
                nums[a-1] |= 0x80000000;
            }

            ArrayList<Integer> result = new ArrayList<>();
            for(int i=0; i<nums.length; ++i)
                if(nums[i] > 0) result.add(i+1);

            return result;
        }
    }
}
