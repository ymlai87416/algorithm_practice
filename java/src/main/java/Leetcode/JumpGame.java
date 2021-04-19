package Leetcode;

/*
number: 55
problem: https://leetcode.com/problems/jump-game/
level: medium
solution:
    Greedy (1ms): from the last index, find the index which can jump to it, and until it can reach the first position.
    Dp(1114ms)

Question:
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.

#greedy

 */

public class JumpGame {
    public static void main(String[] args){
        int[] nums = new int[] {2,3,1,1,4};
        Solution s = new Solution();
        System.out.println(s.canJump(nums));
    }


    static
    class Solution {
        public boolean canJump(int[] nums){
            return greedyHelper(nums);
        }

        public boolean dpHelper(int[] nums) {
            int[] dp = new int[nums.length];

            for(int i=0; i<nums.length; ++i)
                dp[i] = -1;

            dp[nums.length-1] = 1;

            boolean mod;

            //this implementation is bad for finding topological sort
            while(true){
                mod = false;

                for(int i=0; i<nums.length; ++i){
                    if(dp[i] == 1) continue;
                    int a = nums[i];
                    for(int j=i+1; j<=Math.min(nums.length-1, i+a); ++j){
                        if(dp[j] == 1){
                            dp[i] = 1;
                            mod = true;
                        }
                    }
                }

                if(!mod) break;
            }

            return dp[0] == 1;
        }

        private boolean greedyHelper(int[] nums){
            int lastGood = nums.length-1;

            for(int i=nums.length-2; i>=0; --i){
                if(nums[i] + i >= lastGood){
                    lastGood = i;
                }
            }

            return lastGood == 0;
        }

    }
}
