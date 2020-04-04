package Mathematics.BigIntegerPrimalityTesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Tom on 13/4/2016.
 */
public class UVA11287 {

    static class SieveOfEratosthenes {

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
        void eratosthenes1() {
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

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        SieveOfEratosthenes s  = new SieveOfEratosthenes((int)Math.sqrt(1000000000));
        s.eratosthenes1();

        List<Integer> primeslist = s.result;

        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(a == 0 && b == 0) break;

            if(!checkprime(a, primeslist) && pow(b, a, a) == b)
                System.out.println("yes");
            else
                System.out.println("no");
        }

    }

    public static boolean checkprime(int a, List<Integer> primes){
        for(int i=0; i<primes.size(); ++i){
            int p = primes.get(i);
            if(p * p > a) break;
            if(a % p == 0) return false;
        }
        return true;
    }

    public static int pow(int base, int pow, int m){
        if(pow  == 1) return base;
        if(pow == 0) return 1;
        int a = pow(base, pow/2, m);
        if(pow %2 != 0) {
            long temp = ((( ((long)a) * a) % m) * base) % m ;
            return (int)temp;
        }
        else {
            long temp = (((long)a) * a) % m;
            return  (int)temp;
        }
    }
}
