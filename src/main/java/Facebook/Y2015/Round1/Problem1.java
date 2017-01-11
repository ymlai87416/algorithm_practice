package Facebook.Y2015.Round1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//start at 0:59, accept at 1:12
//you are missing the word "ORDER LIST" which you cannot change the given order.
public class Problem1 {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "C:\\Users\\Tom\\Documents\\algorithm_practice\\homework (1)";
            IN = FILENAME + ".txt";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    class SieveOfEratosthenes {

        final int N;
        int[] sieve;
        List<Integer> result;

        public SieveOfEratosthenes(int size) {
            N = size;
            sieve = new int[(N >> 6) + 1];
        }

        boolean GET(int x) {
            return (sieve[x >> 5] >> (x & 31) & 1) == 1;
        }

        void SET(int x) {
            sieve[x >> 5] |= 1 << (x & 31);
        }

        int N2I(int i) {
            return (i - 1) >> 1;
        }

        int I2N(int i) {
            return (i << 1) + 1;
        }

        // 驗證一個數字是不是質數
        boolean isprime(int x) {
            return x == 2 || x > 2 && ((x & 1) == 1) && !GET(N2I(x));
        }

        //best
        void genPrime() {
            int half_sqrt_N = (int) Math.sqrt(N) / 2;
            int half_N = N >> 1;
            int i, j, k;
            for (i = 1; i <= half_sqrt_N; i++)
                if (!GET(i))
                    for (j = 2 * i * (i + 1), k = 2 * i + 1; j < half_N; j += k)
                        SET(j);

            ArrayList<Integer> primes = new ArrayList<Integer>();
            for (i = 2; i <= N; i += 1)
                if (isprime(i))
                    primes.add(i);

            result = primes;
        }
    }

    private void preCompute(){
        SieveOfEratosthenes sieve = new SieveOfEratosthenes(10000000);
        sieve.genPrime();

        for(int i=0; i<=10000000; ++i)
            p[i] = 0;

        for(int u : sieve.result){
            for(int i=u; i<=10000000; i+=u){
                p[i]++;
            }
        }
    }

    int[] p = new int[10000001];

    private void run() throws Exception {
        preCompute();

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            System.out.print("Case #" + i + ": ");
            out.print("Case #" + i + ": ");
            int a = sc.nextInt();
            int b = sc.nextInt();
            int k = sc.nextInt();

            int result = 0;
            for(int j=a; j<=b; ++j){
                if(p[j] == k)
                    result++;
            }

            System.out.println(result);
            out.println(result);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Problem1().run();
    }
}
