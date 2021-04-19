package GoogleCodeJam.Y2011.Round1C.A;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2011\\Round1C\\A\\A-test.in";
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

    private char[][] solve(int N, int M, char[][] tile) {

        //just start doing it from the top-left corner, until we have no more to cover or cannot cover
        while(true){
            boolean findB = false;
            //find top left
            for (int i = 0; i < N && !findB; i++) {
                for (int j = 0; j < M && !findB; j++) {
                   if(tile[i][j] == '#'){
                       if(i+1 < N && j+1 < M &&
                               tile[i][j+1] == '#' && tile[i+1][j] == '#' && tile[i+1][j+1] == '#'){
                           tile[i][j] = '/';
                           tile[i][j+1] = '\\';
                           tile[i+1][j] = '\\';
                           tile[i+1][j+1] = '/';
                           findB = true;
                       }
                       else return null;
                   }
                }
            }
            if(!findB) return tile;
        }

    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            char[][] tt = new char[N][M];
            sc.nextLine();
            for (int j = 0; j < N; j++) {
                String line =sc.nextLine();
                for (int k = 0; k < M; k++) {
                    tt[j][k] = line.charAt(k);
                }
            }
            
            out.println("Case #" + i + ":");
            char[][] r =  solve(N, M, tt);

            if(r == null) out.println("Impossible");
            else {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < M; k++) {
                        out.print(r[j][k]);
                    }
                    out.println();
                }
            }
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}