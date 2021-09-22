package Mathematics.MathSimulationHarder;

import java.util.Scanner;

/**
 * Created by Tom on 12/4/2016.
 *
 * 19:08 first read, 20:24 AC. 2 WA.
 * Too far fetch for the formula, the coding is pretty easy...
 *
 * problem: https://onlinejudge.org/external/6/616.pdf
 * #UVA #math_simulation #Lv3
 */
public class UVA616 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int read = sc.nextInt();
            if(read == -1) break;
            boolean found = false;

            for(int i=9; i>1; --i){
                found = true;
                int n=read, nn=0;

                for(int j=0; j<i; ++j){
                    nn = n / i;

                    if(nn * i + 1 != n) {
                        found = false;
                        break;
                    }
                    n=nn*(i-1);
                }

                if(found) found = ((n/i) *i == n);

                if(found) {
                    System.out.format("%d coconuts, %d people and 1 monkey\n", read, i);
                    found = true;
                    break;
                }
            }

            if(found) continue;
            System.out.format("%d coconuts, no solution\n", read);
        }

    }
}
