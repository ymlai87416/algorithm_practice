package GoogleCodeJam.Y2019.Quali.C;

import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
                    */

            //IN = "A-test.in";
            IN = null;
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

    static String lookup = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private void solve(BigInteger p, int r, BigInteger[] crypto){
        TreeMap<BigInteger, Character> dict = new TreeMap<BigInteger, Character>();
        TreeSet<BigInteger> factors = new TreeSet<BigInteger>();

        int ii;
        BigInteger solve_a, solve_b, k1, k2, seed0;
        solve_a = BigInteger.ZERO; solve_b = BigInteger.ONE;
        k2 = BigInteger.ZERO;
        for(ii=0; ii<r-1; ++ii) {
            k1 = crypto[ii].gcd(crypto[ii+1]);
            if(k1.isProbablePrime(1)) {
                k2 = crypto[ii].divide(k1);
                factors.add(k1);
                factors.add(k2);
                solve_a = k2;
                solve_b = k1;
                break;
            }
        }

        k1 = solve_a;
        for(int i=ii-1; i>=0; --i){
            k2 = crypto[i].divide(k1);
            factors.add(k2);
            k1 = k2;
        }

        seed0 = k2;

        k1 = solve_b;
        for(int i=ii+1; i<crypto.length; ++i){
            k2 = crypto[i].divide(k1);
            factors.add(k2);
            k1 = k2;
        }

        int ptr = 0;
        for(BigInteger a : factors) {
            char c = lookup.charAt(ptr++);
            dict.put(a, c);
        }

        /*
        out.println("debug");
        for(BigInteger a : dict.keySet())
            out.print(a + " " + dict.get(a));
        */

        String result = "";
        result += dict.get(seed0);
        k1 = seed0;
        for(int i=0; i<r; ++i){
            BigInteger t = crypto[i].divide(k1);
            result += dict.get(t);
            k1 = t;
        }

        out.println(result);
    }

    private void run() throws Exception {
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            BigInteger p = sc.nextBigInteger();
            int r = sc.nextInt();
            BigInteger[] crypto = new BigInteger[r];
            for(int j=0; j<r; ++j)
                crypto[j] = sc.nextBigInteger();

            solve(p, r, crypto);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
