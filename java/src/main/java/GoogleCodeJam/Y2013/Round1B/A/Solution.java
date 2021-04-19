package GoogleCodeJam.Y2013.Round1B.A; /**
 * Created by Tom on 9/4/2016.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2013\\Round1B\\A\\A-test.in";
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

    int N;
    int[] m;

    private void solve(long A, int N, int[] m) {
        int ans = 0;

        this.N = N;
        this.m= m;
        Arrays.sort(m);
        dp =new TreeMap<>();
        ans = solveHelper(A, 0);

        out.println(ans);
    }

    static class Pair implements Comparable<Pair>{
        long a;
        int b;
        public Pair(long a, int b){
            this.a=a;
            this.b=b;;
        }

        @Override
        public int compareTo(Pair o) {
            if(a ==o.a)
                return ((Integer)b).compareTo(o.b);
            else
                return ((Long)a).compareTo(o.a);
        }
    }

    TreeMap<Pair, Integer> dp;

    private int solveHelper(long A, int idx){
        //System.out.format("idx: %d, A: %d\n", idx, A);
        Pair p = new Pair(A, idx);

        if(dp.containsKey(p))
            return dp.get(p);

        if(idx == N)
            return 0;
        if(A > m[idx]) {
            int ans = solveHelper(A + m[idx], idx + 1);
            dp.put(p, ans);
            return ans;
        }
        else{
            //double its size
            int a1 = Integer.MAX_VALUE;
            if(A != 1)
                a1 = solveHelper(A+A-1, idx)+1;
            int a2 = solveHelper(A, idx+1)+1;

            int ans = Math.min(a1, a2);
            dp.put(p, ans);
            return ans;
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            long A =sc.nextLong();
            int N = sc.nextInt();
            int[] m = new int[N];
            for(int j=0; j<N; ++j){
                m[j] = sc.nextInt();
            }
            solve(A, N, m);
        }
        sc.close();
        out.close();


        /*long r =10000000000000000L, rr=r;
        long result = 0;
        for(int i=0; i<50; ++i){
            result += (r+1)*(r+1) - r*r;
            System.out.format("D: %d\n", result);
            System.out.format("D2: %d\n", paint(BigInteger.valueOf(i+1), BigInteger.valueOf(rr)));
            r+=2;
        }*/
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}