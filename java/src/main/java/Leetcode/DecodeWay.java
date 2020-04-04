package Leetcode;

import DataStructure.JavaPriorityQueue.UVA1203;

/*
url: https://leetcode.com/problems/decode-ways/
level: medium
solution: it is a dfs, speed up by dp
 */

public class DecodeWay {
    public static void main(String[] args) {
        String s = "226";
        Solution sol = new Solution();
        int r = sol.numDecodings(s);
        System.out.println(r);
    }

    static
    class Solution {
        int[] dp;

        public int numDecodings(String s){
            dp = new int[s.length()+1];
            for(int i=0; i<dp.length; ++i) dp[i] = -1;

            return numDecodingsHelper(s);
        }

        public int numDecodingsHelper(String s) {
            if(s.length() == 0){
                return 1;
            }
            else if (dp[s.length()] != -1)
                return dp[s.length()];

            int ans  = 0;
            for(int i=1; i<=s.length(); ++i){
                String ts = s.substring(0, i);
                int ti = Integer.valueOf(ts);
                if(ti > 0 && ti <=26){
                    ans += numDecodingsHelper(s.substring(i));
                }
                else break;
            }

            dp[s.length()] = ans;
            return dp[s.length()];
        }
    }
}
