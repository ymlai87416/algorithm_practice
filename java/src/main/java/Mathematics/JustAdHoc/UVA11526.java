package Mathematics.JustAdHoc;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Tom on 18/4/2016.
 *
 * Start at 22:02 and end at 22:20 AC and got 1 WA because of forgot to cater input < 0.
 *
 * problem: https://onlinejudge.org/external/115/11526.pdf
 * #UVA #adhoc #Lv3
 */
public class UVA11526 {

    public static long H(int n){
        if(n <=0) return 0;

        long res = 0;
        int prevdiv = 0;
        int div = 0;
        int i;
        for(i=1; i<=n; ++i){
            div = n/i;
            res = (res + n/i);
            if(i > 1){
                res += (prevdiv-div)*(i-1);
            }
            if(i >= div) {
                break;
            }

            prevdiv = div;
        }

        for(int j=div+1; j<=i; ++j){
            res -= n/j;
        }

        return res;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();

        for(int i=0; i<c; ++i){
            int a=sc.nextInt();
            System.out.println(H(a));
        }
    }

}
