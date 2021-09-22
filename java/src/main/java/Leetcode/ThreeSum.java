package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
number: 15
problem: https://leetcode.com/problems/3sum/
level: medium
solution: 1. brute force 3 loop, O(n^3)
          2. 2 loop with lookup. O(n^2)
          3. a loop with start - end behind i pointer O(n^2)

#array #two_pointers

 **/

public class ThreeSum {
    public static void main(String[] args){
        List<Integer> input= Arrays.asList(0, 0, 0, 0);
        List<Integer> input2 = Arrays.asList(-1,0,1,2,-1,-4);
        Solution s = new Solution();

        System.out.println("debug");
        s.threeSum(input.stream().mapToInt(i -> i).toArray());
        System.out.println("debug");
        s.threeSum(input2.stream().mapToInt(i -> i).toArray());

        //System.out.println(s.threeSum(input.stream().mapToInt(i -> i).toArray()));
    }


    static
    class Solution {

        public List<List<Integer>> threeSum(int[] nums) {
            return use2Sum(nums);
            //return bruteForce(nums);
        }


        public List<List<Integer>> bruteForce(int[] nums){
            Arrays.sort(nums);
            List<List<Integer>> a = new ArrayList<>();
            for(int i=0; i<nums.length; ++i) {
                for (int j = i + 1; j < nums.length; ++j) {
                    for (int k = j + 1; k < nums.length; ++k) {
                        final int ni = nums[i];
                        final int nj = nums[j];
                        final int nk = nums[k];
                        if (nums[i] + nums[j] + nums[k] == 0) {
                            if (a.stream().filter(x -> x.get(0) == ni && x.get(1) == nj && x.get(2) == nk).count() == 0)
                                //System.out.format("%d %d %d\n", nums[i], nums[j], nums[k]);
                                a.add(Arrays.asList(ni, nj, nk));
                        }
                    }
                }
            }
            return a;
        }

        public List<List<Integer>> use2Sum(int[] nums) {
            List<List<Integer>> a = new ArrayList<>();
            int len = nums.length;
            Arrays.sort(nums);

            for(int i=0; i<len-2; ++i){
                if(i > 0 && nums[i] == nums[i-1]) continue;

                //now get the shit
                List<List<Integer>> r2 = twoSum(nums, 0-nums[i], i+1);
                for (int j = 0; j < r2.size(); j++) {
                    a.add(Arrays.asList(nums[i], r2.get(j).get(0), r2.get(j).get(1)));
                }
            }

            return a;
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
                    result.add(Arrays.asList(nums[lo], nums[hi])); //new int[]{nums[lo], nums[hi]}
                    ++lo;
                }
            }

            return result;
        }
    }
}
