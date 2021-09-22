package Mathematics.BigIntegerPrimalityTesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Tom on 13/4/2016.
 *
 * Read at 18:00 and AC at 18:39, total time consume  39 minutes, very poor as you cannot watch the question for consecutive....
 *
 * problem: https://onlinejudge.org/external/12/1210.pdf
 * #UVA #big_integer #primality_testing #Lv3
 */
public class UVA1210 {
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

        SieveOfEratosthenes sieve = new SieveOfEratosthenes(10000);
        sieve.eratosthenes1();

        int[] sum = new int[sieve.result.size()+1];
        sum[1] = 2;
        for(int i=0; i<sieve.result.size(); ++i)
            sum[i+1] = sum[i] + sieve.result.get(i);

        while(true){
            int a = sc.nextInt();
            if(a == 0) break;
            int cnt = 0;

            for(int i=1; i<sum.length; ++i){
                for(int j=0; j<i; ++j){
                    int result = sum[i] - sum[j];
                    if(result == a) cnt++;
                }
            }

            System.out.println(cnt);
        }

    }
}
