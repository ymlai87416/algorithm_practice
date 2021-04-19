package Leetcode;

/*
number: 236
url: https://leetcode.com/problems/majority-element/
level: easy
solution: 1.Boyer-Moore majority vote algorithm in 1ms
time complexity: O(n)

#divideAndConquer #bit #boyerMooreMajorityVote

 */

public class MajorityElement {
    //Boyer-Moore majority vote algorithm

    public static void main(String[] args){
        int[] input = {2,2,1,1,1,2,2};
        Solution s = new Solution();
        System.out.println(s.majorityElement(input));
    }

    static
    class Solution {
        public int majorityElement(int[] nums) {
            int m=-1;
            int cnt=0;

            for(int i=0; i< nums.length; ++i){
                if(cnt == 0) {
                    m = nums[i];
                    cnt = 1;
                }
                else {
                    if (m == nums[i])
                        cnt += 1;
                    else {
                        cnt -= 1;
                    }
                }
            }

            return m;
        }
    }
}
