package Facebook.Y2016;

import Geometry.PointsAndLines.UVA920;

import java.io.*;
import java.util.*;

//start at 23:53, 0:12 end, 2 WA because of casting result as int instead of long...
public class Problem3 {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "C:\\Users\\Tom\\Documents\\algorithm_practice\\the_price_is_correct";
            IN = FILENAME + ".txt";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, true));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    static class Pair implements Comparable<Pair>{
        int first, second;
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            if(first < o.first) return 1;
            else if(first > o.first) return -1;
            else return second - o.second;
        }
    }


    private void solve(int c, int k, long limit, long[] sums) {
        long result = 0;
        for(int i=0; i<k; ++i){
            for(int j=i+1; j<=k; ++j){
                if(sums[j] - sums[i] <= limit)
                    result++;
            }
        }

        String output = String.format("Case #%d: %d", c, result);
        System.out.println(output);
        out.println(output);
    }


    private long distance(Pair x, Pair y){
        return (x.first - y.first) *  (x.first - y.first) + (x.second - y.second) * (x.second - y.second);
    }

    int[] input = new int[100000];
    long[] sum = new long[100001];

    private void run() throws Exception {

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ":");
            int k = sc.nextInt();
            int l = sc.nextInt();

            for(int j=0; j<k; ++j){
                input[j] = sc.nextInt();
            }

            sum[0] = 0;
            for(int j=0; j<k; ++j){
                sum[j+1] = sum[j] + input[j];
            }

            solve(i, k, l,  sum);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Problem3().run();
    }
}
