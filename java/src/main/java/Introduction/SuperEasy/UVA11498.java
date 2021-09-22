package Introduction.SuperEasy;

import java.util.Scanner;

/**
 * Created by Tom on 13/4/2016.
 * Start at 20:59 and AC at 21:09
 *
 * problem: https://onlinejudge.org/external/114/11498.pdf
 * #UVA #Lv0 #ad_hoc
 */
public class UVA11498 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            int a = sc.nextInt();
            if(a == 0) break;

            int x = sc.nextInt();
            int y = sc.nextInt();

            for(int i=0; i<a; ++i){
                int qx = sc.nextInt();
                int qy = sc.nextInt();

                if(qx == x || qy == y) System.out.println("divisa");
                else if(qx > x && qy > y) System.out.println("NE");
                else if(qx > x && qy < y) System.out.println("SE");
                else if(qx < x && qy > y) System.out.println("NO");
                else if(qx < x && qy < y) System.out.println("SO");

            }
        }
    }
}
