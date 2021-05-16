package GoogleCodeJam.Y2012.Round1C.C;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2012\\Round1C\\C\\C-test.in";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            out = new PrintStream(System.out);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    boolean debugflag = true;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private void debug(Object... s){
        if(debugflag) {
            //System.out.println(s);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                sb.append(s[i].toString() + " ");
            }
            System.out.println("\033[0;34m" + sb.toString() + "\033[0;30m");
        }
    }

    double D;
    int N;
    int A;
    double[] t ;
    double[] x;
    double[] a;

    double mv;
    double mx;
    double ma;

    double INF = 10e10;
    private double[] solve(double D, int N, int A, double[] t, double[] x, double[] a) {

        this.D = D;
        this.N = N;
        this.A = A;
        this.t = t;
        this.x =x;
        this.a = a;

        double[] ans = new double[A];

        for (int i = 0; i < A; i++) {
            double ai = a[i];

            if(D == 0){
                ans[0] = 0;
                continue;
            }
            mv = 0;
            mx = 0;
            ma = ai;
            for (int j = 0; j < N-1; j++) {
                //if pass then the car only have mv = other and mx = other
                //if not then
                if(x[j] < D && D<=x[j+1]){
                    //find mid
                    double min = t[j];
                    double max = INF;

                    while(Math.abs(min-max) > 1e-6){
                        double mid = (min+max)/2;
                        boolean p = pass(mid, j);
                        if(p)
                            max = mid;
                        else
                            min = mid;
                    }

                    double otherD = (x[j+1] - x[j]) / (t[j+1]-t[j]) * (max-t[j]) + x[j];
                    if(otherD > D){
                        min = t[j];
                        while(Math.abs(min-max) > 1e-6){
                            double mid = (min+max)/2;
                            double dd = carD(mid, j);
                            if(dd > D)
                                max = mid;
                            else
                                min = mid;
                        }
                        ans[i] = max;
                        debug(i, j, "hah", ans[i]);
                    }
                    else{
                        //follow the car
                        double otherV =  (x[j+1] - x[j]) / (t[j+1]-t[j]);
                        ans[i] = (D - x[j]) /otherV + t[j];
                        debug(i, j, "hah2", ans[i]);
                    }

                    continue;
                }


                //he can stop and just drow down with max v.
                boolean p = pass(t[j+1], j);
                if(!p){
                    mv = mv + ma * (t[j+1]-t[j]);
                    mx =  mv * (t[j+1]-t[j]) + 0.5 * ma * (t[j+1]-t[j]) * (t[j+1]-t[j]) + mx;
                }
                else{
                    //mv =  (x[j+1] - x[j]) / (t[j+1]-t[j]);
                    mv = Math.max( (x[j+1] - x[j]) / (t[j+1]-t[j]), 2*ma*(x[j+1] - x[j]));
                    mx = x[j+1];
                }

                debug(i, j, mx, mv, ma);
            }
        }

        
        return ans;
    }

    private boolean pass(double ct, int i){
        double f1 = (x[i+1] - x[i]) / (t[i+1]-t[i]) * (ct-t[i]) + x[i];
        double f2 = mv * (ct-t[i]) + 0.5 * ma * (ct-t[i]) * (ct-t[i]) + mx;

        if(f1 >= f2) return false;
        else return true;
    }

    private double carD(double ct, int i){
        return mv * (ct-t[i]) + 0.5 * ma * (ct-t[i]) * (ct-t[i]) + mx;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.println("Case #" + i + ":");
            double D = sc.nextDouble();
            int N = sc.nextInt();
            int A = sc.nextInt();
            double[] tt = new double[N];
            double[] x = new double[N];
            double[] a = new double[A];

            for (int j = 0; j < N; j++) {
                tt[j] = sc.nextDouble();
                x[j] = sc.nextDouble();
            }

            for (int j = 0; j < A; j++) {
                a[j] =sc.nextDouble();
            }

            double[] r = solve(D, N, A, tt,x, a);

            for (int j = 0; j <r.length; j++) {
                out.println(r[j]);
            }

        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
