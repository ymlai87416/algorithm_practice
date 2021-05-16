package GoogleCodeJam.Y2011.Round1C.B;

import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
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
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2011\\Round1C\\B\\B-test.in";
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

    //dp[planet][time][Booster left]
    private BigInteger solve(int L, long t, int N, long[] dist) {

        //when ship arrive the star k without booster
        //how many time reduce by building acc on star k
        //sort and find the time saved and then substract from total?
        BigInteger[] timeReachedNoBoost = new BigInteger[N+1];
        BigInteger tt = BigInteger.ZERO;
        timeReachedNoBoost[0] = BigInteger.ZERO;
        for (int i = 1; i < N+1; i++) {
            tt = tt.add(BigInteger.valueOf(dist[i-1]*2));
            timeReachedNoBoost[i] = tt;
        }

        BigInteger[] tReduce = new BigInteger[N+1];
        BigInteger bt = BigInteger.valueOf(t);
        tReduce[0] = BigInteger.ZERO;
        for (int i = 1; i < N+1; i++) {
            if(timeReachedNoBoost[i].compareTo(bt) > 0){

                BigInteger startTime = bt.compareTo(timeReachedNoBoost[i-1]) >= 0 ? bt: timeReachedNoBoost[i-1];

                tReduce[i] = (timeReachedNoBoost[i].subtract(startTime)).divide(BigInteger.TWO);
            }
            else
                tReduce[i] = BigInteger.ZERO;
        }

        Arrays.sort(tReduce);

        BigInteger ans = timeReachedNoBoost[N];

        for (int i = 0; i < L; i++) {
            ans = ans.subtract(tReduce[N-i]);
        }

        return ans;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int L = sc.nextInt();
            long tt = sc.nextLong();
            int N = sc.nextInt();
            int C = sc.nextInt();
            long[] a = new long[C];
            for (int j = 0; j < C; j++) {
                a[j] = sc.nextLong();
            }
            long[] dist = new long[N];
            int ptr = 0;
            int cptr = 0;
            while(ptr!= N){
                dist[ptr] = a[cptr];
                ptr++;
                cptr = (cptr+1)%C;
            }

            out.println(solve(L, tt, N, dist));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }


}
