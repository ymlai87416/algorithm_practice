package Mathematics.ModifiedSieve;

import java.io.*;
import java.util.*;
import java.math.*;

public class UVA10990 {
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            //IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1C\\A\\A-test.in";
            //OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\consistency_chapter_2_output.txt";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            if(OUT == null)
                out = new PrintStream(System.out);
            else out = new PrintStream(OUT);
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

    private long solve(int m, int n) {
        long ans = 0;

        for(int i=m; i<=n; ++i) {
            int rr = memo[i];
            //long a = EulerPhi(i);
            //System.out.println(i + ": " + rr + " " + a);
            ans += rr;
        }

        return ans;
    }

    int[] memo = new int[2000001];
    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        sieve(2000000);
        memo[2] =1;
        memo[1] =0;
        for(int i=3; i<=2000000; ++i) {
            long a = EulerPhi(i);
            memo[i] = memo[(int)a] + 1;
        }

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            long r = solve(m, n);
            out.println(r);
        }
        sc.close();
        out.close();
    }


    long _sieve_size; // ll is defined as: typedef long long ll;
    BitSet bs = new BitSet(10_000_010); // 10^7 should be enough for most cases
    ArrayList<Integer> primes; // compact list of primes in form of vector<int>

    long EulerPhi(long N) {
        int PF_idx = 0;
        long PF = primes.get(PF_idx), ans = N; // start from ans = N
        while (PF * PF <= N) {
            if (N % PF == 0) ans -= ans / PF; // only count unique factor
            while (N % PF == 0) N /= PF;
            PF = primes.get(++PF_idx);
        }
        if (N != 1) ans -= ans / N; // last factor
        return ans;
    }

    void sieve(int upperbound) { // create list of primes in [0..upperbound]
        primes = new ArrayList<>();
        _sieve_size = upperbound + 1; // add 1 to include upperbound
        bs.set(0, (int)_sieve_size); // set all bits to 1
        bs.clear(0);
        bs.clear(1); // except index 0 and 1
        for (long i = 2; i <= _sieve_size; i++)
            if (bs.get((int) i)) {
                // cross out multiples of i starting from i * i!
                for (long j = i * i; j <= _sieve_size; j += i)
                    bs.clear((int)j);
                primes.add((int) i); // add this prime to the list of primes
            }
    } // call this method in main method

    public static void main(String args[]) throws Exception {
        new UVA10990().run();
    }

}