package Leetcode;

/*
number: 242
url: https://leetcode.com/problems/valid-anagram/
level: easy
solution: just count the character
 */

public class ValidAnagram {
    public static void main(String[] args){
        String s = "anagram", t = "nagaram";
        Solution sol = new Solution();
        System.out.println(sol.isAnagram(s, t));
    }

    static
    class Solution {
        public boolean isAnagram(String s, String t) {
            return helper2(s, t);
        }

        private boolean helper1(String s, String t){
            int[] sfreq = new int[26];
            for(int i=0; i<26; ++i)
                sfreq[i] = 0;
            for(int i=0; i<s.length(); ++i)
                sfreq[s.charAt(i)-'a'] +=1;
            for(int i=0; i<t.length(); ++i)
                sfreq[t.charAt(i)-'a'] -=1;
            for(int i=0; i<26; ++i)
                if(sfreq[i] != 0) return false;
            return true;
        }

        public boolean helper2(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            int[] table = new int[26];
            for (int i = 0; i < s.length(); i++) {
                table[s.charAt(i) - 'a']++;
            }
            for (int i = 0; i < t.length(); i++) {
                table[t.charAt(i) - 'a']--;
                if (table[t.charAt(i) - 'a'] < 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
