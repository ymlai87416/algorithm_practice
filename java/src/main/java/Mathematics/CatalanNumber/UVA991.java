package Mathematics.CatalanNumber;

import java.io.*;
import java.util.*;
import java.math.*;

/**
 * Created by Tom on 28/4/2022.
 *
 * problem: https://onlinejudge.org/external/9/991.pdf
 * #UVA #catalan_number #Lv2
 */

public class UVA991 {
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/Mathematics/CatalanNumber/in.txt";
            OUT = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/Mathematics/CatalanNumber/out.txt";
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

    long catalanNumber(int n){
        if(n == 0|| n ==1) return 1;
        return catalanNumber(n-1) * (2*n) * (2*n-1) / (n+1) / n;
    }

    private long solve(int n) {
        long ans = catalanNumber(n);

        return ans;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        boolean first = true;
        while(sc.hasNextInt()){
            int n = sc.nextInt();
            long r = solve(n);
            if(!first) out.println();
            else first = false;
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new UVA991().run();
    }

}