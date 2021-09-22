package Leetcode;


/**
number: 334
problem: https://leetcode.com/problems/increasing-triplet-subsequence/
level: medium
solution: naive way is to create 2 additional array to store the min (from left) and max (from right) and find an index
            which min[i] < num[i] < max[i]
          another way is to invent an operation which remove the element if no one before is smaller than itself, remove it.
          apply it 2 times and if there are still number left, it should be an answer.

#time_O(n) #space_O(1) #eliminiate
 */

public class IncreasingTripletSubsequence {

    public static void main(String[] args){
        int[] input = new int[]{5,4,3,2,1};

        Solution s = new Solution();
        System.out.println(s.increasingTriplet(input));
    }

    static
    class Solution {
        public boolean increasingTriplet(int[] nums) {
            if(nums.length < 3) return false;

            int smallest = nums[0];
            nums[0] = -1;
            for(int i=1; i<nums.length; ++i){
                if(nums[i] <= smallest){
                    smallest = nums[i];
                    nums[i] = -1;   //eliminate as this.
                }
            }

            for(int i=0; i<nums.length; ++i)
                System.out.print(nums[i] + " ");
            System.out.println();

            smallest = Integer.MAX_VALUE;
            for(int i=1; i<nums.length; ++i){
                if(nums[i] != -1) {
                    if(smallest < nums[i])
                        return true;
                    else
                        smallest = nums[i];
                }
            }

            return false;
        }
    }

}
