package GoogleCodeJam.Y2014.Round1C.A;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
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
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2014\\Round1C\\A\\A-test.in";
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

   private long gcd(long a, long b){
        if(a%b == 0) return b;
        else return gcd(b, a%b);
   }

    private int solve(long a, long b) {
        //if nAncestor can be divide by b, it is ok
        long gcdx = gcd(a, b);
        if(gcdx > 1){ a/=gcdx; b/=gcdx;};

        boolean valid =  nAncestor.mod(BigInteger.valueOf(b)).compareTo(BigInteger.ZERO) == 0;
        if(!valid) return -1;

        //now find the valid generation
        //((1+?)/2 + ? ) /2 = a/b
        int gen = 0;
        while(a < b){
            a = a *2;
            ++gen;
        }
        return gen;
    }

    BigInteger nAncestor;

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        nAncestor = BigInteger.valueOf(2);
        for (int i = 0; i < 40; i++) {
            nAncestor = nAncestor.multiply(BigInteger.TWO);
        }
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String line = sc.nextLine();
            String[] tok = line.split("/");
            long a = Long.parseLong(tok[0]);
            long b = Long.parseLong(tok[1]);
            int r = solve(a, b);
            if(r ==-1)
                out.println("impossible");
            else
                out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}