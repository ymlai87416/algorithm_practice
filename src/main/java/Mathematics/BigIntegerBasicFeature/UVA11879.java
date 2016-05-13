package Mathematics.BigIntegerBasicFeature;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Tom on 13/4/2016.
 * Start at 1:40 and finish at 1:47 total of 7 minutes
 */
public class UVA11879 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        BigInteger b17 = BigInteger.valueOf(17L);

        while(true){
            String in = sc.nextLine();
            BigInteger a = new BigInteger(in);
            if(a.compareTo(BigInteger.ZERO) == 0) break;

            if(a.mod(b17) == BigInteger.ZERO) System.out.println(1);
            else System.out.println(0);
        }
    }
}
