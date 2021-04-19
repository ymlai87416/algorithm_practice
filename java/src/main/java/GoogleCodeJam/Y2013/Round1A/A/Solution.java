package GoogleCodeJam.Y2013.Round1A.A; /**
 * Created by Tom on 9/4/2016.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2013\\Round1A\\A\\A-test.in";
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

    private void solve(BigInteger r, BigInteger t) {
        int ans = 0;

        //find numCircle
        BigInteger high = BigInteger.valueOf(3037000499L); //sqrt(Long.MAX)
        BigInteger low = BigInteger.valueOf(1);
        BigInteger mid;

        while(low.add(BigInteger.ONE).compareTo(high) < 0){
            mid = low.add(high).divide(BigInteger.valueOf(2));

            //System.out.format("H: %s L: %s M: %s V: %s\n", high.toString(), low.toString(), mid.toString(), paint(mid, r).toString());

            if(paint(mid, r).compareTo(t) > 0)
                high = mid;
            else
                low = mid;
        }

        out.println(low);
    }

    private static BigInteger paint(BigInteger numCircle, BigInteger r){
        //formula: (2r+1)*n + (n-1)n*2

        BigInteger a = numCircle.multiply(numCircle.subtract(BigInteger.ONE)).multiply(BigInteger.valueOf(2));
        BigInteger b = numCircle.multiply(r.multiply(BigInteger.valueOf(2)).add(BigInteger.ONE));

        return a.add(b);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            BigInteger r = new BigInteger(sc.next());
            BigInteger T = new BigInteger(sc.next());
            solve(r, T);
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