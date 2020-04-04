package Introduction.Time;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Tom on 13/4/2016.
 *
 * Start at 0:23 and AC at 0:34 total: 11 minutes
 */
public class UVA893 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int ad = sc.nextInt();
            int day = sc.nextInt();
            int month = sc.nextInt();
            int year = sc.nextInt();

            if(ad == 0 && day == 0 && month == 0 && year == 0) break;

            Calendar c = Calendar.getInstance();
            c.set(year, month-1, day);
            c.add(Calendar.DATE, ad);  // number of days to add

            int rd, rm, ry;
            Date result = c.getTime();
            rd = c.get(Calendar.DAY_OF_MONTH);
            rm = c.get(Calendar.MONTH);
            ry = c.get(Calendar.YEAR);

            System.out.format("%d %d %d\n", rd, rm+1, ry);
        }
    }
}
