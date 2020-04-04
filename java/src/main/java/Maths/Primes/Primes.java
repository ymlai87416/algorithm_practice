package Maths.Primes;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tom on 12/4/2016.
 */
public class Primes {
    class SieveOfEratosthenes{

        final int N;
        int[] sieve;
        List<Integer> result;

        public SieveOfEratosthenes(int size){
            N = size;
            sieve = new int[(N>>6) + 1];
        }

        boolean  GET(int x) { return (sieve[x>>5]>>(x&31) & 1) == 1; }
        void SET(int x) { sieve[x>>5] |= 1<<(x&31); }
        int  N2I(int i) { return (i-1)>>1; }
        int  I2N(int i) { return (i<<1)+1; }

        // 驗證一個數字是不是質數
        boolean isprime(int x)
        {
            return x==2 || x>2 && ((x&1) == 1) && !GET(N2I(x));
        }

        //best
        void eratosthenes1()
        {
            int half_sqrt_N = (int)Math.sqrt(N) / 2;
            int half_N = N >> 1;
            int i, j, k;
            for (i=1; i<=half_sqrt_N; i++)
                if (!GET(i))
                    for (j=2*i*(i+1), k=2*i+1; j<half_N; j+=k)
                        SET(j);

            ArrayList<Integer> primes = new ArrayList<Integer>();
            for(i=2; i<=N; i+=1)
                if(isprime(i))
                    primes.add(i);

            result = primes;
        }

        //double time almost
        void eratosthenes2() {
            int half_sqrt_N = (int)Math.sqrt(N) / 2;
            int i, j, k, ii;
            for (i = 1; i <= half_sqrt_N; i++)
                if (!GET(i))
                    for (ii = I2N(i), j = N2I(N / ii), k = ii * j + i; j >= i; j--, k -= ii)
                        if (!GET(j))
                            SET(k);

            ArrayList<Integer> primes = new ArrayList<Integer>();
            for(i=2; i<=N; i+=1)
                if(isprime(i))
                    primes.add(i);

            result = primes;
        }

        //also double time
        void eratosthenes3()
        {
            char[] sieve = new char[N];

            int sqrtN = (int)Math.sqrt(N);
            sieve[0] = sieve[1] = 1;
            for (int i=2; i<=sqrtN; i++)
                if (sieve[i] == 0)
                    for (int k=(N-1)/i, j=i*k; k>=i; k--, j-=i)
                        if (sieve[k]==0)
                            sieve[j] = 1;

            ArrayList<Integer> primes = new ArrayList<Integer>();
            for(int i=2; i<N; i+=1)
                if(sieve[i]==0)
                    primes.add(i);

            result = primes;
        }

        //10 times slower...
        void poorImplementation(){
            ArrayList<Integer> p = new ArrayList<Integer>();
            p.add(2); p.add(3); p.add(5);
            int limit = N;
            for (int i = 7; i < limit; i += 2) {
                boolean prime = true;
                for (int j = 0; ; ++j) {
                    long divisor = p.get(j);
                    if(divisor*divisor > i) break;
                    if (i % divisor == 0)
                        prime = false;
                }
                if (prime) p.add(i);
            }
            result = p;
        }
    }

    public void run(){
        long start, end;
        double interval;

/*        start = (new Date()).getTime();
        SieveOfEratosthenes sieve2 = new SieveOfEratosthenes(15219314);
        sieve2.eratosthenes2();
        end = (new Date()).getTime();
        interval = (end-start) / 1000.0;
        System.out.println("eratosthenes-2 run at " + interval);*/

        start = (new Date()).getTime();
        SieveOfEratosthenes sieve1 = new SieveOfEratosthenes((1<<31)-1);
        sieve1.eratosthenes1();
        end = (new Date()).getTime();
        interval = (end-start) / 1000.0;
        System.out.println("eratosthenes-1 run at " + interval);

        StringBuilder sb200k = new StringBuilder();
        StringBuilder sb64k = new StringBuilder();
        sb200k.append("int[] p={");
        for(int i=0; i<sieve1.result.size(); ++i){
            sb200k.append(sieve1.result.get(i));
            if(sb200k.length() > 200*1000) break;
            sb200k.append(",");
        }
        for(int i=0; i<sieve1.result.size(); ++i){
            sb64k.append(sieve1.result.get(i));
            if(sb64k.length() > 64*1000) break;
        }
        sb200k.append("};");
        sb64k.append("};");

        File f64k = new File("primeArray64k.txt");
        File f200k = new File("primeArray200k.txt");

        PrintStream out = null;
        try {
            out = new PrintStream(new FileOutputStream(f64k));
            PrintStream out2 = new PrintStream(new FileOutputStream(f200k));

            out.println(sb64k.toString());
            out2.println(sb200k.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

/*        start = (new Date()).getTime();
        SieveOfEratosthenes sieve3 = new SieveOfEratosthenes(15219314);
        sieve3.poorImplementation();
        end = (new Date()).getTime();
        interval = (end-start) / 1000.0;
        System.out.println("poor implementation run at " + interval);

        start = (new Date()).getTime();
        SieveOfEratosthenes sieve4 = new SieveOfEratosthenes(15219314);
        sieve4.eratosthenes3();
        end = (new Date()).getTime();
        interval = (end-start) / 1000.0;
        System.out.println("boolean sieve run at  " + interval);

        System.out.println("Goodbye");*/
    }

    public static void main(String[] args){
        Primes prime = new Primes();
        prime.run();
    }
}
