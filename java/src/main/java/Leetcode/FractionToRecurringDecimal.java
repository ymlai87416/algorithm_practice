package Leetcode;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;

/*
problem: https://leetcode.com/problems/fraction-to-recurring-decimal/
level: medium
solution: store the remainder and the position, if I saw the remainder again. I know there is a loop

#hashTable #math
 */

public class FractionToRecurringDecimal {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.fractionToDecimal(-1, -2147483648));
    }


    static
    class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            String sign = ((numerator > 0 ? 1: numerator < 0 ? -1 : 0) * (denominator >= 0 ? 1 :denominator < 0 ? -1 : 0) >= 0) ? "" : "-";

            long _numerator = Math.abs((long)numerator);
            long _denominator = Math.abs((long)denominator);

            long integerPart = _numerator / _denominator;
            long remainder = _numerator % _denominator;

            if(remainder == 0) return sign + String.valueOf(integerPart);

            long quotient = 0;
            HashMap<Long, Integer> pos = new HashMap<>();

            int recurStart=-1, recurEnd=-1;
            String decimal = "";
            int curP = 0;
            pos.put(remainder, 0);

            while(true){
                curP++;
                long dividend = remainder*10;
                if(dividend < _denominator){
                    quotient = 0;
                    remainder = dividend;
                }
                else {
                    quotient = dividend / _denominator;
                    remainder = dividend % _denominator;
                }

                decimal += quotient;

                if(remainder == 0)
                    break;
                else if(!pos.containsKey(remainder))
                    pos.put(remainder, curP);
                else{
                    recurStart = pos.get(remainder);
                    decimal = decimal.substring(0, recurStart) + "(" + decimal.substring(recurStart) + ")";
                    break;
                }
            }

            return sign + integerPart + "." + decimal;
        }


    }
}

        /*
            if(isTerminating(denominator)){
                BigDecimal d = BigDecimal.valueOf(numerator).divide(BigDecimal.valueOf(denominator));
                return d.toString();
            }
            else{
                int m = get10Multiple(denominator);
                int newNumerator = numerator*m;
                int newDenominator = denominator*m;

                int t = newDenominator; int nonR = 0;
                while(t % 10 == 0){
                    t /=10;
                    nonR++;
                }

                String nines = "9";
                while(true){
                    if(new BigInteger(nines).mod(BigInteger.valueOf(t)).compareTo(BigInteger.ZERO) == 0){
                        //now we get the precision. haha
                        int precision = nonR + nines.length();
                        BigDecimal d = BigDecimal.valueOf(numerator).divide(BigDecimal.valueOf(denominator), precision, RoundingMode.FLOOR);

                        String r = d.toString();
                        StringBuilder rs = new StringBuilder();
                        int i=0;
                        for(i=0; i<r.length(); ++i){
                            rs.append(r.charAt(i));
                            if(r.charAt(i) == '.') break;
                        }
                        ++i;
                        rs.append('.');
                        for(int j=0; j<nonR; ++j){
                            rs.append(r.charAt(i));
                            ++i;
                        }
                        rs.append('(');
                        for(int j=0; j<nines.length(); ++j){
                            rs.append(r.charAt(i));
                            ++i;
                        }
                        rs.append(')');

                        return rs.toString();
                    }
                    nines = nines+"9";
                }
            }
        }

        private boolean isTerminating(int denominator){
            int p2, p5; p2=p5=0;
            while(denominator%2 == 0){
                denominator/=2;
                p2++;
            }
            while(denominator%5 == 0){
                denominator/=5;
                p5++;
            }

            return denominator == 1;
        }

        private int get10Multiple(int denominator){
            //turn d into somewhat where can be divided by 9999...9
            int p2, p5; p2=p5=0;
            while(denominator%2 == 0){
                denominator/=2;
                p2++;
            }
            while(denominator%5 == 0){
                denominator/=5;
                p5++;
            }

            int r=1;
            if(p2 < p5){
                for(int i=0; i<p5-p2; ++i)
                    r *=2;
            }
            else{
                for(int i=0; i<p5-p2; ++i)
                    r *=5;
            }

            return r;
        }
        */