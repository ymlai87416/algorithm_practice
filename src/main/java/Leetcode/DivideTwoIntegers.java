package Leetcode;

public class DivideTwoIntegers {
    public static void main(String[] args){
        int dd = -2147483648;
        int dr = -1;
        DivideTwoIntegers.Solution s = new DivideTwoIntegers.Solution();
        System.out.println(s.divide(dd, dr));
    }


    static
    class Solution {
        public int divide(int dividend, int divisor) {
            long r;
            long ldd = dividend;
            long ldr = divisor;

            if(dividend < 0 && divisor > 0)
                r = -findAns(-ldd, ldr, 0);
            else if (dividend > 0 && divisor < 0)
                r = -findAns(ldd, -ldr, 0);
            else if (dividend < 0 && divisor < 0)
                r = findAns(-ldd, -ldr, 0);
            else
                r = findAns(ldd, ldr, 0);

            return (int)Math.max(-2147483648, Math.min(r, 2147483647));  //min
        }

        //[start, end)
        private long findAns(long dividend, long divisor, long base){
            //System.out.format("%d %d %d\n", dividend, divisor, base);
            if(dividend < divisor)
                return base;

            //find 2 power
            long a = divisor;
            long am = 1;
            while(a <= dividend){
                a = a+a;
                am = am+am;
            }

            return findAns(dividend-(a >> 1), divisor, (am >> 1) +base);
        }
    }
}
