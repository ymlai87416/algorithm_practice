package Leetcode;

import java.util.*;

//When you sort, you know the new list is already in the list or not by looking only the last entry.

/*
problem: https://leetcode.com/problems/4sum/
level: medium
solution: time complexity O(n^3)
    sort the array, then the result should be in ascending order

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


                        /*
                        for(int l=k+1; l<nums.length; ++l){
                            if(nums[i]+nums[j]+nums[k]+nums[l] == target){
                                curr = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                                if(prev == null || (prev != null && !repeat(prev, curr))) {
                                    r.add(curr);
                                    prev = curr;
                                }
                            }
                        }
                        */
                    }
                }
            }

            return r;
        }

        private boolean repeat(List<Integer> a, List<Integer> b){

            /*
            System.out.print("debug: ");
            System.out.print(a);
            System.out.print(" ");
            System.out.println(b);
            */

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
    }
}

