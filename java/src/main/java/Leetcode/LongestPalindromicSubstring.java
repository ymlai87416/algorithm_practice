package Leetcode;

/*
number: 5
problem: https://leetcode.com/problems/longest-palindromic-substring/
level: medium
solution: another dp problem. if str[a...b] is a palindrom, then dp[a][b+1] = b-a+1
    time complexity = O(n^2) = 124ms

#string #dp

 */

public class LongestPalindromicSubstring {
    public static void main(String[] args){
        String input = "babaddtattarrattatddetartrateedredividerb";
        Solution s = new Solution();
        System.out.println(s.longestPalindrome(input));
    }

    static
    class Solution {
        static int[][] dp;
        static {
            dp = new int[1001][];
            for(int i=0; i<1001; ++i)
                dp[i] = new int[1001];
        }

        void resetMen(){
            for(int i=0; i<1001; ++i)
                for(int j=0; j<1001; ++j)
                    dp[i][j] = -1;
            for(int i=0; i<1000; ++i) {
                dp[i][i] = 0;
                dp[i][i + 1] = 1;
            }
            dp[1000][1000] = 0;
        }

        String input;

        int solve(int start, int end){
            if(start == end) return 0;
            else if(end-start == 1) return 1;
            else if(dp[start][end] != -1) return dp[start][end];
            else{
                int t1, t2;
                if(input.charAt(start) == input.charAt(end-1)){
                    if(solve(start+1, end-1) == end-start-2){
                        dp[start][end] = end-start;
                        return dp[start][end];
                    }
                    else{
                        t1 = solve(start+1, end);
                        t2 = solve(start, end-1);
                        dp[start][end] = Math.max(t1, t2);
                        return dp[start][end];
                    }
                }
                else{
                    t1 = solve(start+1, end);
                    t2 = solve(start, end-1);
                    dp[start][end] = Math.max(t1, t2);
                    return dp[start][end];
                }
            }
        }

        String backtrack(int r){
            for(int i=0; i<input.length(); ++i) {
                if (dp[i][r + i] == r)
                    return input.substring(i, r + i);
            }
            return "";
        }

        public String longestPalindrome(String s) {
            resetMen();
            this.input = s;
            int r = solve(0, s.length());
            return backtrack(r);
        }
    }
}

