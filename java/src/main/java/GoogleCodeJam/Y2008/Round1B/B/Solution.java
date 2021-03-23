package GoogleCodeJam.Y2008.Round1B.B;

import java.io.*;
import java.util.*;

/**
 * Created by ymlai on 11/4/2017.
 * #UnionFind
 */
public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;
    static int[] primes;

    static{
        try{
            /*
            FILENAME = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1B\\B\\B-large-practice";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false)); ;

             */

            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1B\\B\\B-test.in";
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

    static class UnionFind { // OOP style
        int[] p;
        int[] rank;
        int[] size;

        UnionFind(int N) {
            p = new int[N];
            rank = new int[N];
            size = new int[N];
            //for (int i = 0; i < N; i++) p[i] = i;
            init();
        }

        void init(){
            for (int i = 0; i < p.length; i++) p[i] = i;
            Arrays.fill(rank, 0);
            Arrays.fill(size, 1);
        }
        int findSet(int i) { return (p[i] == i) ? i : (p[i] = findSet(p[i])); }

        boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

        void unionSet(int i, int j) {
            if (!isSameSet(i, j)) { // if from different set
                int x = findSet(i), y = findSet(j);
                if (rank[x] > rank[y]) {
                    p[y] = x; // rank keeps the tree short
                    size[x] += size[y];
                }
                else {
                    p[x] = y;
                    size[y] += size[x];
                    if (rank[x] == rank[y])
                        rank[y]++;
                }
            }
        }

        int getSetSize(int i){
            return size[i];
        }
        
        int countNumberOfSet(){
            int result = 0;
            for (int i = 0; i < p.length; i++) {
                if(p[i] == i) //root
                    ++result;
            }
            return result;
        }
    }

    private int solveSmall(long A, long B, long P) {

        HashMap<Long, List<Long>> factorMap = new HashMap<>();

        int asize = (int)(B-A+1);
        //find all primes using seive
        UnionFind uf = new UnionFind(asize);

        for(long i=A; i<=B; ++i) {
            factorMap.put(i, factor(i));

            System.out.print(i + ": ");
            for (int j = 0; j < factorMap.get(i).size(); j++) {

                System.out.print(factorMap.get(i).get(j) + " ");
            }
            System.out.println();
        }

        for(long i=A; i<=B; ++i){
            int taptr=-1;
            List<Long> afactorz = factorMap.get(i);
            for (int k = 0; k < afactorz.size(); k++)
                if(afactorz.get(k) >= P){
                    taptr = k;
                    break;
                }

            if (taptr==-1) continue;

            for(long j=i+1; j<=B; ++j){
                //check any prime > P

                List<Long> bfactorz = factorMap.get(j);
                int aptr= taptr;
                int bptr=-1;

                for (int k = 0; k < bfactorz.size(); k++)
                    if(bfactorz.get(k) >= P){
                        bptr = k;
                        break;
                    }

                if(aptr==-1 || bptr==-1) continue;

                while(aptr != afactorz.size() && bptr != bfactorz.size()){
                    if(afactorz.get(aptr).compareTo(bfactorz.get(bptr)) == 0) {
                        uf.unionSet((int) (i-A), (int) (j-A));
                        //System.out.format("join %d %d\n", i, j);
                        break;
                    } else if (afactorz.get(aptr) < bfactorz.get(bptr))
                        aptr++;
                    else
                        bptr++;

                }
            }
        }

        //debug for all the set

        for(int i=0; i<=(int)(B-A); ++i){
            if(uf.p[i] == i){
                System.out.println("set: " + (i+A) + " " + uf.size[i]) ;
            }
        }


        return uf.countNumberOfSet();
    }

    private int solveBig(long A, long B, long P){
        //observation here
        //find all the prime number x which is A < x < B < Px
        /* other likes
            for each A
                1. factorize all < P then it own group
                2. factorize with x => x > P, denote x' = smallest prime > P
                    => B < Px: cannot join with other
                    => if x'x < B, then it must join the large group   => this is a bad logic because x' may not be in the group when the range is small
         */

        TreeSet<Long> primeFactors = new TreeSet<>();

        int allSmallPrimes = 0;
        int ownGroupPrimes = 0;
        int largeGroupPrimes = 0;

        long primeGteP = -1;

        long pp = P;
        while(true){
            ArrayList<Long> factorz = factor(pp);
            if(factorz.size() == 1) {
                primeGteP = pp;
                break;
            }
            ++pp;
        }

        for(long i=A; i<=B; ++i){
            ArrayList<Long> factorz = factor(i);

            boolean allSmall = true;
            for (int j = 0; j < factorz.size(); j++) {
                if(factorz.get(j) > P) allSmall = false;
            }

            if(allSmall){
                allSmallPrimes += 1;
                continue; //no contribute to join group
            }

            //if all prime cannot be group to larger
            boolean own = true;
            for (int j = 0; j < factorz.size(); j++) {
                if(factorz.get(j) < P) continue;
                if(factorz.get(j) * primeGteP <= B) {
                    own = false;
                    largeGroupPrimes = 1;
                }
            }
            if(own) {
                ownGroupPrimes += 1;
                System.out.println(i);
            }

            primeFactors.addAll(factorz);
        }


        System.out.println("Big: small prime: " + allSmallPrimes + " own group: " + ownGroupPrimes + " large group: " + largeGroupPrimes);
        return allSmallPrimes + ownGroupPrimes + largeGroupPrimes;
    }

    private ArrayList<Long> factor(long A){
        ArrayList<Long> a = new ArrayList<>();
        for(int i=0; i<primes.length; ++i){
            if(A == 1) break;
            if(A % primes[i] == 0) {
                a.add((long) primes[i]);
                while (A % primes[i] == 0) {
                    A = A / primes[i];
                }
            }
        }
        if(A!=1)a.add(A);
        return a;
    }
    //test for finding all prime between 0 and 10^12
    private static int[] findPrimes(int N){
        byte[] n = new byte[N+1];
        n[0] = 1; n[1] = 1; n[2] = 0;
        int curp = 2;
        while(curp * curp <= N) {
            for (int i = curp * 2; i <= N; i+=curp)
                n[i] = 1;
            for(int i=curp+1; i<=N; ++i)
                if(n[i] == 0){
                    curp = i;
                    break;
                }
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for(int i=0; i<N; ++i){
            if (n[i] == 0) primes.add(i);
        }

        return primes.stream().mapToInt((Integer i) -> i.intValue()).toArray();
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            long A = sc.nextLong();
            long B = sc.nextLong();
            long P = sc.nextLong();
            int r;
            //r = solveSmall(A,B,P);
            r = solveBig(A,B,P);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        primes = findPrimes(1000000);
        new Solution().run();
    }
}
