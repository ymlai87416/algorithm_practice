package GoogleCodeJam.Y2012.Round1B.C;

import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

/*
MLE dp even on set = 50
 */

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2012\\Round1B\\C\\C-test.in";
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

    HashSet<Long> lookup = new HashSet<>();

    private void solve(int N) {
        int ans = 0;
        TreeSet<Long> a = new TreeSet<>();
        TreeSet<Long> b = new TreeSet<>();

        lookup.clear();
        for (int i = 0; i < N; i++) {
            lookup.add(M[i]);
        }

        for (int i = 0; i < N; i++) {
            for (Long tt : a) {
                b.add(tt+M[i]);
            }
            b.add(M[i]);

            TreeSet<Long> ttt = a;
            a = b;
            b = ttt;
        }

        a.add(0l);

        //now we backtrack
        for (Long k : a){
            if(k==0) continue;
            TreeSet<Long> r1 = new TreeSet<Long>();
            TreeSet<Long> r2 = new TreeSet<Long>();
            if(bt(k, k, a, r1, r2)){
                boolean first= true;
                for (Long ri : r1) {
                    if(first){
                        out.print(ri);
                        first = false;
                    }
                    else
                        out.print(" " + ri);
                }
                out.println();

                first= true;
                for (Long ri : r2) {
                    if(first){
                        out.print(ri);
                        first = false;
                    }
                    else
                        out.print(" " + ri);
                }
                out.println();

                return;
            }
            else
                debug("failed at ", k);
        }

        out.println("Impossible");
    }

    private boolean bt(long k1, long k2, TreeSet<Long> p, TreeSet<Long> a, TreeSet<Long> b){
        debug(k1, k2, a, b);
        if(k1==0){
            //now look for another way to build ok. but this time with filter
            if(k2==0)
                return true;
            for (Long t : p) {
                if (t >= k2) break;

                long diff = k2 - t;
                if (lookup.contains(diff) && !a.contains(diff) && !b.contains(diff)) {
                    b.add(diff);
                    boolean pp = bt(k1, t, p, a, b);
                    if(!pp) b.remove(diff);
                    else return true;
                }
            }
        }
        else {
            for (Long t : p) {
                if (t >= k1) break;

                long diff = k1 - t;
                if (lookup.contains(diff) && !a.contains(diff)) {
                    a.add(diff);
                    boolean pp = bt(t, k2, p, a, b);
                    if(!pp) a.remove(diff);
                    else return true;
                }
            }
        }
        return false;
    }

    long[] M = new long[501];
    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ":\n");
            int N = sc.nextInt();

            for (int j = 0; j < N; j++) {
                M[j] = sc.nextLong();
            }
            solve(N);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
