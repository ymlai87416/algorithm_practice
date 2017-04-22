package GoogleCodeJam.Y2015.Round1C;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by ymlai on 22/4/2017.
 */
public class C {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "C-large-practice (2)";
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

    private void solve(int C, int D, int V, int[] M) {
        int ans = 0;

        long allOK = 0;
        TreeSet<Long> Ms = new TreeSet<>();
        for(int i=0; i<M.length; ++i)
            Ms.add((long)M[i]);

        while(allOK < V){
            long next = allOK+1;
            long nextOK = 0;
            boolean contains = Ms.contains(next);

            if(!contains) {
                System.out.println("Add " + next);
                ans++;
            }
            else
                Ms.remove(next);

            nextOK = allOK + next * C;

            TreeSet<Long> markDelete = new TreeSet<>();
            for(Long e: Ms)
                if(nextOK >= e) {
                    nextOK = nextOK + e * C;
                    markDelete.add(e);
                }

            Ms.removeAll(markDelete);

            allOK = nextOK;
        }

        out.println(ans);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            System.out.println("Case #" + i + ": ");
            int C, D, V;
            C = sc.nextInt();
            D = sc.nextInt();
            V = sc.nextInt();

            int[] M = new int[D];
            for(int j=0; j<D; ++j)
                M[j] = sc.nextInt();

            solve(C, D, V, M);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new C().run();
    }
}
