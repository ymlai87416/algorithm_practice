package Leetcode;

import java.util.Arrays;
import java.util.List;

/*
number: 395
url: https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
level: medium
solution: I got a string, count the freq map, and then I can break the strings into small segment or cannot (if the
    constraint are fulfilled).
 */

public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public static void main(String[] args) {
        String s = "aacbbbdc";
        int k = 2;
        Solution sol = new Solution();
        int r = sol.longestSubstring(s, k);
        System.out.println(r);
    }

    static
    class Solution {
        int[][] dp;
        public int longestSubstring(String s, int k) {
            return linearHelper(s, k, 0, s.length()-1);
        }

        private int linearHelper(String s, int k, int start, int end){
            //keep only character with k freq. then do it in recursive
            //System.out.format("%d %d\n", start, end);
            if(start > end) return 0;

            int[] freq = new int[26];
            for(int i=start; i<=end; ++i){
                freq[s.charAt(i)-'a'] += 1;
            }

            boolean allGood = true;
            for(int i=0; i<freq.length; ++i) {
                if (freq[i] != 0 && freq[i] < k) {
                    allGood = false;
                    break;
                }
            }

            if(allGood)
                return end-start+1;

            int result = 0;
            int left = 0;
            for(int i=start; i<=end; ++i){
                int idx = s.charAt(i)-'a';
                if(freq[idx] != 0 && freq[idx] < k){
                    int tmp = linearHelper(s, k, left, i-1);
                    result = Math.max(result, tmp);
                    left = i+1;
                }
            }
            int tmp = linearHelper(s, k, left, end);
            result = Math.max(result, tmp);

            return result;
        }


        private int dpHelper(String s, int k){
            int[] freq = new int[26];
            for(int i=0; i<s.length(); ++i){
                freq[s.charAt(i)-'a'] += 1;
            }

            dp = new int[s.length()][s.length()];
            for(int i=0; i<s.length(); ++i)
                Arrays.fill(dp[i], -1);

            return helper(s, k, 0, s.length()-1, freq);
        }

        private int helper(String s, int k, int start, int end, int[] freq){

            if(start > end)
                return 0;

            if(dp[start][end] != -1)
                return dp[start][end];

            //is good
            boolean allGood = true;
            boolean allBad = true;
            for(int i=0; i<freq.length; ++i) {
                if (freq[i] != 0 && freq[i] < k) {
                    allGood = false;
                }
                if(freq[i] != 0 && freq[i] >=k){
                    allBad = false;
                }
            }

            if(allBad)
                return 0;

            if(allGood) {
                dp[start][end] = end - start + 1;
                return dp[start][end];
            }

            //either left or right
            freq[s.charAt(start)-'a']--;
            int left = helper(s, k, start+1, end, freq);
            freq[s.charAt(start)-'a']++;

            freq[s.charAt(end)-'a']--;
            int right = helper(s, k, start, end-1, freq);
            freq[s.charAt(end)-'a']++;

            dp[start][end] =  Math.max(left, right);
            return dp[start][end];
        }
    }
}
