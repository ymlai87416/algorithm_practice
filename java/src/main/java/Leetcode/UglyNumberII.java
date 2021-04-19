package Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
number: 264
problem: https://leetcode.com/problems/ugly-number-ii/
level: medium
solution: 2^a*3^b*5^c.

#math #dp #heap

 **/

public class UglyNumberII {
    public static void main(String[] args){
        int n= 1;
        Solution sol = new Solution();
        System.out.println(sol.nthUglyNumber(n));
    }

    static
    class Solution {
        ArrayList<Integer> uglyNumbers;
        int i2 = 0, i3=0, i5=0;
        public Solution(){
            uglyNumbers = new ArrayList<>();
            uglyNumbers.add(1);
        }

        public int nthUglyNumber(int n) {
            generateUglyNumber(n);
            return uglyNumbers.get(n-1);
        }

        private void generateUglyNumber(int to){
            while (uglyNumbers.size() < to) {

                int next_2 = uglyNumbers.get(i2) * 2;
                int next_3 = uglyNumbers.get(i3) * 3;
                int next_5 = uglyNumbers.get(i5) * 5;

                int minVal = Math.min(next_2, Math.min(next_3, next_5));

                if(next_2 == minVal)
                    i2++;
                if(next_3 == minVal)
                    i3++;
                if(next_5 == minVal)
                    i5++;
                uglyNumbers.add(minVal);
            }
        }
    }
}
