package ProblemSolving.GreedyClassical;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 22/4/2016.
 * Start at 0:33 and finished at 0:42, total time spent: 9 mins.
 */
public class UVA11389 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            int n = sc.nextInt();
            int d = sc.nextInt();
            int r = sc.nextInt();
            if(n==0 && d==0 && r==0) break;

            int day[] = new int[n];
            int night[] = new int[n];

            for(int i=0; i<n; ++i){
                day[i] = sc.nextInt();
            }
            for(int i=0; i<n; ++i){
                night[i] = sc.nextInt();
            }

            Arrays.sort(day);
            Arrays.sort(night);

            int ot =0;
            for(int i=0; i<n; ++i){
                int td = day[i] + night[n-i-1];
                ot += Math.max(0, (td - d)) * r;
            }

            System.out.println(ot);
        }
    }
}
