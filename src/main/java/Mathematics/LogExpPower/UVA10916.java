package Mathematics.LogExpPower;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Tom on 13/4/2016.
 * Start at 1:25, completed at 1:40, total time consume: 15 minutes. Sometime log can be useful than bigInt.
 */
public class UVA10916 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int a = sc.nextInt();
            if(a == 0) break;

            int year10 = a/10 - 196;
            int base = 4;

            int curr = base * (int)Math.pow(2, year10);

            int cnt = 0;
            double result = 0;
            while(true){
                ++cnt;
                result += Math.log(cnt) / Math.log(2);
                if(result >= curr) break;
            }

            System.out.println(cnt-1);
        }
    }
}
