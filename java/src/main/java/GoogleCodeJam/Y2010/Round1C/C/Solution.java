package GoogleCodeJam.Y2010.Round1C.C;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1C\\A\\A-test.in";
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

    int[][] bb = new int[2048+1][2048+1];
    private void solve(int M, int N, boolean[][] b) {
        int ans = 0;

        //this is the maximal square?


        out.println(ans);
    }

    private void floodfill(int M, int N, boolean[][] b){
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                
            }
        }
    }


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int M = sc.nextInt();
            int N = sc.nextInt();
            boolean[][] b = new boolean[M*4][N*4];
            sc.nextLine();
            String line;
            for (int j = 0; j < M; j++) {
                line = sc.nextLine();
                for (int k = 0; k < line.length(); k++) {
                    b[j][k*4] = ((line.charAt(k) & 1) > 0);
                    b[j][k*4+1] = ((line.charAt(k) & 2) > 0);
                    b[j][k*4+2] = ((line.charAt(k) & 4) > 0);
                    b[j][k*4+3] = ((line.charAt(k) & 8) > 0);
                }
            }

            solve(M, N, b);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}