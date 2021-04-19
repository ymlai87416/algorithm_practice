package Leetcode;

/*
number: 5
problem: https://leetcode.com/problems/longest-palindromic-substring/
level: medium
solution: another solution to the problem, which is expand around the center
    time complexity = O(n^2) = 36ms. with no additional space

#string #dp

 */

public class LongestPalindromicSubstring2 {
    public static void main(String[] args){
        String input = "babaddtattarrattatddetartrateedredividerb";
        Solution s = new Solution();
        System.out.println(s.longestPalindrome(input));
    }

    static
    class Solution {

        public String longestPalindrome(String s) {

            if(s.length()==0) return s;

            int max = 0;
            String result = null;
            for(int i=0; i<s.length(); ++i){

                //first case: the center is i
                int left = i;
                int right = i;

                for(; left >= 0 && right < s.length(); --left, ++right){
                    if(!(s.charAt(left) == s.charAt(right))) {
                        break;
                    }
                }

                ++left; --right;
                if (max < right-left+1){
                    max = right-left+1;
                    result = s.substring(left, right+1);
                }

                //second case: the center is between i and i+1
                left = i;
                right = i+1;

                if(right <s.length()) {

                    for (; left >= 0 && right < s.length(); --left, ++right) {
                        if (!(s.charAt(left) == s.charAt(right))) {
                            break;
                        }
                    }

                    ++left;
                    --right;

                    if (max < right - left + 1) {
                        max = right - left + 1;
                        result = s.substring(left, right + 1);
                    }
                }
            }
            return result;
        }
    }
}

