package Leetcode;

public class PowerOfThree {
    public static void main(String[] args){
        int a = -14, b=16;
        Solution s = new Solution();
        System.out.println(s.isPowerOfThree(243));
    }

    static
    class Solution {
        public boolean isPowerOfThree(int n) {
            return helper1(n);
        }

        public boolean helper1(int n){
            while(n % 3 == 0){
                n = n/3;
            }
            return n == 1;
        }
    }
}
