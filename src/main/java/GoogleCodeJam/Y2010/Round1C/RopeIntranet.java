package GoogleCodeJam.Y2010.Round1C;

import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Tom on 30/4/2016.
 */
public class RopeIntranet {
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

    class Rope{
        int left;
        int right;
        public Rope(int left, int right){
            this.left = left;
            this.right = right;
        }
    }
    private void solve() {
        int ans = 0;
        int rope = sc.nextInt();
        Rope[] ropes = new Rope[rope];
        for(int i=0; i<rope; ++i){
            int left = sc.nextInt();
            int right = sc.nextInt();
            ropes[i] = new Rope(left, right);
        }

        for(int i=0; i<ropes.length; ++i){
            for(int j=0; j<i; ++j){
                if(ropes[i].left > ropes[j].left && ropes[i].right < ropes[j].right ||
                        ropes[i].left < ropes[j].left && ropes[i].right > ropes[j].right)
                    ++ans;
            }
        }

        out.println(ans);
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
        new RopeIntranet().run();
    }
}
