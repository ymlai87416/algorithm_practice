package Mathematics.ExtendedEuclidean;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by Tom on 28/4/2022.
 *
 * problem: https://onlinejudge.org/external/106/10633.pdf
 * #UVA #extended_euclidean #Lv2
 */

public class UVA10633 {
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            //IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1C\\A\\A-test.in";
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

    // store x, y, and d as global variables
    int x, y, d;

    void extendedEuclid(int a, int b) {
        if (b == 0) { x = 1; y = 0; d = a; return; } // base case
        extendedEuclid(b, a % b); // similar as the original gcd
        int x1 = y;
        int y1 = x - (a / b) * y;
        x = x1;
        y = y1;
    }

    private List<Long> solve(long C) {
        ArrayList<Long> ai = new ArrayList<>();
        //the equation is 9a + b = C
        //extendedEuclid(9, 1);

        //System.out.println(x + " " +  y);

        //equation: 9 (0+n) + 1 (C - 9n) = C
        //a = 0+n   a >= 0
        //b = C-9n  0 <= b < 10

        long n = C/9;
        long b1 = C - 9*n;
        long a1 = n;
        ai.add(a1 * 10+b1);

        long test1 = C-9*(n-1);
        if(0 <= test1 && test1 < 10) {
            b1 = test1;
            a1 = n-1;
            ai.add(a1 * 10+b1);
        }
        long test2 = C-9*(n+1);
        if(0 <= test2 && test2 < 10) {
            b1 = test2;
            a1 = n+1;
            ai.add(a1 * 10+b1);
        }

        Collections.sort(ai);

        return ai;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        while(true) {
            long a = sc.nextLong();
            if(a == 0) break;
            List<Long> r = solve(a);
            boolean first = true;
            for(long rr : r){
                if(first) {
                    out.print(rr); first=false;
                }
                else
                    out.print(" " + rr);
            }
            out.println();
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new UVA10633().run();
    }

}
