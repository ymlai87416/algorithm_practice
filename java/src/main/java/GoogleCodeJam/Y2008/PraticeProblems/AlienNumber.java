package GoogleCodeJam.Y2008.PraticeProblems;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Tom on 25/4/2016.
 *
 * Start at 17:14 and end at 17:36, total minutes = 22 minutes.
 * Dont depends on java radix to much, the maximum is just 36.
 */
public class AlienNumber {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "A-large-practice";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, true)); ;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void solve() {
        String num = sc.next();
        String alien1 = sc.next();
        String alien2 = sc.next();

        int radix1 = alien1.length();
        long a = 0;
        for(int i=0; i<num.length(); ++i){
            a = a * radix1 + (alien1.indexOf(num.charAt(i)));
        }

        int radix2 = alien2.length();

        //String bb = Long.toString(a, radix2);
        StringBuilder result = new StringBuilder();
        while(a != 0) {
            char ca = alien2.charAt((int)(a % radix2));
            a = a / radix2;
            result.append(ca);
        }
        result = result.reverse();

        out.println(result.toString());
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            solve();
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new AlienNumber().run();
    }
}
