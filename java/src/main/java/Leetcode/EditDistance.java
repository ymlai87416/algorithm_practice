package Leetcode;

/**
problem: https://leetcode.com/problems/edit-distance/
level: medium
solution: classic edit distance

#string #dp
 */
public class EditDistance {
    public static void main(String[] args){
        String w1 = "";
        String w2 = "a";

        Solution s = new Solution();
        System.out.println(s.minDistance(w1, w2));
    }


    static
    class Solution {
        int[][] dp;
        public int minDistance(String word1, String word2) {

            if(dp == null || (dp.length < word1.length()+1 && dp[0].length < word2.length()+1))
                dp = new int[word1.length()+1][word2.length()+1];

            dp[0][0] = 0;
            for(int i=0; i<word1.length(); ++i)
                dp[i+1][0] = i+1; //delete char
            for(int i=0; i<word2.length(); ++i)
                dp[0][i+1] = i+1; //add char

            for(int i=1; i<=word1.length(); ++i){
                for(int j=1; j<=word2.length(); ++j){
                    char ci = word1.charAt(i-1);
                    char cj = word2.charAt(j-1);

                    if(ci==cj)
                        dp[i][j] = dp[i-1][j-1];
                    else
                        dp[i][j] = dp[i-1][j-1]+1;

                    dp[i][j] = Math.min(dp[i][j], Math.min(dp[i-1][j]+1, dp[i][j-1]+1));
                }
            }

            return dp[word1.length()][word2.length()];
        }
    }
}

