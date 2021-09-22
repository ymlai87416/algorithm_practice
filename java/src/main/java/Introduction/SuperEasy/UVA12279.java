package Introduction.SuperEasy;

import java.util.Scanner;

/**
 * Created by Tom on 14/5/2016.
 *
 * problem: https://onlinejudge.org/external/122/12279.pdf
 * #UVA #Lv1 #ad_hoc
 */
public class UVA12279 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int ci =0;
        while(true){
            int a = sc.nextInt();
            if(a == 0) break;

            int r = 0;

            for(int i=0; i<a; ++i){
                int b=  sc.nextInt();
                if(b == 0) r--;
                else r++;
            }

            System.out.format("Case %d: %d\n", ++ci, r);

        }
    }
}
