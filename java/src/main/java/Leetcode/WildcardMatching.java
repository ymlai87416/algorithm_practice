package Leetcode;

/*
number: 44
url: https://leetcode.com/problems/wildcard-matching/
level: hard
solution: the special case is *, backtrack
 */

public class WildcardMatching {
    public static void main(String[] args) {
        String s = "ab";
        String p = "?*";
        Solution sol = new Solution();
        boolean r = sol.isMatch(s, p);
        System.out.println(r);
    }

    static
    class Solution {
        int[][] dp;

        public boolean isMatch(String s, String p) {
            //dp = new int[s.length() + 1][p.length() + 1];   //this statement will have 3ms
            boolean result;
            //recursion
            //initDp(dp, -1);
            //result = isMatchRecursiveHelper(s, p, 0, 0);

            //interative
            //result = isMatchIterativeHelper(s, p);

            result = backTrackHelper(s, p);

            return result;
        }

        //100%
        private boolean backTrackHelper(String s, String p){
            int sIdx = 0, pIdx = 0;
            int sLen = s.length(), pLen = p.length();
            int starIdx = -1, sTmpIdx = -1;

            while(sIdx < sLen){
                if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))){
                    ++sIdx;
                    ++pIdx;
                }
                //* character, mark down the start for backtracking
                //multiple * will not hurt me.
                else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                    starIdx = pIdx;
                    sTmpIdx = sIdx;
                    ++pIdx;
                }
                else if (starIdx == -1) {
                    return false;
                }
                else {
                    // Backtrack: check the situation
                    // when '*' matches one more character
                    pIdx = starIdx + 1;
                    sIdx = sTmpIdx + 1;
                    sTmpIdx = sIdx;
                }
            }

            for(int i = pIdx; i < pLen; i++)
                if (p.charAt(i) != '*') return false;
            return true;
        }

        private void initDp(int[][] dp, int v){
            for (int i = 0; i < dp.length; ++i)
                for (int j = 0; j < dp[0].length; ++j)
                    dp[i][j] = v;
        }

        //14ms => 64%
        private boolean isMatchIterativeHelper(String s, String p) {
            //base case: empty string match to empty string
            initDp(dp, 0);  //remove this statement result in 11ms => 83.56%
            dp[0][0] = 1;

            for (int i = 1; i < p.length()+1; ++i) {
                for (int j = 0; j < s.length()+1; ++j) {
                    if (p.charAt(i-1) == '*'){
                        dp[j][i] = (j > 0 ? (dp[j-1][i] != 0) : false) || dp[j][i-1] != 0 ? 1: 0;
                    }
                    else if (j > 0 && p.charAt(i-1) == '?'){
                        dp[j][i] = dp[j-1][i-1];
                    }
                    else if (j > 0 && p.charAt(i-1) == s.charAt(j-1)){
                        dp[j][i] = dp[j-1][i-1];
                    }
                }
            }

            return dp[s.length()][p.length()] != 0;
        }

        //current is recursive solution which is slow, run at 44ms => iterative should be under 20ms
        private boolean isMatchRecursiveHelper(String s, String p, int si, int pi) {
            if (s.length() == si && p.length() == pi) return true;
            else if (s.length() == si && containOnlyStar(p, pi)) return true;
            else if (s.length() != si && p.length() == pi || s.length() == si && p.length() != pi) return false;

            if (dp[si][pi] != -1)
                return !(dp[si][pi] == 0);

            char c = p.charAt(pi);
            char sc = s.charAt(si);

            if (c == '?') {
                dp[si][pi] = isMatchRecursiveHelper(s, p, si + 1, pi + 1) ? 1 : 0;
            } else if (c == '*') {
                dp[si][pi] = isMatchRecursiveHelper(s, p, si + 1, pi) || isMatchRecursiveHelper(s, p, si + 1, pi + 1) || isMatchRecursiveHelper(s, p, si, pi + 1) ? 1 : 0;
            } else
                dp[si][pi] = (c == sc && isMatchRecursiveHelper(s, p, si + 1, pi + 1)) ? 1 : 0;

            return !(dp[si][pi] == 0);
        }

        private boolean containOnlyStar(String p, int pi) {
            for (int i = pi; i < p.length(); ++i) if (p.charAt(i) != '*') return false;
            return true;
        }
    }
}
