package Leetcode;

import java.util.Arrays;

/*
url: https://leetcode.com/problems/distinct-subsequences/
level: medium
solution: dp, for each substring 1...n of T, find how many way to make it in S.
 */

public class DistinctSubsequence {
    public static void main(String[] args){
        String s = "babgbag";
        String t = "bag";
        Solution sol = new Solution();
        System.out.println(sol.numDistinct(s, t));
    }

    static
    class Solution {
        int[][] dp;
        public int numDistinct(String s, String t) {
            /*
            dp = new int[s.length()+1][t.length()+1];
            for(int i=0; i<dp.length; ++i)
                Arrays.fill(dp[i], -1);

            return helper(s, t, 0, 0);
            */
            return iterativeHelper(s,t);
        }

        private int iterativeHelper(String s, String t){

            int[] prev = new int[s.length()+1];
            int[] cur = new int[s.length()+1];
            for(int i=0; i<=s.length(); ++i)
                prev[i] = 1;

            for(int i=1; i<=t.length(); ++i){
                cur[0] = 0;
                for(int j=1; j<=s.length(); ++j){
                    if(s.charAt(j-1) == t.charAt(i-1))
                        cur[j] = prev[j-1] + cur[j-1];
                    else
                        cur[j] = cur[j-1];
                }
                int[] tt = prev;
                prev = cur;
                cur = tt;
            }

            return prev[s.length()];
        }

        private int helper(String s, String t, int sp, int tp){
            if(dp[sp][tp] != -1)
                return dp[sp][tp];
            if(tp == t.length()){
                //System.out.println(debug);
                return 1;
            }
            else if(sp == s.length()) return 0;

            if(s.charAt(sp) == t.charAt(tp))
                dp[sp][tp] = helper(s, t, sp+1, tp+1) + helper(s, t, sp+1, tp);
            else
                dp[sp][tp] =  helper(s, t, sp+1, tp);

            return dp[sp][tp];
        }
    }
}
