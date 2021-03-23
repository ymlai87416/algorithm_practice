package GoogleCodeJam.Y2009.Round1C;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Tom on 5/5/2016.
 */
public class AllYourBase {
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

    String base = "00123456789abcdefghijklmnopqrstuvwxyz";
    private void solve() {
        int ans = 0;
        String s = sc.nextLine();
        TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();

        for(int i=0; i<s.length(); ++i){
            map.put(s.charAt(i), -1);
        }

        int radix;
        int dsize = map.size();
        if(dsize < 2) radix = 2;
        else radix = dsize;

        int cnt = 0;
        map.put(s.charAt(0), 1);
        BigInteger val = BigInteger.ONE;
        for(int i=1; i<s.length(); ++i){
            char cc = s.charAt(i);
            int dcc = map.get(cc);
            if(dcc == -1) {
                map.put(cc, cnt);
                if (cnt == 0) cnt = 2;
                else cnt++;
            }

            dcc = map.get(cc);
            val = val.multiply(BigInteger.valueOf(radix)).add(BigInteger.valueOf(dcc));
        }

        out.println(val.toString());
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
        new AllYourBase().run();
    }
}
