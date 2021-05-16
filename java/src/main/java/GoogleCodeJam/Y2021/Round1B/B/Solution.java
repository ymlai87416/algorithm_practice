package GoogleCodeJam.Y2021.Round1B.B;

import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2021\\Round1B\\B\\B-test.in";
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

    int limit= 1000;

    private int gcd(int A, int B){
        if(A%B==0) return B;
        return gcd(B, A%B);
    }

    private int solve(int N, int A, int B, int[] U) {

        //both odd and only U odd have number
        int gg =  gcd(B,A);

        int r = N%gg;
        for (int i = 1; i <=N ; i++) {
            if(i % gg != r && U[i] != 0) return -1;
        }

        //just go
        int min = 0;
        int max = limit / gg;
        while (min < max) {
            debug(min + " " + max);
            int mid = (min + max) / 2;
            int tmid = mid*gg+r;

            if (possible(tmid, U, A, B))
                max = mid;
            else
                min = mid + 1;
        }

        if (max == limit/gg) return -1;

        return max*gg+r;
    }

    BigInteger[] t= new BigInteger[limit+20];
    private boolean possible(int N, int[] U, int A, int B){

        if(N < U.length)
            return false;

        for (int i = 0; i < t.length; i++) {
            t[i] = BigInteger.ZERO;
        }
        t[N] = BigInteger.ONE;
        for (int i = N; i>=0; --i) {

            if(i< U.length){
                if(t[i].compareTo(BigInteger.valueOf(U[i])) < 0)
                    return false;
                BigInteger r = t[i].subtract(BigInteger.valueOf(U[i]));
                if(i-A > 0) t[i-A]=t[i-A].add(r);
                if(i-B > 0) t[i-B] =t[i-B].add(r);
                t[i] = BigInteger.valueOf(U[i]);
            }
            else{
                //else break all

                if(i-A > 0)t[i-A]=t[i-A].add(t[i]);
                if(i-B > 0)t[i-B]=t[i-B].add(t[i]);
                t[i] = BigInteger.ZERO;
            }
        }
        return true;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            int A = sc.nextInt();
            int B = sc.nextInt();
            int[] U = new int[N+1];
            for (int j = 0; j < N; j++) {
                U[j+1] = sc.nextInt();
            }
            int r = solve(N, A, B, U);
            if(r==-1)
                out.println("IMPOSSIBLE");
            else
                out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
