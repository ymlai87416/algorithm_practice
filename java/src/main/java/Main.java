import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Tom on 12/4/2016.
 */
class Main {
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
    }

    public List<Integer> calcPrime(int size){
        SieveOfEratosthenes obj= new SieveOfEratosthenes(size);
        obj.eratosthenes1();
        return obj.result;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);

        List<Integer> primes = calcPrime(46341);

        while(true){
            int num = scanner.nextInt();
            if(num == 0) break;
            ArrayList<Integer> result = new ArrayList<Integer>();
            int numx = num;
            if(num < 0) { result.add(-1); num = -num;}
            if(num == 1) result.add(1);

            while(true){
                if(num == 1) break;
                boolean isprime = true;
                for(Integer p : primes){
                    if(num % p == 0){
                        num  = num / p;
                        result.add(p);
                        isprime = false;
                        break;
                    }
                }
                if(isprime) {result.add(num); num=1;}
            }

            System.out.format("%d = ", numx);
            for(int i=0; i<result.size(); ++i){
                if(i == 0)
                    System.out.print(result.get(i));
                else
                    System.out.print(" x " + result.get(i));
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        Main obj = new Main();
        obj.run();
    }
}
