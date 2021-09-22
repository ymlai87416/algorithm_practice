package Mathematics.BigIntegerPrimalityTesting;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Tom on 18/4/2016.
 *
 * problem: https://onlinejudge.org/external/102/10235.pdf
 * #UVA #big_integer #primality_testing #Lv1
 */
public class UVA10235 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            if(!sc.hasNext()) break;
            String sa = sc.nextLine();
            String rsa = new StringBuilder(sa).reverse().toString();

            BigInteger ba = new BigInteger(sa);
            BigInteger bra =new BigInteger(rsa);

            boolean pba = ba.isProbablePrime(30);
            boolean pbra = bra.isProbablePrime(30);
            if(pba && pbra && ba.compareTo(bra) != 0){
                System.out.format("%s is emirp.\n", sa);
            } else if(pba){
                System.out.format("%s is prime.\n", sa);
            } else{
                System.out.format("%s is not prime.\n", sa);
            }
        }
    }
}
