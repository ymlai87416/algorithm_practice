package GoogleCodeJam.Y2016.Round1A;

import Template.CodeJamTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by ymlai on 21/4/2017.
 */
public class A {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "A-large-practice (1)";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false)); ;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void solve(String a) {
        String prev = "";
        for(int i=0; i<a.length(); ++i){
            char t = a.charAt(i);

            String t1 = t+prev;
            String t2 = prev+t;

            if(t1.compareTo(t2) >= 0){
                prev = t1;
            }
            else
                prev = t2;
        }

        out.println(prev);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String S = sc.next();
            solve(S);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new A().run();
    }

}
