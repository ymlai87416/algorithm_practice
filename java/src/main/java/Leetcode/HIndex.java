package Leetcode;

import java.util.Arrays;

/*
url: https://leetcode.com/problems/climbing-stairs/
level: medium
solution: h index, just sort it in ascending order. and find out the h index
        second way, store the cumulative frequency list.
 */

public class HIndex {
    public static void main(String[] args){
        int[] citations = new int[]{3,0,6,1,5};
        Solution s = new Solution();
        System.out.println(s.hIndex(citations));
    }

    static
    class Solution {


        public int hIndex(int[] citations) {
            return helper2(citations);
        }

        //O(nlogn)
        public int helper(int[] citations) {
            Arrays.sort(citations);

            int hMax = 0;
            for(int i=0; i<citations.length; ++i){
                int h = citations.length-i;
                if(citations[i] >= h) {
                    hMax = h;
                    break;
                }
            }

            return hMax;
        }

        //O(n)
        //h can never > N
        public int helper2(int[] citations) {
            int[] f = new int[citations.length+1];

            for(int i=0; i<citations.length; ++i){
                int fi = Math.min(citations[i],citations.length);
                f[fi]++;
            }

            if(f[f.length-1] == citations.length)
                return f.length-1;

            for(int i=f.length-2; i>=0; --i){
                f[i] = f[i+1]+f[i];
                if(f[i] >= i)
                    return i;
            }
            return 0;
        }
    }
}
