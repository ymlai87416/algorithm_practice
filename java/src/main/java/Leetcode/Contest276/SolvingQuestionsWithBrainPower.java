package Leetcode.Contest276;

import java.util.HashMap;

/**
 problem:
 level: medium
 solution:

 655-704
 #math
 **/

public class SolvingQuestionsWithBrainPower {

    HashMap<Integer, Long> dp = new HashMap<>();
    int[][] question;
    public long mostPoints(int[][] questions) {
        dp.clear();
        this.question = questions;
        return helper(0);
    }

    public long helper(int loc){
        if(loc >= question.length) return 0;
        if(dp.containsKey(loc)) return dp.get(loc);
        long a1 = question[loc][0] + helper(loc+question[loc][1]+1);
        long a2 = helper(loc+1);
        long ans = Math.max(a1, a2);
        dp.put(loc, ans);
        return ans;

    }



    public static void main(String[] args){
        SolvingQuestionsWithBrainPower s = new SolvingQuestionsWithBrainPower();

        int[][] s1 = {{3,2},{4,3},{4,4},{2,5}};
        int[][] s2 = {{1,1},{2,2},{3,3},{4,4},{5,5}};

        System.out.println(s.mostPoints(s1));
        System.out.println(s.mostPoints(s2));
    }
}
