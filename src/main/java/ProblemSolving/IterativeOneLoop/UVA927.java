package ProblemSolving.IterativeOneLoop;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 17/4/2016.
 * Start 16:27, AC at 16:58, tottal time: 31 minutes.
 */
public class UVA927 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int c  = sc.nextInt();
        int[] poly =new int[21];

        for(int i=0; i< c; ++i){
            int ad =sc.nextInt();
            Arrays.fill(poly, 0);
            for(int j=0; j<=ad; ++j){
                poly[j]=  sc.nextInt();
            }

            long d = sc.nextLong();
            long k = sc.nextLong();

            long cnt = (long)Math.ceil((-d+Math.sqrt(d*d-4*d*-2*k)) / 2 / d);

            long result = 0;
            for(int j=0; j<=ad; ++j){
                result += poly[j] * Math.pow(cnt, j);
            }
            System.out.println(result);
        }
    }
}
