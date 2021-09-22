package Mathematics.SimplerOne;

import java.util.Scanner;


/**
 * Created by Tom on 12/4/2016.
 *
 * Log: Create at 2:50, formula at 2:53, WA at 3:02, AC at 3:11, totoal time 21 mins
 *
 * problem: https://onlinejudge.org/external/107/10773.pdf
 * #UVA #math #ad_hoc #Lv2
 */
public class UVA10773 {

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);

        int ntest = sc.nextInt();
        sc.nextLine();
        for(int i=0; i<ntest; ++i){
            System.out.format("Case %d: ", i+1);
            double width = sc.nextDouble();
            double water = sc.nextDouble();
            double boat = sc.nextDouble( );

            double fastest = width / boat;
            if(water >= boat || water==0 || boat==0) {System.out.println("can't determine"); continue;}
            double slowest = width / Math.sqrt(Math.pow(boat, 2) - Math.pow(water, 2));
            System.out.format("%.3f\n", slowest-fastest);
        }
    }
}
