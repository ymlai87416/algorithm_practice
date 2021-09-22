package Mathematics.BigIntegerOthers;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Tom on 18/4/2016.
 * Start at 23:33 and WA (i think it is integer only...) and AC at 23:39, total time: 6 minutes.
 *
 * problem: https://onlinejudge.org/external/108/10814.pdf
 *
 * #UVA #
 */
public class UVA10814 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();

        for(int i=0; i<a; ++i){
            String b = sc.next();
            sc.next();
            String c = sc.next();
            BigInteger bb = new BigInteger(b);
            BigInteger cc = new BigInteger(c);

            BigInteger r = bb.gcd(cc);
            System.out.format("%s / %s\n", bb.divide(r).toString(), cc.divide(r).toString());
        }
    }
}
