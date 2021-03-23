package GoogleCodeJam.Y2008.Round1A.A;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 30/4/2016.
 *
 * #Adhoc
 */
public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "A-large-practice";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, true)); ;
            */
            IN = null;
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

    private void solve() {
        int ans = 0;
        int ss = sc.nextInt();
        long x[] = new long[ss];
        long y[] = new long[ss];
        for(int i=0; i<ss; ++i)
            x[i] = sc.nextInt();
        for(int i=0; i<ss; ++i)
            y[i] = sc.nextInt();

        Arrays.sort(x); Arrays.sort(y);

        long result = 0;
        for(int i=0; i<ss; ++i)
            result += x[i] * y[ss-i-1];

        out.println(result);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            solve();
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
