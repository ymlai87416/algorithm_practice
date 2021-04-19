package GoogleCodeJam.Y2009.Round1C.C;

/**
 * Created by Tom on 9/4/2016.
 */
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
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2009\\Round1C\\C\\C-test.in";
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

    int P;
    int Q;
    int[] pr;
    int[][] dp = new int[101][101];

    private int solve(int P, int Q, int[] prisoners) {
        this.P = P;
        this.Q = Q;
        this.pr = prisoners;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return dpHelper(0, Q);
    }

    // Note: off by one error, if calc distance, just use inclusive.
    private int leftOf(int s){
        if(s == 0) return 0;
        else return pr[s-1]+1;
    }
    private int rightOf(int s){
        if(s == Q-1) return P-1;
        else return pr[s+1]-1;
    }

    private int dpHelper(int s, int e){
        if(s == e) return 0;
        if(dp[s][e] != -1) return dp[s][e];

        int ans = Integer.MAX_VALUE;
        int temp;
        int releaseS = Math.max(rightOf(e-1) - leftOf(s) , 0);
        debug("f("+s + " " + e + ") fc: " + releaseS);
        for (int i = s; i < e; i++) {
            temp = dpHelper(s, i) + dpHelper(i+1, e) + releaseS;
            if(temp < ans)
                ans = temp;
        }
        dp[s][e] = ans;
        return ans;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int P = sc.nextInt();
            int Q = sc.nextInt();
            int[] prisoners = new int[Q];
            for (int j = 0; j < Q; j++) {
                prisoners[j] = sc.nextInt()-1;
            }
            System.out.println(solve(P, Q, prisoners));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}