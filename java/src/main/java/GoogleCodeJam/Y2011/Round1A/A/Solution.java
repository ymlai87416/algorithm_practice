package GoogleCodeJam.Y2011.Round1A.A;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2011\\Round1A\\A\\A-test.in";
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

    private boolean solve(long N, long Pd, long Pg) {
        /*
            Pd = Wd / D
            Pg = Wg / Sum(Di)

            Pd can always possible, Pg?
            possible if  Pg = Wd + whatever / kN is possible
            Pg always possible.

         */

        //find the max of such
        long mWd = -1, mDd = -1;
        if(N <=100) {
            for (int i = (int)N; i >=1 ; i--) {
                if(Pd * i % 100 == 0){
                    mWd = Pd * i / 100;
                    mDd = i;
                    debug("Found " + mWd + " / " + mDd);
                    break;
                }
            }
        }
        else{
            long maxR = N / 100;
            mWd = Pd * maxR;
            mDd = 100 * maxR;
            debug("Found " + mWd + " / " + mDd);
        }

        if(mWd == -1 || mDd == -1) return false;

        if(Pg == 100)
            return Pd == 100;
        if(Pg == 0)
            return Pd == 0;
        else {
            //always can find a big number to make it close to 100% by adding Di to wingame
            //can alway find a big number to make it close to 0. by adding 0 to wingame
            return true;
        }

    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            long N = sc.nextLong();
            long Pd = sc.nextLong();
            long Pg = sc.nextLong();
            System.out.println(solve(N, Pd, Pg) ? "Possible" : "Broken" );
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}