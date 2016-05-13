package Mathematics.BigIntegerBasicFeature;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Tom on 13/4/2016.
 * Start at 1:47, end at 1:54 total of 7 minutes AC.
 */
public class UVA10523 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            if(!sc.hasNext()) break;

            int a = sc.nextInt();
            int b = sc.nextInt();
            sc.nextLine();

            BigInteger result = BigInteger.ZERO;
            for(int i=1; i<=a; ++i){
                result = result.add(BigInteger.valueOf(i).multiply(BigInteger.valueOf(b).pow(i)));
            }
            System.out.println(result.toString());
        }
    }
}
