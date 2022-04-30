package GoogleCodeJam.Y2022.Round1C.B;

/**
 * Created by Tom on 9/4/2016.
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class Solution {
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/GoogleCodeJam/Y2022/Round1C/B/B-test.in";
            //OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\consistency_chapter_2_output.txt";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            if(OUT == null)
                out = new PrintStream(System.out);
            else out = new PrintStream(OUT);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private void debug(Object... s){
        if(debugflag) {
            //System.out.println(s);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                sb.append(s[i].toString() + " ");
            }
            System.out.println("\033[0;34m" + sb.toString() + "\033[0;30m");
        }
    }

    private ArrayList<Long> solve(int N, int K, int[] aa) {
        ArrayList<Long> ans= new ArrayList<>();

        if(K ==1){
            BigInteger sum = BigInteger.ZERO;
            BigInteger i2_sum = BigInteger.ZERO;
            for(int i=0; i<N; ++i){
                BigInteger v = BigInteger.valueOf(aa[i]);
                i2_sum = i2_sum.add(v.pow(2));
                sum = sum.add(v);
            }
            BigInteger sum2 = sum.pow(2);
            BigInteger denom = sum.multiply(BigInteger.TWO);
            BigInteger norm = i2_sum.subtract(sum2);
            if(norm.compareTo(BigInteger.ZERO) == 0){
                ans.add(0L);
                return ans;
            }
            else if(denom.compareTo(BigInteger.ZERO) == 0 ||  norm.mod(denom.abs()).compareTo(BigInteger.ZERO) != 0)
                return null;
            else{
                long b;
                BigInteger fa =  norm.divide(denom);
                b = fa.longValue();
                ans.add(b);

                return ans;
            }

        }


        return ans;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            int K = sc.nextInt();
            int[] aa = new int[N];
            for (int j = 0; j < N; j++) {
                aa[j] = sc.nextInt();
            }
            ArrayList<Long> result = solve(N, K, aa);
            if(result == null)
                out.println("IMPOSSIBLE");
            else{
                for (int j = 0; j < result.size(); j++) {
                    if(j==0){
                        out.print(result.get(j));
                    }
                    else
                        out.print(" " + result.get(j));
                }
                out.println();
            }

        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}