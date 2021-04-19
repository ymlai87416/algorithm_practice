package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
number: 43
problem: https://leetcode.com/problems/multiply-strings/
level: medium
solution: just do the multiplication by hand.

#math #string

 **/

public class MultiplyStrings {
    public static void main(String[] args) {
        String a = "123";
        String b = "456";
        Solution sol = new Solution();
        String r = sol.multiply(a, b);
        System.out.println(r);
    }

    static
    class Solution {
        public String multiply(String num1, String num2) {
            int[] t = new int[num1.length()+num2.length()];

            for(int i=0; i<num1.length(); ++i){
                for(int j=0; j<num2.length(); ++j){
                    int c1 = num1.charAt(num1.length()-i-1)-'0';
                    int c2 = num2.charAt(num2.length()-j-1)-'0';
                    t[i+j] += (c1*c2);
                }
            }

            StringBuilder r = new StringBuilder();
            for(int i=0; i<t.length-1; ++i){
                int carry = t[i] / 10;
                t[i+1] += carry;
                r.insert(0, (t[i] % 10));
            }
            r.insert(0,  t[t.length-1]);

            int leadZero = 0;
            String r2=r.toString();
            while(leadZero < r2.length() && r2.charAt(leadZero) == '0') ++ leadZero;
            r2=r2.substring(leadZero);
            return r2.isEmpty() ? "0" : r2;
        }
    }
}
