package GoogleCodeJam.Y2010.Round1B.B;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2010\\Round1B\\B\\B-test.in";
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

    int PASS = 10;
    int FAILED = 20;

    private int solve(int N, int K, long B, long T, int[] dist, int[] v) {
        int ans = 0;

        int[] status = new int[N];
        int failedCount = 0;

        int tCnt = K;
        for (int i = N-1; i >=0; i--) {
            boolean canPass =  v[i] * T >= (B-dist[i]);
            if(!canPass) {
                status[i] = FAILED;
                failedCount++;
                continue;
            }

            //here, all chicken can pass
            /*
            if(i==N-1) {
                status[i] = PASS;
                tCnt--;
                debug("C: " + i + " pass");
            }
            else if(status[i+1] == PASS){
                status[i] = PASS;
                tCnt--;
                debug("C: " + i + " pass");
                //if not, can walk with chasing
            } else {
                //how many failed chicken ahead?

            }*/

            ans += failedCount;
            status[i] = PASS;
            tCnt--;
            debug("C: " + i + " pass by take over " + failedCount + "C");

            if(tCnt == 0)
                break;
        }

        if(N-failedCount < K) ans = -1;

        return ans;
    }

    static class Event{
        int c1;
        int c2;
        double t;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            int K = sc.nextInt();
            int B = sc.nextInt();
            int T = sc.nextInt();
            int[] dist = new int[N];
            int[] v = new int[N];
            for (int j = 0; j < N; j++) {
                dist[j] = sc.nextInt();
            }
            for (int j = 0; j < N; j++) {
                v[j] = sc.nextInt();
            }
            int ans = solve(N, K, B, T, dist, v);

            if(ans==-1)
                System.out.println("IMPOSSIBLE");
            else
                System.out.println(ans);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}