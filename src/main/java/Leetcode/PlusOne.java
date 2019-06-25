package Leetcode;

import java.util.Arrays;

public class PlusOne {
    public static void main(String[] args){
        int[] input = {1,2,3};
        Solution s = new Solution();
        int[] t=s.plusOne(input);
        for(int i=0; i<t.length; ++i)
            System.out.print(t[i]);
    }

    static
    class Solution {
        public int[] plusOne(int[] digits) {
            int[] result = new int[digits.length+1];
            boolean carry = true;
            for(int i=result.length-1; i>=0; --i){
                result[i] = (i==0?0:digits[i-1])+(carry?1:0);
                if(result[i] >= 10) {
                    result[i] = result[i] % 10;
                    carry = true;
                }
                else
                    carry=false;
            }
            if(result[0] == 0)
                return Arrays.copyOfRange(result, 1, result.length);
            return result;
        }
    }
}
