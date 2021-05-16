package GoogleCodeJam.Y2013.Round1B.C;

/*
   Pass the udebug, but not the code jam practice mode... wtf.

 */
import java.io.File;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2013\\Round1B\\C\\C-test.in";
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

    private double solve(int N, int X, int Y) {

        int cl = (Math.abs(X) + Y);
        if(cl%2!=0) return 0;

        int cl2 = cl/2;

        //check if the diamond enough to fill the current level
        //0 -> 1, 1 -> 5+1, 2 -> 9+5+1, 3 -> 13+9+5+1
        int prev = cl2 == 0 ? 0 : ffL(cl2-1);
        int cur = ffL(cl2);

        debug("cur level", cl2, " prev f: ", prev, " cur f: ", cur);

        if(N <= prev) {
            debug("failed to fill prev level");
            return 0;
        }

        if(N >= cur){
            debug("level already filled");
            return 1;
        }

        if(Y==2*cl2){
            debug("cannot fill top");
            return 0;
        }
        //now we know the y, just loop for the possible value s.t. fill a side N time

        int left = N-prev;
        debug(left, " to be filled up to level ", Y);

        //
        int maxL = Math.min(left, cl2*2);
        int minL = left - maxL;

        //not possible => lC1, lC2, .... lC(Y-1)
        //possible => lCY, lCY+1, ... lCl
        BigInteger temp  = BigInteger.ZERO;
        for (int i = Math.max(minL, Y+1); i <= maxL; i++) {
            debug("add ", left, "c", i);
            temp = temp.add(ncr(left, i));
        }

        BigDecimal tt = new BigDecimal(temp);
        BigDecimal p2 = new BigDecimal(total(left, minL, maxL));
        BigDecimal ans2 = tt.divide(p2, 9, RoundingMode.HALF_UP);

        return ans2.doubleValue();
    }


    private BigInteger total(int left, int minL, int maxL){
        BigInteger temp  = BigInteger.ZERO;
        for (int i = minL; i <= maxL; i++) {
            debug("add ", left, "c", i);
            temp = temp.add(ncr(left, i));
        }
        return temp;
    }

    private BigInteger pow2(int r){
        if(r == 1) return BigInteger.TWO;
        BigInteger a = pow2(r/2);
        if(r%2!=0)
            return a.multiply(a).multiply(BigInteger.TWO);
        else
            return a.multiply(a);
    }

    private BigInteger npr(int n, int r){
        BigInteger result = factorial(n).divide(factorial(n-r));
        //BigInteger result = factorial(n).multiply(factorial(n-r));
        return result;
    }

    private BigInteger ncr(int n, int r){
        BigInteger result = factorial(n).divide(factorial(r).multiply(factorial(n-r)));
        //BigInteger result = factorial(n).multiply(factorial(n-r));
        return result;
    }

    static BigInteger factorial(int n){
        BigInteger result = BigInteger.ONE;
        for(int i=2; i<=n; ++i){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    private int ffL(int L){
        return 2 * (L * (L+1) ) + L+1;
        //0 = 1
        //1 = 4+2 = 6
        //2 = 15
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            int X = sc.nextInt();
            int Y = sc.nextInt();
            double r = solve(N, X, Y);
            out.format("%.9f\n", r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
