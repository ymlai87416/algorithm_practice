package Leetcode.Biweek70;

/**
 problem: https://leetcode.com/problems/count-the-hidden-sequences/
 level: medium
 solution: just create a base 0 sequence and you are good to go

 #math
 **/

public class CountTheHiddenSequence {

    public int numberOfArrays(int[] differences, int lower, int upper) {
        int t = 0;
        int len = differences.length;
        long[] base0 = new long[len+1];
        base0[0] = 0;
        long tmin = 0, tmax=0;

        for(int i=0; i<len; ++i){
            base0[i+1] = base0[i] + differences[i];
            if(tmin > base0[i+1]) tmin =  base0[i+1];
            if(tmax < base0[i+1]) tmax =  base0[i+1];
        }

        long ulRange = (long)upper - (long)lower;
        long sRange = tmax-tmin;

        boolean possible = ulRange >= sRange;
        if(!possible) return 0;
        else{
            t  = (int)(ulRange - sRange + 1);
        }

        return t;
    }

    public static void main(String[] args){
        CountTheHiddenSequence s = new CountTheHiddenSequence();

        int[] el1 = {1,-3,4};
        int[] el2 = {3,-4,5,1,-2};
        int[] el3 = {4,-7,2};
        int[] el4 = {-40};

        System.out.println(s.numberOfArrays(el4, -46, 53));
        System.out.println(s.numberOfArrays(el1, 1, 6));
        System.out.println(s.numberOfArrays(el2, -4, 5));
        System.out.println(s.numberOfArrays(el3, 3, 6));
    }
}
