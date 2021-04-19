package GoogleCodeJam.Y2011.Round1B.A;

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
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2011\\Round1B\\A\\A-test.in";
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

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private double[] solve(int N, int[][] m) {
        double[] r = new double[N];
        int[] wg = new int[N];
        int[] pg =new int[N];
        double[] wp = new double[N];
        for (int i = 0; i < N; i++) {
            int t = 0;
            pg[i] = 0;
            for (int j = 0; j < N; j++) {
                if(m[i][j] == -1) continue;

                t += m[i][j];

                pg[i]+=1;

            }
            wg[i] = t;
            wp[i] = 1.0*t/pg[i];
        }
        
        StringBuilder sb;
        sb= new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(wp[i] + " ");
        }
        debug("WP: " + sb.toString());


        double[] owp = new double[N];
        for (int i = 0; i < N; i++) {
            double a = 0;
            for (int j = 0; j < N; j++) {
                //team i never play with j
                if(m[i][j] == -1) continue;

                int t = wg[j] - m[j][i];
                a += 1.0* t/(pg[j] -1);
            }
            owp[i] = a/pg[i];
        }

        sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(owp[i] + " ");
        }
        debug("OWP: "+sb.toString());
        
        double[] oowp = new double[N];
        for (int i = 0; i < N; i++) {
            double a = 0;
            for (int j = 0; j < N; j++) {
                if(m[i][j] == -1) continue;
                a += owp[j];
            }
            oowp[i] = a/(pg[i]);
        }

        sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(oowp[i] + " ");
        }
        debug("OOWP: "+ sb.toString());

        for (int i = 0; i < N; i++) {
            r[i] = 0.25 * wp[i] + 0.5 * owp[i] + 0.25 * oowp[i];
        }
        return r;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        
        for (int i = 1; i <= t; i++) {
            int N = sc.nextInt();
            sc.nextLine();
            int[][] m = new int[N][N];
            for (int j = 0; j < N; j++) {
                String b =sc.nextLine();
                for (int k = 0; k < N; k++) {
                    if(b.charAt(k) == '.') m[j][k] = -1;
                    else if(b.charAt(k) == '0') m[j][k]=0;
                    else if(b.charAt(k) == '1') m[j][k]=1;
                }
            }
            out.print("Case #" + i + ": ");
            double[] r = solve(N, m);
            out.println();
            for (int j = 0; j < r.length; j++) {
                out.format("%.12f\n", r[j]);
            }
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}