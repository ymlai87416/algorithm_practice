package GoogleCodeJam.Y2009.Round1A.C;


/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2009\\Round1A\\C\\C-test.in";
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

    int _C, _N;
    BigDecimal[] dp = new BigDecimal[41];
    
    private double solveSmall(int C, int N) {
        _C = C; _N = N;
        for (int i = 0; i < 41; i++) {
            dp[i] = null;
        }
        double ans = 1+dpHelper(_C - _N).doubleValue();
        return ans;
    }

    private BigDecimal dpHelper(int wanted){
        //System.out.println("D: " + wanted);
        if(wanted == 0) return BigDecimal.ZERO;
        if(dp[wanted] != null) return dp[wanted];
        else{
            int have = _C-wanted;

            //prob of getting card which already have = hCn, total combination = cCn
            BigDecimal  totalCombination = new BigDecimal(nCr(_C, _N));
            BigDecimal ans = BigDecimal.ONE;

            //case package does not match any card
            BigInteger matchedCases = nCr(have, Math.min(_N, have));
            BigDecimal prob0 = new BigDecimal(matchedCases).divide(totalCombination, 20, RoundingMode.HALF_EVEN);
            prob0 = BigDecimal.ONE.subtract(prob0);

            for (int i = 1; i <=Math.min(_N, wanted); i++) {
                //here it match with at least one card which we want, but we can only match all = h
                matchedCases = nCr(wanted , i).multiply(nCr(have, Math.min(have, _N-i)));
                BigDecimal prob = new BigDecimal(matchedCases).divide(totalCombination, 20, RoundingMode.HALF_EVEN);
                //System.out.println("f"+ wanted +"match: " + i + " c:" + matchedCases.toString() + " p:" + prob.toString());

                ans = ans.add( prob.multiply(dpHelper(wanted-i))) ;
            }

            //System.out.println("f"+ wanted + ": " + ans + " / " + prob0);
            dp[wanted] = ans.divide(prob0, 10, RoundingMode.HALF_EVEN);
            //System.out.println("f(" + wanted + "): " + dp[wanted].toString());

            return dp[wanted];
        }
    }

    static BigInteger nCr(int n, int r){
        return factorial(n).divide(factorial(r).multiply(factorial(n-r)));
    }

    static BigInteger factorial(int n){
        BigInteger result = BigInteger.ONE;
        for(int i=2; i<=n; ++i){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int C = sc.nextInt();
            int N = sc.nextInt();
            double d = solveSmall(C, N);
            System.out.println(d);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}