package Leetcode;

/**
number: 8
url: https://leetcode.com/problems/string-to-integer-atoi/
level: medium
solution: care about . / 0 / int / decimal

#math #string

 **/

public class StringToIntegerAtoi {
    public static void main(String[] args){
        int a = 0b11111111111111111111111111111101;
        Solution s = new Solution();
        System.out.println(s.myAtoi("-91283472332"));
    }

    static
    class Solution {
        public int myAtoi(String str) {
            boolean start = false, negative= false, valid=true, overflow =false;
            int r = 0; long tr;
            for(int i=0; i<str.length(); ++i){
                char c = str.charAt(i);
                if(start){
                    if(c >= '0' && c<='9'){
                        start = true;
                        tr = (long)r * 10 + (c-'0');
                        if(tr > Integer.MAX_VALUE && !overflow)
                            overflow = true;
                        r = (int) tr;
                    }
                    else{
                        break;
                    }
                }
                else{
                    if(c >= '0' && c<='9'){
                        start = true;
                        r = r * 10 + (c-'0');
                    }
                    else if(c == '-'){
                        negative = true;
                        start = true;
                    }
                    else if(c == '+')
                        start= true;
                    else if(c == ' ') {}
                    else
                        valid = false;
                }


            }

            if(!valid) return 0;
            if(negative && overflow) return Integer.MIN_VALUE;
            if(!negative && overflow) return Integer.MAX_VALUE;
            return r * (negative ? -1:1);
        }
    }

}
