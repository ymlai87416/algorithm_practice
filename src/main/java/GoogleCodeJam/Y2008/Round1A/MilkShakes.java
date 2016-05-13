package GoogleCodeJam.Y2008.Round1A;

import Template.CodeJamTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 5/5/2016.
 */
public class MilkShakes {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "A-test";
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
        int nmilkshake = sc.nextInt();
        int nc = sc.nextInt();

        int[] milkshake = new int[nmilkshake];
        Arrays.fill(milkshake, -1);

        boolean possible =true;
        for(int i=0; i<nc; ++i){
            int np = sc.nextInt();
            for(int j=0; j<np; ++j){
                int mt = sc.nextInt();
                int mal = sc.nextInt();
                mt--;

                if(milkshake[mt] == -1)
                    milkshake[mt]  =mal;
                else if(milkshake[mt] != mal)
                    possible=false;
            }
        }

        if(possible) {
            for (int i = 0; i < nmilkshake; ++i) {
                out.print(" ");
                out.print(milkshake[i]);
            }
            out.println();
        }
        else
            out.println(" IMPOSSIBLE");
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
        new MilkShakes().run();
    }
}
