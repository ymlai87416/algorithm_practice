package Leetcode;

import java.util.Arrays;

/*
number: 12
problem: https://leetcode.com/problems/integer-to-roman/
level: medium
solution: execute the procedure.

#math #string
 */

public class IntegerToRoman {
    public static void main(String[] args){
        int input= 58;
        Solution s = new Solution();
        System.out.println(s.intToRoman(input));
    }


    static
    class Solution {
        String[] t0 = new String[] {"", "M", "MM", "MMM"};
        String[] t1 = new String[] {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] t2 = new String[] {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] t3 = new String[] {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        public String intToRoman(int num) {
            int[] digits = new int[4];
            for(int i=1000, j=0; i>0; i/=10, ++j){
                digits[j] = num / i;
                num = num % i;
            }

            int start = 0;
            for(start = 0; start<4; ++start)
                if(digits[start] > 0) break;

            String result = "";
            for(; start < 4; ++start){
                switch(start){
                    case 0:
                        result = result + t0[digits[0]];
                        break;
                    case 1:
                        result = result + t1[digits[1]];
                        break;
                    case 2:
                        result = result + t2[digits[2]];
                        break;
                    case 3:
                        result = result + t3[digits[3]];
                        break;
                }
            }

            return result;
        }
    }
}
