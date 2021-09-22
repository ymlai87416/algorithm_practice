package Leetcode;

import java.util.*;

//When you sort, you know the new list is already in the list or not by looking only the last entry.

/**
problem: https://leetcode.com/problems/4sum/
level: medium
solution: time complexity O(n^3)
    sort the array, then the result should be in ascending order 73ms
    kSum implementation is 2ms

#hashTable #twoPointer
 */

public class FourSum {
    public static void main(String[] args){
        int[] nums = new int[] {1, 0, -1, 0, -2, 2};
        int target = 0;
        Solution s = new Solution();
        System.out.println(s.fourSum(nums, target));
    }


    static
    class Solution {

        public List<List<Integer>> fourSum(int[] nums, int target) {
            //return firstImpl(nums, target);
            Arrays.sort(nums);
            return kSum(nums, target, 0, 4);
        }

        public List<List<Integer>> firstImpl(int[] nums, int target) {
            Arrays.sort(nums);
            ArrayList<List<Integer>> r = new ArrayList<List<Integer>>();

            HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
            for(int i=0; i<nums.length; ++i) {
                if(hs.containsKey(nums[i]))
                    hs.put(nums[i], hs.get(nums[i])+1);
                else
                    hs.put(nums[i], 1);
            }

            List<Integer> prev = null, curr = null;

            for(int i=0; i<nums.length; ++i){
                for(int j=i+1; j<nums.length; ++j){
                    for(int k=j+1; k<nums.length; ++k){
                        int b = target - (nums[i]+nums[j]+nums[k]);
                        if(b >=nums[k]){
                            //bcnt max = 4, count the number of b in the formula.
                            int bcnt = (nums[i]==b?1:0) + (nums[j]==b?1:0) + (nums[k]==b?1:0) + 1;
                            //if b only have 3, and you tell me you need 4b, of coz not allowed.
                            if(hs.containsKey(b) && hs.get(b) >= bcnt) {
                                curr = Arrays.asList(nums[i], nums[j], nums[k], b);
                                if (prev == null || (prev != null && !repeat(prev, curr))) {
                                    r.add(curr);
                                    prev = curr;
                                }
                            }
                        }
                    }
                }
            }

            return r;

        }

        private boolean repeat(List<Integer> a, List<Integer> b){


            for(int i=0; i<a.size(); ++i) {
                int ai = a.get(i);
                int bi = b.get(i);
                if (ai < bi){
                    break;        //not a repeat
                }
                if (ai > bi) {    //if a > b, then it should be repeat
                    //System.out.println("T1");
                    return true;
                }
            }

            for(int i=0; i<a.size(); ++i) {
                int ai = a.get(i);
                int bi = b.get(i);
                if (ai != bi) {
                    //System.out.println("F");
                    return false;   //if a > b, then it should be repeat
                }
            }

            //System.out.println("T2");
            return true;   //if a == b, then it also repeat
        }

        public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {

            List<List<Integer>> result = new ArrayList<>();

            if (start == nums.length || nums[start] * k > target || target > nums[nums.length-1] * k)
                return result;

            if(k == 2)
                return twoSum(nums, target, start);

            for (int i = start; i < nums.length; i++) {
                if(i==start || nums[i] != nums[i-1]){
                    List<List<Integer>> n_1 = kSum(nums, target-nums[i], i+1, k-1);

                    for (int j = 0; j < n_1.size(); j++) {
                        ArrayList<Integer> t = (ArrayList)n_1.get(j);
                        t.add(nums[i]);
                        result.add(t);
                    }
                }
            }

            return result;
        }

        private List<List<Integer>> twoSum(int[] nums, int target, int start) {
            List<List<Integer>> result = new ArrayList<>();
            int lo = start, hi = nums.length-1;

            while(lo < hi){
                int sum = nums[lo] + nums[hi];
                if(sum < target  || (lo > start && nums[lo] == nums[lo - 1])) //sum smaller => move lo to right
                    lo += 1;
                else if(sum > target  || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) //sum bigger => move hi to left, or move lo back to left
                    hi -= 1;
                else {
                    ArrayList t = new ArrayList<Integer>();
                    t.add(nums[lo]); t.add(nums[hi]);
                    result.add(t);
                    ++lo;
                }
            }

            return result;
        }
    }
}

