package GoogleCodeJam.Y2013.Round1A.B;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2013\\Round1A\\B\\B-test.in";
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

    int E;
    int R;
    int N;
    long[] v;
    long[][] dp = new long[11][11];

    private long solveSmall(int E, int R, int N, long[] v) {
        long ans = 0;
        this.E = E;
        this.N = N;
        this.R = R;
        this.v = v;

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        ans = dphelper(E, 0);

        return ans;
    }

    private long dphelper(int curE, int ptr){
        long r=-1;

        if(ptr==N)
            return 0;

        if(dp[curE][ptr] != -1)
            return dp[curE][ptr];

        for (int i = 0; i <= curE; i++) {
            long t = dphelper(Math.min(curE-i + R, E), ptr+1) + v[ptr]*i;
            if(t > r)
                r = t;
        }

        dp[curE][ptr]= r;
        return r;
    }

    private long solveLarge(int E, int R, int N, long[] v) {
        long ans = 0;
        this.E = E;
        this.N = N;
        this.R = R;
        this.v = v;

        long[] rr = largeHelper(0, 0, N, 0);
        ans = rr[0];
        return ans;
    }

    private long[] largeHelper(int curE, int a, int b, int target){

        if(a == b) return new long[]{0, curE};

        if(target > 0) {
            if (curE + R * (a - b) <= target)
                return new long[]{0, curE + R * (b - a)};
            else {
                //find an activities which is good enough
                int maxIdx = findMaxActivities(a, b);
                //can use up some energy to reach target?
                long[] rr = largeHelper(curE, a, maxIdx, target);
                int use = ((int)rr[1] + R *(b-maxIdx-1)) - target;
                use = Math.max(use, 0);
                long r;
                long[] rr2 = largeHelper(curE - use, maxIdx + 1, b, target);
                r = rr[0] + v[maxIdx] * use + rr2[0];
                debug("assign ", rr2[1], " to task ", maxIdx);

                return new long[]{r, target};
            }
        }
        else{
            int maxIdx = findMaxActivities(a, b);
            long[] rr = largeHelper(curE, a, maxIdx, E);
            long[] rr2 = largeHelper(R, maxIdx + 1, b, 0);
            long r = rr[0];
            r += v[maxIdx] * rr[1];
            debug("assign ", rr[1], " to task ", maxIdx);
            r += rr2[0];

            return new long[]{r, 0};
        }
    }


    private int findMaxActivities(int a, int b){
        int maxIdx = -1;
        long mv = 0;
        for (int i = a; i < b; i++) {
            if(v[i] > mv){
                mv = v[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int E =sc.nextInt();
            int R = sc.nextInt();
            int N = sc.nextInt();
            long[] v = new long[N];
            for (int j = 0; j < N; j++) {
                v[j] = sc.nextLong();
            }
            long r = solveSmall(E, R, N, v);

            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
