package Leetcode;

/*
url: https://leetcode.com/problems/factorial-trailing-zeroes/
level: easy
solution: count the number of 2, 5 and 10
 */

public class FactorialTrailingZeroes {
    public static void main(String[] args){
        String input = "ZY";
        Solution s = new Solution();
        System.out.println(s.trailingZeroes(1808548329));
    }

    static
    class Solution {
        public int trailingZeroes(int n) {
            long nb =5;
            long r = 0;
            while(nb <= n){
                System.out.format("%d %d\n", nb, n/nb);
                r += n/nb;
                nb = nb*5;
            }
            return (int)r;
        }
    }
}
