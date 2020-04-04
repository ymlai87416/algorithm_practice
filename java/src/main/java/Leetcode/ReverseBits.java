package Leetcode;

/*
number: 380
url: https://leetcode.com/problems/climbing-stairs/
level: easy
solution: bitwise op and flip
 */

public class ReverseBits {
    public static void main(String[] args){
        int a = 0b11111111111111111111111111111101;
        Solution s = new Solution();
        System.out.println(s.reverseBits(a));
    }

    static
    public class Solution {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            for(int i=0, j=31; i<j; ++i, --j){
                int a = n & (1 << i);
                int b = n & (1 << j);

                //set or clear bit i according to b
                if(b != 0) n = n | (1<<i);
                else n = n & ~(1<<i);

                //set or clear bit j according to a
                if(a != 0) n = n | (1<<j);
                else n = n & ~(1<<j);
            }
            return n;
        }

    }
}
