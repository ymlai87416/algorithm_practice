package Leetcode;

/*
url: https://leetcode.com/problems/counting-bits/
level: medium

keep expanding the result from 1 -> 2 -> 4 -> n
 */

public class CountingBits {
    public static void main(String[] args){
        Solution sol = new Solution();
        System.out.println(sol.countBits(5));
    }

    static
    class Solution {
        public int[] countBits(int num) {
            int[] r = new int[num+1];

            if(num >= 1){
                r[0] = 0; r[1] = 1;
            }

            int pow2 = 1;
            do{
                pow2 = pow2 * 2;
                for(int i=pow2; i<=Math.min(num, pow2*2-1); ++i){
                    r[i] = r[i-pow2]+1;
                }
            }while(pow2 < num);

            return r;
        }
    }
}
