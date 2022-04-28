package Leetcode.FacebookMock;

import java.util.*;

public class OnsiteQ1 {

    public List<Integer> findAnagrams(String s, String p) {
        int[] dp = new int[26];
        int[] freqP = new int[26];
        Arrays.fill(freqP, 0);
        Arrays.fill(dp, 0);

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            freqP[c-'a']++;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int exitC = i-p.length();
            if(exitC >=0){
                char ec = s.charAt(exitC);
                dp[ec-'a']--;
            }
            char cc = s.charAt(i);
            dp[cc-'a']++;

            //check if the composition of s match
            boolean allMatched = true;
            for (int j = 0; j < 26; j++) {
                if(freqP[j] != dp[j]){
                    allMatched = false;
                    break;
                }
            }

            if(allMatched)
                result.add(i-p.length()+1);

        }
        return result;
    }

    public static void main(String[] args){
        OnsiteQ1 s = new OnsiteQ1();
        System.out.println(s.findAnagrams("cbaebabacd", "abc"));
        System.out.println(s.findAnagrams("abab", "ab"));
        System.out.println(s.findAnagrams("abcd", "dbca"));
    }
}
