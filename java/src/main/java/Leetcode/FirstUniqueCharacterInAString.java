package Leetcode;

public class FirstUniqueCharacterInAString {
    public static void main(String[] args){
        String ss = "loveleetcode";
        Solution s = new Solution();
        System.out.println(s.firstUniqChar(ss));
    }

    static
    class Solution {

        public int firstUniqChar(String s) {
            return helper1(s);
        }

        public int helper1(String s) {
            int[] test = new int[26];
            for(int i=0; i<26; ++i) test[i] = 0;
            for(int i=0; i<s.length(); ++i){
                test[s.charAt(i)-'a'] += 1;
            }

            for(int i=0; i<s.length(); ++i){
                if(test[s.charAt(i)-'a'] ==1)
                    return i;
            }
            return -1;
        }

    }
}
