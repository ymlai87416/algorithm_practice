package Leetcode;

/**
number: 125
problem: https://leetcode.com/problems/valid-palindrome/
level: remove all useless char, and then reverse the string.
solution:

 #twoPointer #string
 **/

public class ValidPalindrome {
    public static void main(String[] args){
        String input = "0P";
        Solution s = new Solution();
        System.out.println(s.isPalindrome(input));
    }

    //very slow....must be the regex...
    static
    class Solution {
        public boolean isPalindrome(String s) {
            s = s.toLowerCase();
            s = s.replaceAll("[^a-z0-9]", "");

            for(int i=0, j=s.length()-1; i<j; ++i, --j){
                if(s.charAt(i) != s.charAt(j))
                    return false;
            }
            return true;
        }
    }
}
