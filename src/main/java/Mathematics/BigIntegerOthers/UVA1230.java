package Mathematics.BigIntegerOthers;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Tom on 18/4/2016.
 * Start at 11:23 and end at 11:33, total time is 10 mins
 */
public class UVA1230 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        for(int i=0; i<a; ++i){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int n = sc.nextInt();

            BigInteger bx = BigInteger.valueOf(x);
            BigInteger by = BigInteger.valueOf(y);
            BigInteger bn = BigInteger.valueOf(n);

            System.out.println(bx.modPow(by, bn));
        }
    }
}
