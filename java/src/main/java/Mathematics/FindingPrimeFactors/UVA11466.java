package Mathematics.FindingPrimeFactors;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Tom on 19/4/2016.
 *
 * problem: https://onlinejudge.org/external/114/11466.pdf
 *
 * #TLE #skip #UVA #prime #Lv2
 */
public class UVA11466 {

    public static BigInteger bigIntSqRootFloor(BigInteger x)
            throws IllegalArgumentException {
        if (x.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }
        // square roots of 0 and 1 are trivial and
        // y == 0 will cause a divide-by-zero exception
        if (x .equals(BigInteger.ZERO) || x.equals(BigInteger.ONE)) {
            return x;
        } // end if
        BigInteger two = BigInteger.valueOf(2L);
        BigInteger y;
        // starting with y = x / 2 avoids magnitude issues with x squared
        for (y = x.divide(two);
             y.compareTo(x.divide(y)) > 0;
             y = ((x.divide(y)).add(y)).divide(two));
        return y;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            String s = sc.nextLine();
            if(s.trim().compareTo("0") == 0)break;
            BigInteger bs = new BigInteger(s);
            if(bs.isProbablePrime(30)) {
                System.out.println("-1");
                continue;
            }

            BigInteger result = BigInteger.ONE;

            BigInteger bsq = bigIntSqRootFloor(bs);
            if(bsq.multiply(bsq).compareTo(bs) == 0 && bsq.isProbablePrime(30))
                result = bsq;
            else{
                BigInteger cnt = bsq;
                BigInteger next;
                while(true){
                    next= cnt.nextProbablePrime();
                    if(next.compareTo(bs) > 0) break;
                    if(bs.remainder(next) == BigInteger.ZERO)
                        result = next;
                    cnt=next;
                }
            }

            System.out.println(result);
        }
    }
}
