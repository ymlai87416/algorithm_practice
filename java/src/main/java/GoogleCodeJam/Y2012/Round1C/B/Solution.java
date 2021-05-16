package GoogleCodeJam.Y2012.Round1C.B;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2012\\Round1C\\B\\B-test.in";
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

    int N;
    int M;
    int[] boxType;
    long[] boxNum;
    int[] toyType;
    long[] toyNum;
    HashMap<Pair, Long>[][] dp = new HashMap[103][103];

    static class Pair{
        long a;
        long b;
        public Pair(long a, long b){
            this.a = a; this.b= b;
        }

        @Override
        public int hashCode() {
            return Long.valueOf(a+b).hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Pair){
                Pair o = (Pair)obj;
                return o.a==a && o.b==b;
            }
            else return false;
        }
    }


    private long solve(int N, int M, int[] boxType, long[] boxNum, int[] toyType, long[] toyNum) {
        long ans = 0;

        this.N = N; this.M = M;
        this.boxType = boxType; this.boxNum = boxNum;
        this.toyType = toyType; this.toyNum = toyNum;

        for (int i = 0; i < 103; i++) {
            for (int j = 0; j < 103; j++) {
                dp[i][j] = new HashMap<>();
            }
        }

        ans = helper(0, boxNum[0], 0, toyNum[0]);

        return ans;
    }

    private long helper(int aptr, long aleft, int bptr, long bleft){
        //now we are at...
        if(aptr>= N) return 0;
        if(bptr>=M) return 0;

        Pair param=  new Pair(aleft, bleft);
        if(dp[aptr][bptr].containsKey(param))
            return dp[aptr][bptr].get(param);

        long rr;
        if(boxType[aptr] == toyType[bptr]) {
            long a = Math.min(aleft, bleft);
            if(aleft > bleft){
                long r1 = helper(aptr, aleft-a, bptr+1, toyNum[bptr+1]);
                long r2 = helper(aptr+1, boxNum[aptr+1],bptr+1 , toyNum[bptr+1]);
                rr = Math.max(r1, r2)+a;
                dp[aptr][bptr].put(new Pair(aleft, bleft), rr);
            }
            else if(aleft < bleft){
                long r1 = helper(aptr + 1, boxNum[aptr+1], bptr, bleft-a);
                long r2 = helper(aptr+1, boxNum[aptr+1], bptr+1, toyNum[bptr+1]);
                rr = Math.max(r1, r2)+a;
                dp[aptr][bptr].put(new Pair(aleft, bleft), rr);
            }
            else{
                rr = helper(aptr+1, boxNum[aptr+1], bptr+1, toyNum[bptr+1])+a;
                dp[aptr][bptr].put(new Pair(aleft, bleft), rr);
            }

        }
        else {
            long r1 = helper(aptr + 1, boxNum[aptr+1], bptr, bleft);
            long r2 = helper(aptr, aleft, bptr+1, toyNum[bptr+1]);
            rr = Math.max(r1, r2);
            dp[aptr][bptr].put(new Pair(aleft, bleft), rr);
        }

        debug(boxType[aptr],  aleft, toyType[bptr], bleft, "result ", rr);

        return rr;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[] boxType = new int[N+1];
            long[] boxNum = new long[N+1];
            for (int j = 0; j < N; j++) {
                boxNum[j] = sc.nextLong();
                boxType[j] = sc.nextInt();
            }
            int[] toyType = new int[M+1];
            long[] toyNum = new long[M+1];
            for (int j = 0; j < M; j++) {
                toyNum[j] = sc.nextLong();
                toyType[j] = sc.nextInt();
            }
            long r = solve(N, M, boxType, boxNum, toyType, toyNum);

            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
