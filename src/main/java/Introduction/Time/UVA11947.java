package Introduction.Time;

import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by Tom on 14/4/2016.
 *
 * Start at 0:23 and AC at 0:37, total of 14 minutes
 */
public class UVA11947 {
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        int a = sc.nextInt();
        sc.nextLine();
        for(int i=0; i<a; ++i){
            String lastd = sc.nextLine();
            int m = Integer.parseInt(lastd.substring(0, 2));
            int d = Integer.parseInt(lastd.substring(2, 4));
            int y = Integer.parseInt(lastd.substring(4, 8));

            Calendar c = Calendar.getInstance();
            c.set(y, m-1, d);
            c.add(Calendar.DATE, 40*7);

            int rm = c.get(Calendar.MONTH);
            int rd = c.get(Calendar.DAY_OF_MONTH);
            int ry = c.get(Calendar.YEAR);

            String scop = "";

            switch(rm){
                case 0:
                    scop = rd < 21 ? "capricorn" : "aquarius"; break;
                case 1:
                    scop = rd < 20 ? "aquarius" : "pisces"; break;
                case 2:
                    scop = rd < 21 ? "pisces" : "aries"; break;
                case 3:
                    scop  = rd < 21 ? "aries" : "taurus"; break;
                case 4:
                    scop = rd < 22 ? "taurus" : "gemini"; break;
                case 5:
                    scop = rd < 22 ? "gemini" : "cancer"; break;
                case 6:
                    scop = rd < 23 ? "cancer" : "leo" ; break;
                case 7:
                    scop = rd < 22 ? "leo" : "virgo"; break;
                case 8 :
                    scop = rd < 24 ? "virgo" : "libra"; break;
                case 9:
                    scop = rd < 24 ? "libra" : "scorpio"; break;
                case 10:
                    scop = rd < 23 ? "scorpio" : "sagittarius"; break;
                case 11:
                    scop = rd < 23 ? "sagittarius" : "capricorn"; break;
            }
            System.out.format("%d %02d/%02d/%04d %s\n", i+1, rm+1, rd, ry, scop);
        }
    }
}
