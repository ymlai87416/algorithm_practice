package Mathematics.BigIntegerBaseNumberConversion;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Tom on 13/4/2016.
 * Start at 17:55, AC at 18:00, total time 5mins
 */
public class UVA10551 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int a = sc.nextInt();
            if( a == 0) break;

            String n1 = sc.next();
            String n2 = sc.next();

            BigInteger b = new BigInteger(n1, a);
            BigInteger c =new BigInteger(n2, a);

            BigInteger d = b.mod(c);
            System.out.println(d.toString(a));
        }
    }
}
