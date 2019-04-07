package GoogleCodeJam.Y2019.Quali.A;

import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
                    */

            //IN = "A-test.in";
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

    static char a_choose[] = {'0', '1','2','3','3','5','6','7','8','9'};
    static char b_choose[] = {'0','0','0','0','1','0','0','0','0','0'};

    private void solve(String r){
        String a, b;
        a = "";
        b = "";
        for(int i=0; i<r.length(); ++i){
            int j = Integer.parseInt(String.valueOf(r.charAt(i)));
            a = a + a_choose[j];
            b = b + b_choose[j];
        }

        BigInteger ai, bi;
        ai = new BigInteger(a);
        bi = new BigInteger(b);

        out.print(ai);
        out.print(" ");
        out.print(bi);

        out.println();
    }

    private void run() throws Exception {
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            String r = sc.nextLine();

            solve(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
