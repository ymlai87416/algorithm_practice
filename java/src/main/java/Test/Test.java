package Test;
import java.util.*;

public class Test {

    public static void main(String[] args){
        new Test().run();
    }

    public void run(){
        sieve(10_000_000);

        /*
        for(int i=0; i<100; ++i)
            System.out.println(i  + ": " + isPrime(i));
        ArrayList<Long> r = primeFactors(2147483647); // slowest, 2147483647 is a prime
        for (Long i : r) System.out.format("> %d\n", i);
        r = primeFactors(136117223861L); // slow, 104729*1299709
        for (Long i : r) System.out.format("> %d\n", i);
        r = primeFactors(142391208960L); // faster, 2^10*3^4*5*7^4*11*13
        for (Long i : r) System.out.format("> %d\n", i);

        System.out.println(numPF(142391208960L));
        */

        System.out.println(sumDiv(60));
        System.out.println(EulerPhi(24));

    }
    long _sieve_size; // ll is defined as: typedef long long ll;
    BitSet bs = new BitSet(10_000_010); // 10^7 should be enough for most cases
    ArrayList<Integer> primes; // compact list of primes in form of vector<int>
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

    boolean isPrime(long N) { // a good enough deterministic prime tester
        if (N <= _sieve_size) return bs.get((int)N); // O(1) for small primes
        for (int i = 0; i < primes.size(); i++)
            if (N % primes.get(i) == 0) return false;
        return true; // it takes longer time if N is a large prime!
    }

    ArrayList<Long> primeFactors(long N) { // remember: vi is vector<int>, ll is long long
        ArrayList<Long> factors = new ArrayList<>();
        int PF_idx = 0;
        long PF = primes.get(PF_idx); // primes has been populated by sieve
        while (PF * PF <= N) { // stop at sqrt(N); N can get smaller
            while (N % PF == 0) { N /= PF; factors.add((long)PF); } // remove PF
            PF = primes.get(++PF_idx); // only consider primes!
        }
        if (N != 1) factors.add(N); // special case if N is a prime
        return factors; // if N does not fit in 32-bit integer and is a prime
    } // then ‘factors’ will have to be changed to vector<ll>
    // inside int main(), assuming sieve(1000000) has been called before

    long numPF(long N) {
        int PF_idx = 0;
        long PF = primes.get(PF_idx), ans = 0;
        while (PF * PF <= N) {
            while (N % PF == 0) { N /= PF; ans++; }
            PF = primes.get(++PF_idx);
        }
        if (N != 1) ans++;
        return ans;
    }


    long numDiv(long N) {
        int PF_idx = 0;
        long PF = primes.get(PF_idx), ans = 1; // start from ans = 1
        while (PF * PF <= N) {
            long power = 0; // count the power
            while (N % PF == 0) { N /= PF; power++; }
            ans *= (power + 1); // according to the formula
            PF = primes.get(++PF_idx);
        }
        if (N != 1) ans *= 2; // (last factor has pow = 1, we add 1 to it)
        return ans;
    }

    long sumDiv(long N) {
        int PF_idx = 0;
        long PF = primes.get(PF_idx), ans = 1; // start from ans = 1
        while (PF * PF <= N) {
            long power = 0;
            while (N % PF == 0) { N /= PF; power++; }
            ans *= ((long)Math.pow((double)PF, power + 1.0) - 1) / (PF - 1);
            PF = primes.get(++PF_idx);
        }
        if (N != 1) ans *= ((long)Math.pow((double)N, 2.0) - 1) / (N - 1); // last
        return ans;
    }

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

}
