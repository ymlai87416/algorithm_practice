package GoogleCodeJam.Y2015.Round1B.A;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2015\\Round1B\\A\\A-test.in";
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

    int[] dp = new int[1000001];

    private long reverse(long a){
        long c = 0;

        while(a != 0){
            c = c * 10 + (a% 10);
            a = a /10;
        }

        return c;
    }


    private int solveSmall(int N){
        //dp = shortest to count to 1...N
        //valid reverse is such that both are of same length
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            int rr = (int)reverse(i);
            boolean sameLen = (i%10 != 0);
            if(rr >= i || !sameLen)
                dp[i] = dp[i-1] + 1;
            else
                dp[i] = Math.min(dp[i-1] + 1, dp[rr]+1);
        }
        
        return dp[N];
    }

    private long solveHelper(long b, long N){

        String sN = String.valueOf(N);
        String sb = String.valueOf(b);
        if(sN.length() != sb.length()) return -1;

        int ans = 0;
        //count to N
        long ans1 = N-b;
        //reverse and then count to N
        long ans2, ans3 ;
        if(N %10 == 0)
            ans3 = solveHelper(b, N-1) + 1;
        else
            ans3 = Long.MAX_VALUE;

        if(sN.length() % 2 == 0) {
            String rr =  sN.substring(0, sN.length()/2);
            while(rr.length() < sN.length())
                rr += "0";
            long irr = Long.valueOf(rr)+1;
            long rirr = reverse(irr);

            if(N < irr) ans2 = Long.MAX_VALUE;
            else ans2 =  (rirr-b ) + 1 + (N-irr);
        }
        else{
            String rr; long irr, rirr;

            rr =  sN.substring(0, sN.length()/2);
            while(rr.length() < sN.length())
                rr += "0";
            irr = Long.valueOf(rr)+1;
            rirr = reverse(irr);

            //count to rirr
            if(N < irr) ans2 = Long.MAX_VALUE;
            else ans2 =  (rirr-b ) + 1 + (N-irr);

            //second
            rr =  sN.substring(0, sN.length()/2);
            while(rr.length() < sN.length())
                rr += "0";
            irr = Long.valueOf(rr)+1;
            rirr = reverse(irr);
            long t;

            if(N < irr) t = Long.MAX_VALUE;
            else t =  (rirr-b ) + 1 + (N-irr);

            ans2 = Math.min(ans2, t);
        }
        return Math.min(ans1, Math.min(ans2, ans3));
    }

    private long solve(long N) {
        //strategy count to ABCDEF,
        /*
            first count to 100000
            1st:  to 100CBA -> CBA001 -> ABCDEF
            2nd: 1000000 -> ABCDEF
         */

        String sN = String.valueOf(N);

        long fans = 0;
        for (int i = 1; i <= sN.length()-1; i++) {
            String nines = "";
            for (int j = 0; j < i; j++) {
                nines = nines + "9";
            }
            String oneZero = "1";
            for (int j = 0; j < i-1; j++) {
                oneZero = oneZero + "0";
            }
            
            //count to 1xx
            long st = solveHelper(Long.valueOf(oneZero), Long.valueOf(nines));

            debug(oneZero + " to " + nines + ": " + st);
            fans = fans + st + 1;
        }

        String oneZero = "1";
        for (int j = 0; j < sN.length()-1; j++) {
            oneZero = oneZero + "0";
        }

        long st = solveHelper(Long.parseLong(oneZero), N);
        debug( oneZero + " to " + N + ": " + st);
        fans = fans + st;
        fans +=1;
        return fans;
    }

    private String backTrack(int n, String a){
        if (n==1) return a  + " 1";

        int rr = (int)reverse(n);
        boolean sameLen = (n%10 != 0);

        if(rr >= n || !sameLen)
            a = backTrack(n-1, a + " " + n);
        else{
            if(dp[n] == dp[n-1] + 1)
                a = backTrack(n-1, a + " " + n);
            else
                a = backTrack(rr, a + " " + n);
        }

        return a;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));

        if(true){
            solveSmall(100000);

            for (int i = 1; i < 100000; i++) {
                long t = solve(i);
                if(dp[i]!=t)
                    out.format("%d %d %d\n", i, dp[i], t);
            }

            //System.out.println(backTrack(61237, ""));

            return;
        }

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N= sc.nextInt();
            out.println(solve(N));
            //out.println(solveSmall(N));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
