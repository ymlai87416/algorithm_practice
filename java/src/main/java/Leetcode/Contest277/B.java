package Leetcode.Contest277;

/**
 problem: https://leetcode.com/contest/weekly-contest-277/problems/rearrange-array-elements-by-sign/
 level: medium
 solution:

 **/

public class B {

    public int[] rearrangeArray(int[] nums) {
        int[] pos = new int[nums.length/2];
        int[] neg = new int[nums.length/2];
        int[] r = new int[nums.length];

        int pp=0, np=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < 0) {
                pos[pp] = i;
                ++pp;
            }
            else{
                neg[np] = i;
                ++np;
            }
        }

        for (int i = 0; i < nums.length; i+=2) {
            r[i] = nums[pos[i/2]];
            r[i+1] = nums[neg[i/2]];
        }

        return r;
    }

    public static void main(String[] args){
        B s = new B();

    }

}
