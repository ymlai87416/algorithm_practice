package Introduction.SuperEasy;

import java.util.Scanner;

/**
 * Created by Tom on 21/4/2016.
 * Start at 3:09 and Ac at 3:12, total of 3 minutes.
 *
 * problem: https://onlinejudge.org/external/110/11044.pdf
 * #UVA #Lv1 #ad_hoc
 */
public class UVA11044 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for(int i=0; i<t;++i){
            int m, n;
            m = sc.nextInt();
            n = sc.nextInt();

            int rowsonar = ((m-2)/3) + ((m-2)%3 == 0 ? 0: 1);
            int rowsonar2 = ((n-2)/3) + ((n-2)%3 == 0 ? 0: 1);

            System.out.println(rowsonar * rowsonar2);
        }
    }
}
