package Leetcode;

import java.util.*;

/**
 problem: https://leetcode.com/problems/two-sum/
 level: easy
 solution: time complexity O(n) or O(nlogn)
 sort the array, then the result should be in ascending order

 #sorting #twoPointer #hash_table
 */

public class TwoSum {

    public static void main(String[] args){
        List<Integer> input= Arrays.asList(2,7,11,15);
        List<Integer> input2 = Arrays.asList(3,2,4);
        Solution s = new Solution();

        System.out.println("debug");
        //System.out.println(s.twoSum(input.stream().mapToInt(i -> i).toArray(), 9));
        System.out.println("debug");
        System.out.println(s.twoSum(input2.stream().mapToInt(i -> i).toArray(), 6));

    }


    static
    class Solution {
        public int[] twoSum(int[] nums, int target){
            //return sortHelper(nums, target);
            return mapHelper(nums, target);
        }

        public int[] sortHelper(int[] nums, int target){
            //sort the list and get the index
            Integer[] idx = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                idx[i] = i;
            }
            Arrays.sort(idx, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return nums[o1]-nums[o2];
                }
            });

            //now we use 2 pointer to do the job
            int lo = 0, hi = nums.length-1;
            while(lo < hi){
                int sum = nums[idx[lo]] + nums[idx[hi]];
                if(sum < target) //sum smaller => move lo to right
                    lo += 1;
                else if(sum > target) //sum bigger => move hi to left, or move lo back to left
                    hi -= 1;
                else
                    return new int[]{idx[lo], idx[hi]};
            }
            return null;
        }

        public int[] mapHelper(int[] nums, int target){
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }

            for (int i = 0; i < nums.length; i++) {
                if(map.containsKey(target-nums[i])) {
                    int n = map.get(target-nums[i]);
                    if(n != i) return new int[]{i, n};
                }
            }

            return null;
        }
    }
}
