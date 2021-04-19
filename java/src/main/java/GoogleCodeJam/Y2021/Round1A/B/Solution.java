package GoogleCodeJam.Y2021.Round1A.B;

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
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2021\\Round1A\\B\\B-test.in";
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

    boolean debugflag = true ;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private long solveSmall(int M, int[] p, int[] np){
        //if they add up and can be divided by only prime, record it down
        long ans = 0;
        int totalNp = 1;
        for (int i = 0; i < np.length; i++) {
            totalNp *= (np[i]+1);
        }

        int[] cnt= new int[M];
        for (int i = 0; i < M; i++) {
            cnt[i] = 0;
        }
        cnt[0] = 0;
        for (int i = 0; i < totalNp-1; i++) {
            //just like addition
            cnt[0] += 1;
            int advP = 0;
            while(cnt[advP] > np[advP]){
                cnt[advP] = 0;
                advP++;

                cnt[advP] += 1;
            }

            /*
            String dd = "";
            for (int j = 0; j < M; j++) {
                dd = dd + " " + cnt[j];
            }*/
            //debug(dd);
            
            long sum = 0;
            long multi = 1;
            //now sum all the shit
            for (int j = 0; j < M; j++) {
                sum += p[j] * cnt[j];
            }
            //multiple all other
            for (int j = 0; j < M && multi <= sum; j++) {
                for (int k = 0; k < np[j]-cnt[j]; k++) {
                    multi = multi * p[j];
                    if(multi > sum)
                        break;
                }
            }
            if(sum == multi)
                debug(multi + " " + sum);
            if(multi == sum && sum > ans)
                ans = sum;
        }

        return ans;
    }


    //95 primes here
    boolean[] dp = new boolean[49901];
    boolean[] dp2 = new boolean[49901];
    private long solve(int M, int[] p, int[] np) {

        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = false;
        }
        
        for (int i = 0; i < M ; i++) {

            for (int j = 0; j < dp2.length; j++) {
                dp2[j] = false;
            }

            for (int j = 1; j <= np[i]; j++) {
                for (int k = 0; k < dp.length; k++) {
                    if(dp[k]){
                        dp2[k]=true;

                        dp2[k+p[i]*j] = true;
                        //debug("Set true" + k+p[i]*j + " at " + p[i]  + " " + j);

                    }
                }
            }

            boolean[] tdp = dp;
            dp = dp2;
            dp2= tdp;
        }

        //now we have the table just factorize one by one to see the shit
        int[] pp = new int[M];
        long ans = 0;
        for (int i = 1; i < dp.length; i++) {
            if(dp[i]){
                if(tryFact(i, p, pp)){
                    int sum = 0;
                    boolean ok =true;
                    for (int j = 0; j < M; j++) {
                        if(pp[j] > np[j]) ok = false;
                        sum += p[j] * (np[j] - pp[j]);
                    }

                    if(ok && sum == i && sum > ans)
                        ans = sum;
                }
            }
        }


        return ans;
    }

    private boolean tryFact(int k, int[] np, int[] p){
        for (int i = 0; i < p.length; i++) {
            p[i] = 0;
        }
        for (int i = 0; i < np.length; i++) {
            while(k % np[i] == 0) {
                p[i]++;
                k/=np[i];
            };
        }
        return k == 1;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int M = sc.nextInt();
            int[] p = new int[M];
            int[] np = new int[M];
            for (int j = 0; j < M; j++) {
                p[j] = sc.nextInt();
                np[j] = sc.nextInt();
            }
            out.print("Case #" + i + ": ");
            System.out.println(solve(M, p, np));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}