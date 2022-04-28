package GoogleCodeJam.Y2022.Round1A.C;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/GoogleCodeJam/Y2022/Round1A/C/C-test.in";
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

    int E, W;
    int[][] m;
    int[][] memo = new int[101][101];

    int[][][][] common = new int[101][101][101][101];
    private int solve(int E, int W, int[][] m) {
        this.E = E;
        this.W = W;
        this.m = m;

        //now we do a recursion
        return 0;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            int E = sc.nextInt();
            int W = sc.nextInt();
            int[][] m = new int[E][W];
            for (int j = 0; j < E; j++) {
                for (int k = 0; k < W; k++) {
                    m[j][k] =sc.nextInt();
                }
            }
            out.print("Case #" + i + ": ");
            System.out.println(solve(E, W, m));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}