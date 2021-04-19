package GoogleCodeJam.Y2012.Round1A.A;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2012\\Round1A\\A\\A-test.in";
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

    private double solve(int A, int B, double[] p) {
        double ans;
        double[] probC = new double[A];

        probC[0] = p[0];
        for (int i = 1; i < A; i++) {
            probC[i] = probC[i-1] * p[i];
        }
        //retype
        double cont = probC[A-1] * (B-A+1) + (1-probC[A-1]) * (B-A+1+B+1);
        debug("cont: " + cont);
        ans = cont;

        //for backspace 1 =>  A
        for (int i = 0; i < A; i++) {
            double c = i==0 ? 1: probC[i-1];
            int back = A-i;
            double bsp = c * (2*back + (B-A)+1) + (1-c) * (2*back + (B-A) + 1 + B + 1);
            debug("backspace: " + back + " -> " + bsp);
            ans = Math.min(ans, bsp);
        }
        //press enter
        double enter = 1 + B +1 ;
        debug("enter: " + enter);
        ans = Math.min(ans, enter);

        return ans;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            double[] p = new double[A];
            for (int j = 0; j < A; j++) {
                p[j] = sc.nextDouble();
            }
            out.print("Case #" + i + ": ");
            out.println(solve(A, B, p));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}