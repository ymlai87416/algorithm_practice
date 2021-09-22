package Introduction.SuperEasy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 14/5/2016.
 *
 * problem: https://onlinejudge.org/external/113/11364.pdf
 * #UVA #Lv1 #ad_hoc
 */
public class UVA11364 {
    static int[] shop = new int[21];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int i=0; i<nc; ++i){
            int ns = sc.nextInt();
            for(int j=0; j<ns; ++j){
                shop[j] = sc.nextInt();
            }

            Arrays.sort(shop, 0, ns);

            System.out.println((shop[ns-1] - shop[0])*2);
        }
    }
}
