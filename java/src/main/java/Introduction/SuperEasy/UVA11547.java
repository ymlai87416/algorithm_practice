package Introduction.SuperEasy;

import java.util.Scanner;

/**
 * Created by Tom on 14/5/2016.
 *
 * problem: https://onlinejudge.org/external/115/11547.pdf
 * #UVA #Lv0 #ad_hoc
 */
public class UVA11547 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int i=0; i<nc; ++i){
            long a = sc.nextInt();

            double da = (a * 567.0 / 9 + 7492) * 235 / 47 - 498;

            long dai = (int)Math.floor(da);
            String dais = String.valueOf(dai);
            System.out.println(dais.charAt(dais.length()-2));
        }
    }
}
