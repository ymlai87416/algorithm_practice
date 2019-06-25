package Leetcode;

import java.util.HashSet;

public class HappyNumber {
    public static void main(String[] args){
        int a = 19;
        Solution s = new Solution();
        System.out.println(s.isHappy(a));
    }

    static
    class Solution {
        public boolean isHappy(int n) {
            int nextN;

            HashSet<Integer> loop =new HashSet<>();
            while(n != 1) {
                if(!loop.add(n))
                    return false;
                nextN = 0;
                while (n != 0) {
                    nextN += (n % 10) * (n % 10);
                    n = n /10;
                }
                n = nextN;
            }

            return true;
        }
    }
}
