package Leetcode;

/*
number: 318
url: https://leetcode.com/problems/maximum-product-of-word-lengths/
level: medium
solution: use bit and then loop
 */

public class MaximumProductOfWordLength {
    public static void main(String[] args){
        String[] words = new String[]{"abcw","baz","foo","bar","xtfn","abcdef"};
        Solution sol = new Solution();
        System.out.println(sol.maxProduct(words));
    }

    static
    class Solution {

        public int maxProduct(String[] words) {
            int[] wordBin = new int[words.length];

            for(int i=0; i<words.length; ++i){
                for(char c : words[i].toCharArray()){
                    wordBin[i] |= 1<< (c -'a');
                }
            }

            int r = 0;
            for(int i=0; i<wordBin.length; ++i){
                for(int j=i+1; j<wordBin.length; ++j){
                    if((wordBin[i] & wordBin[j]) == 0)
                        r = Math.max(r, words[i].length() * words[j].length());
                }
            }

            return r;
        }
    }
}
