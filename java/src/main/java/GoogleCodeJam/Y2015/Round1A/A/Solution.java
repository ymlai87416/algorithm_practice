package GoogleCodeJam.Y2015.Round1A.A;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2015\\Round1A\\A\\A-test.in";
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

    private int[] solve(int N, int[] m) {
        int[] r = new int[2];
        //any mushroom min
        for (int i = 1; i < N; i++) {
            if(m[i] < m[i-1])
                r[0] += (m[i-1]-m[i]);
        }
        //constant mushroom min, find the max diff and just output
        int minR = 0;
        for (int i = 1; i < N; i++) {
            int e = m[i-1] - m[i];
            if(e > minR)
                minR=e;
        }
        r[1] = 0;
        for (int i = 1; i < N; i++) {
            r[1] += Math.min(m[i-1], minR);
        }

        return r;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int N= sc.nextInt();
            int[] m = new int[N];
            for (int j = 0; j < N; j++) {
                m[j] = sc.nextInt();
            }
            out.print("Case #" + i + ": ");
            int[] r = solve(N, m);
            out.format("%d %d\n", r[0], r[1]);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}