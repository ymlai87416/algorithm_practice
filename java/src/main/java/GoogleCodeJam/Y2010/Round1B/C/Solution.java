package GoogleCodeJam.Y2010.Round1B.C;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2010\\Round1B\\C\\C-test.in";
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

    static boolean debugflag = false;
    private static void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private int solve(int N) {
        int ans = 0;

        if(N==2) return 1;
        if(N==3) return 2;

        for(int i=1; i<N; ++i){
            ans = (ans + dpHelper(N, i)) % 100003;
        }

        StringBuilder sb;
        for (int i = 1; i <= N; i++) {
            sb = new StringBuilder();
            for (int j = 1; j <= N; j++) {
                sb.append(dp[i][j] + " ");
            }
            debug(sb.toString());
        }


        return ans;
    }

    int[][] dp = new int[501][501];

    private int dpHelper(int N, int size){
        //debug("F(" + N + ", " + size + ")");

        if(N==1){
            dp[N][size] = 0;
            return 0;
        }
        if(size==1){
            dp[N][size]=1;
            return 1;
        }
        if(size <= 0) return 0;
        if(dp[N][size] != -1)
            return dp[N][size];
        //rank(N) = size
        
        long ans = 0;
        for (int i = 0; i < N-size; i++) {
            //nCr e.g. if 3 choose pos 1, origin size = 3, new size=1,  3-1-1,  how many number? between 3 and 6 -> 6-3-1
            int newSize = size-i-1;
            int fillPos = size-newSize-1;
            int fillNum = N-size-1;
            long possible = 1;
            //possible = nCr(fillNum, fillPos, 100003);
            possible = nCrModPFermat(fillNum, fillPos, 100003);

            ans = (ans + dpHelper(size, newSize) * possible) % 100003;
        }


        dp[N][size] = (int)ans;
        return dp[N][size];
    }

    private static int nCr(int n, int r, int mod) {
        BigInteger top = BigInteger.ONE;
        BigInteger bot = BigInteger.ONE;
        BigInteger bot2 = BigInteger.ONE;

        for(int i = 2; i<=n; ++i){
            top = top.multiply(BigInteger.valueOf(i));
        }

        for(int i = 2; i<=r; ++i){
            bot = bot.multiply(BigInteger.valueOf(i));
        }

        for(int i = 2; i<=(n-r); ++i){
            bot2 = bot2.multiply(BigInteger.valueOf(i));
        }

        BigInteger rr=  top.divide(bot).divide(bot2);
        return rr.mod(BigInteger.valueOf(mod)).intValue();
    }

    //Note: overflow happens and becareful
    static int power(int x, int y, int p)
    {

        // Initialize result
        long res = 1;
        long lx, ly;
        lx = x; ly = y;

        // Update x if it is more than or
        // equal to p
        lx = lx % p;

        while (ly > 0) {

            // If y is odd, multiply x
            // with result
            if (ly % 2 == 1)
                res = (res * lx) % p;

            // y must be even now
            ly = ly >> 1; // y = y/2
            lx = (lx * lx) % p;
        }

        return (int)res;
    }

    static int modInverse(int n, int p)
    {
        int a = power(n, p - 2, p);
        if(a < 0)
            debug("shit" + n + " " + p);
        return a;
    }

    // Returns nCr % p using Fermat's
    // little theorem.
    static int nCrModPFermat(int n, int r,
                             int p)
    {

        if (n<r)
            return 0;
        // Base case
        if (r == 0)
            return 1;

        // Fill factorial array so that we
        // can find all factorial of r, n
        // and n-r
        int[] fac = new int[n + 1];
        fac[0] = 1;

        for (int i = 1; i <= n; i++)
            fac[i] = fac[i - 1] * i % p;

        long temp =  (1L *fac[n] * modInverse(fac[r], p)
                % p * modInverse(fac[n - r], p)
                % p)
                % p;

        return (int)temp;
    }



    public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

    private void solveSmall(int N){
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 2; i <=N ; i++) {
            hs.add(i);
        }
        Set<Set<Integer>> sets = powerSet(hs);
        for(Set<Integer> s : sets){
            //valid set
            Integer[] bb = s.toArray(new Integer[0]);
            Arrays.sort(bb);
            boolean validSet = true;
            Integer sn = N;
            do{
                int pos = Arrays.binarySearch(bb, sn);
                if(pos < 0){
                    validSet = false;
                    break;
                }
                pos+=1;
                sn = pos;
            }while(sn != 1);

            if(validSet){
                for (int i = 0; i < bb.length; i++) {
                    System.out.print(bb[i] + " ");
                }
                System.out.println("");
            }
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));

        if(false){
            solveSmall(6);
            return;
        }

        //dp set here
        for (int i = 0; i < 501 ; i++) {  //i = N
            for (int j = 0; j < 501; j++) { //j = pos
                if(j == 1) dp[i][j] = 1;
                else dp[i][j] = -1;
            }
        }


        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            System.out.println(solve(N));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
