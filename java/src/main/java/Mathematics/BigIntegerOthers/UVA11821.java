package Mathematics.BigIntegerOthers;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Created by Tom on 18/4/2016.
 *
 * Start at  23:42 and AC at 23:47, total time is 5 minutes.
 *
 * problem: https://onlinejudge.org/external/118/11821.pdf
 * #UVA #big_integer #Lv3
 */
public class UVA11821 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a= sc.nextInt();
        sc.nextLine();

        for(int i=0; i<a; ++i) {
            BigDecimal r = BigDecimal.ZERO;
            while (true) {
                String s = sc.nextLine();
                if (s.trim().compareTo("0") == 0) break;
                BigDecimal bs = new BigDecimal(s);
                r = r.add(bs);
            }

            System.out.println(r.stripTrailingZeros().toPlainString());
        }
    }
}
