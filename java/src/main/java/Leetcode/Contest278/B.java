package Leetcode.Contest278;

import java.util.*;

public class B {

    public List<Integer> maxScoreIndices(int[] nums) {
        int left0 = 0;
        int right1 = 0;
        for(int i=0; i<nums.length; ++i) {
            if (nums[i] == 1) right1++;
        }
        List<Integer> result = new ArrayList<Integer>();
        int[] score=  new int[nums.length+1];
        score[0] = right1;
        int max = score[0];
        for(int i=0; i<nums.length; ++i){
            if(nums[i] == 1)
                right1--;
            if(nums[i] == 0)
                left0++;
            score[i+1] = left0+right1;

            if(max < score[i+1])
                max = score[i+1];
        }

        for(int i=0; i<score.length; ++i){
            if(score[i] == max)
                result.add(i);
        }

        return result;
    }

    public static void main(String[] args){

    }
}
