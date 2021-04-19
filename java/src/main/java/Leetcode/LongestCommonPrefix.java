package Leetcode;

/*
number: 14
problem: https://leetcode.com/problems/longest-common-prefix/
level: easy
solution: just add a character if all string have it. from start-> end

#string

 */

public class LongestCommonPrefix {
    public static void main(String[] args){
        //String[] input = {"flower","flow","flight"};
        String[] input = {"test"};
        Solution s = new Solution();
        System.out.println(s.longestCommonPrefix(input));
    }

    static
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if(strs.length == 0) return "";

            int minLen = Integer.MAX_VALUE;
            for(int i=0; i<strs.length; ++i)
                minLen = Math.min(minLen, strs[i].length());

            String s = "";
            for(int i=0; i<minLen; ++i){
                char c = strs[0].charAt(i);
                boolean ok = true;
                for(int j=1; j<strs.length; ++j){
                    if(c != strs[j].charAt(i)) {
                        ok = false;
                        break;
                    }
                }
                if(ok)
                    s = s+c;
                else
                    break;
            }

            return s;
        }
    }
}
