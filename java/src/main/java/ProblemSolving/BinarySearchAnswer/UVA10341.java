package ProblemSolving.BinarySearchAnswer;

import java.util.Scanner;

/**
 * Created by Tom on 18/4/2016.
 */
public class UVA10341 {
    public static double formula(int p, int q, int r, int s, int t, int u, double xx){
        return p*Math.exp(-xx) + q* Math.sin(xx) + r*Math.cos(xx) + s*Math.tan(xx) + t * xx * xx + u;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int p, q, r, s, t, u;
        while(true){
            if(!sc.hasNext()) break;
            p = sc.nextInt(); q = sc.nextInt(); r = sc.nextInt(); s = sc.nextInt(); t = sc.nextInt(); u=sc.nextInt();
            sc.nextLine();

            double min = 0;
            double max = 1;

            if(formula(p, q, r, s, t, u, min) > 0 && formula(p, q, r, s, t, u, max) > 0
                    || formula(p, q, r, s, t, u, min) < 0 && formula(p, q, r, s, t, u, max) < 0) {
                System.out.println("No solution");
                continue;
            }

            double half=0;
            for(int i=0; i<100; ++i){   //TLE if I have to check halfd < 1e-5...
                half = (min+max)/2.0f;
                double halfd = formula(p, q, r, s, t, u, half);
                if(halfd > 0)
                    min = half;
                else
                    max = half;

                //if(Math.abs(halfd) < 1e-5) break;
            }

            System.out.format("%.4f\n", half);

        }
    }
}
