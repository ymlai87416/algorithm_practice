package Introduction.Easy;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Tom on 13/4/2016.
 *
 * Starrt at 13:16 and AC at 13:59. .... you should do the formula... first.. total 43 minutes, and forgot downpayment..
 *
 *
 * #simulation
 */
public class UVA10114 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            double loan_p = sc.nextDouble();
            double loan_m = sc.nextDouble();
            double loan_t = sc.nextDouble();
            int r = sc.nextInt();

            if(loan_p < 0)
                break;

            HashMap<Integer, Double> records = new HashMap<>();
            for(int i=0; i<r; ++i){
                int m = sc.nextInt();
                double dep = sc.nextDouble();
                records.put(m, dep);
            }

            int i;
            double vl, vd;
            vl = loan_t; vd=(loan_t+loan_m)*(1-records.get(0));
            double dr = records.get(0);
            i = 0;
            if(vl > vd || equal(vl, vd)) {
                for (i = 1; i <= loan_p; ++i) {
                    if (records.containsKey(i))
                        dr = records.get(i);

                    vl -= (loan_t / loan_p);
                    vd = vd * (1 - dr);
                    if (vl < vd && !equal(vl, vd)) break;
                }
            }
            if(i==1) System.out.println("1 month");
            else System.out.format("%d months\n", i);
        }
    }

    static boolean  equal(double d1, double d2) { double d = d1 / d2; return (Math.abs(d - 1.0) < 0.00001); }
}
