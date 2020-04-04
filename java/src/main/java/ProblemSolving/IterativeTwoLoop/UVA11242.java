package ProblemSolving.IterativeTwoLoop;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 17/4/2016.
 *
 * Start at 14:36 and AC at 14:50, total time 14 minutes
 */
public class UVA11242 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int f = sc.nextInt();
            if(f ==0) break;
            int r = sc.nextInt();

            int[] ff = new int[f];
            int[] rr =new int[r];
            for(int i=0; i<f; ++i){
                ff[i] = sc.nextInt();
            }

            for(int i=0; i<r; ++i){
                rr[i] = sc.nextInt();
            }

            double[] dd =new double[f*r];
            for(int i=0; i<f; ++i){
                for(int j=0; j<r; ++j){
                    dd[i*r+j] = ff[i]*1.0 / rr[j];
                }
            }

            Arrays.sort(dd);

            double max= 0;
            for(int i=1; i<f*r; ++i){
                double q = dd[i] / dd[i-1];
                if(q > max) max = q;
            }

            System.out.format("%.2f\n", max);

        }
    }
}
