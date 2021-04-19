package GoogleCodeJam.Y2010.Round1C.B;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2010\\Round1C\\B\\B-test.in";
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

    int[][] dp = new int[1001][1001];
    private int solve(int L, int P, int C) {
        int ans = 0;

        for (int i = 0; i <=1000; i++) {
            for (int j = 0; j <=1000; j++) {
                dp[i][j] = -1;
            }
        }



        int r = helper(L, P, C);

        return r;
    }

    private int helper(int L, int P, int C){
        if(L >= P) dp[L][P] = 0;
        if(L * C >= P) dp[L][P] = 0;
        if(dp[L][P] != -1) return dp[L][P];

        int test =Integer.MAX_VALUE;
        int bestI = -1;
        for (int i = L+1; i < P; i++) {
            int t = Math.max(helper(L, i, C), helper(i, P, C))+1;
            if(test > t){
                test = t;
                bestI = i;
            }
        }

        dp[L][P] = test;
        return test;
    }

    private int solveBig(int L, int P, int C){
        //just binary search will do
        return helper2(L, P, C);
    }

    private int helper2(long L, long P, int C){
        if(L * C >= P) return 0;
        else{
            double mid = Math.exp((Math.log(L) + Math.log(P)) / 2);
            long mid1 = Math.max(L+1, (long)Math.floor(mid));
            long mid2 = Math.max(L+1, (long)Math.ceil(mid));

            //find out how many number
            int a1, a2, b1, b2;
            a1 = helper2(L, mid1, C);
            b1 = helper2(mid1, P, C);
            if(mid1!=mid2) {
                a2 = helper2(L, mid2, C);
                b2 = helper2(mid2, P, C);
            }
            else{
                a2=a1; b2=b1;
            }

            int ar = Math.max(a1, b1) +1;
            int br = Math.max(a2, b2)+1;
            return Math.min(ar, br);
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int L = sc.nextInt();
            int P = sc.nextInt();
            int C = sc.nextInt();
            out.println(solveBig(L, P, C));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}