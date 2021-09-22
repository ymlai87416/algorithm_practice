package Leetcode;

/**
problem: https://leetcode.com/problems/excel-sheet-column-number/
level: easy
solution:

#math

 */

public class ExcelSheetColumnNumber {
    public static void main(String[] args){
        String input = "ZY";
        Solution s = new Solution();
        System.out.println(s.titleToNumber(input));
    }

    static
    class Solution {
        public int titleToNumber(String s) {
            int r=0;
            for(int i=0; i<s.length(); ++i){
                r = r * 26+ ((s.charAt(i)-'A')+1);
            }
            return r;
        }
    }
}
