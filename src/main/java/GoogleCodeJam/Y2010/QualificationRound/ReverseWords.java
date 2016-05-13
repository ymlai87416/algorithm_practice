package GoogleCodeJam.Y2010.QualificationRound;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Tom on 27/4/2016.
 */
public class ReverseWords {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;


    static{
        try{
            FILENAME = "B-large-practice";
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
        String st = sc.nextLine();
        StringTokenizer stt = new StringTokenizer(st);
        Stack<String> stk = new Stack<String>();
        while(stt.hasMoreTokens())
            stk.push(stt.nextToken());

        boolean first = true;
        while(!stk.empty()) {
            if (first) first = false;
            else out.print(" ");
            out.print(stk.pop());
        }
        out.println();
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
        new ReverseWords().run();
    }

}
