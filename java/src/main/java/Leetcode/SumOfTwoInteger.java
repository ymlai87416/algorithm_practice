package Leetcode;

/*
number: 371
url: https://leetcode.com/problems/sum-of-two-integers/
level: easy
solution: interesting bitwise operation.
 */

public class SumOfTwoInteger {
    public static void main(String[] args){
        int a = -14, b=16;
        Solution s = new Solution();
        System.out.println(s.getSum(a, b));
    }

    static
    class Solution {
        // a > 0, b > 0
        public int getSum(int a, int b){
            if(a < 0 && b < 0){
                return negate(getSumHelper(negate(a), negate(b)));
            }
            else if(a >= 0 && b < 0)
                return getSubstractHelper(a, negate(b));
            else if(a < 0 && b >= 0)
                return getSubstractHelper(b, negate(a));
            else
                return getSumHelper(a, b);

        }

        public int getSumHelper(int a, int b) {
            if(a == 0) return b;
            if(b == 0) return a;

            while(b != 0){
                int carry = a & b;
                a = a ^ b;
                b = carry << 1;
            }

            return a;
        }

        //a > 0, b>0
        public int getSubstractHelper(int a, int b){
            if(a == 0) return b;
            if(b == 0) return a;

            while(b != 0){
                int borrow = (~a) & b; //a = 0 & b = 1
                a = a ^ b;
                b = borrow << 1;
            }

            return a;
        }

        public int negate(int x){
            return ~x + 1;
        }
    }
}
