package Leetcode;

/**
problem: https://leetcode.com/problems/hamming-distance/
level: easy
solution: bit operations

#bit
 */

public class HammingDistance {
    public static void main(String[] args){
        int w1 = 1;
        int w2 = 4;

        Solution s = new Solution();
        System.out.println(s.hammingDistance(w1, w2));
    }


    static
    class Solution {
        public int hammingDistance(int x, int y) {
            int result = 0;
            for(int i=0; i<31; ++i){
                int mask = 1 << i;

                if((x & mask) != (y & mask)) result += 1;
            }

            return result;
        }
    }
}
