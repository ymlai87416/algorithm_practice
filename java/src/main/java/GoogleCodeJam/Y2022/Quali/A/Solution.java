package GoogleCodeJam.Y2022.Quali.A;

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
            IN = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/GoogleCodeJam/Y2022/Quali/A/A-test.in";
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

    private char[][] solve(int R, int C) {
        char[][] card = new char[2*R+1][2*C+1];
        for(int i=0; i<card.length; i+=2){
            for (int j = 0; j < card[i].length; j++) {
                if(j%2==0) card[i][j] = '+';
                else card[i][j] = '-';
            }
        }
        for(int i=1; i<card.length; i+=2){
            for (int j = 0; j < card[i].length; j++) {
                if(j%2==0) card[i][j] = '|';
                else card[i][j] = '.';
            }
        }

        //we destroy the first cell
        card[0][0] = '.';
        card[0][1] = '.';
        card[1][0] = '.';
        return card;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.println("Case #" + i + ": ");
            int R = sc.nextInt();
            int C = sc.nextInt();
            char[][] card = solve(R, C);
            for(int j=0; j<card.length; ++j)
                out.println(new String(card[j]));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}