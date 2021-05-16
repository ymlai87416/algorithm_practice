package MoreAdvanceTopic.Dp;

/**
 problem: https://onlinejudge.org/external/109/10911.pdf
 level:
 solution:

 #dp #minimumWeightPerfectMatchingGeneral

 **/

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UVA10911 {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\MoreAdvanceTopic\\Dp\\UVA10911.in";
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

    double[] dp = new double[1048576];
    int[] x;
    int[] y;
    int N;
    double dist[][] = new double[21][21];

    static int NumberOfSetBits(int i)
    {
        i=i-((i>>>1)&0x55555555);
        i=(i&0x33333333)+((i>>>2)&0x33333333);
        return(((i+(i>>>4))&0x0F0F0F0F)*0x01010101)>>>24;
    }

    static double INF = 999999999;
    private double solve(int N, int[] x, int[] y) {
        this.N = N; this.x = x; this.y = y;

        for (int i = 0; i < 1048576; i++) {
            dp[i] = INF;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = Math.sqrt((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));
            }
        }
        int bitmask = 0;
        return dpHelper(bitmask);
    }

    private double dpHelper(int bitmask){
        //pair up someone still not in a group and then return
        if(dp[bitmask] != INF) return dp[bitmask];
        if(NumberOfSetBits(bitmask) == N) return 0;

        double result = INF;
        for (int i = 0; i < N; i++) {
            if((bitmask & (1 << i)) != 0) continue;
            for (int j = 0; j < N; j++) {
                if(i==j) continue;
                if((bitmask & (1 << j)) != 0) continue;
                double temp = dpHelper(bitmask | (1 << i) | (1 << j)) + dist[i][j];
                if(temp < result)
                    result = temp;
            }
        }

        dp[bitmask] =result;
        return result;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int i =0;
        while(true){
            int N = sc.nextInt();
            if(N == 0) break;
            ++i;
            String line;
            int[] x = new int[2*N]; int[]y = new int[2*N];
            sc.nextLine();
            for (int j = 0; j < 2*N; j++) {
                line = sc.nextLine();
                String[] token = line.split(" ");
                x[j] = Integer.parseInt(token[1]);
                y[j] = Integer.parseInt(token[2]);
            }

            out.print("Case " + i + ": ");
            double r = solve(2*N, x, y);

            out.format("%.2f\n", r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new UVA10911().run();
    }
}
