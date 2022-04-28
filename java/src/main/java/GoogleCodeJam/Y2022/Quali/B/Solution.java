package GoogleCodeJam.Y2022.Quali.B;

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
            IN = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/GoogleCodeJam/Y2022/Quali/B/B-test.in";
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

    private int[] solve(int[][] comp) {
        int[] ans = new int[4];
        //find out the min of each color
        //deduct it from 10^6, if >0 return ans
        int[] minInk = new int[4];
        for (int i = 0; i <4; i++) {
            minInk[i] = comp[0][i];
        }
        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                minInk[j] = Math.min(minInk[j], comp[i][j]);
            }
        }

        int ink = 1000_000;

        for(int i=0; i<4; ++i){
            ans[i] = Math.min(minInk[i], ink);
            ink -= ans[i];
        }
        //System.out.println("shit" + ink);
        if(ink > 0) return null;

        return ans;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        int[][] input = new int[3][4];
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            for (int j = 0; j <3; j++) {
                for (int k = 0; k < 4; k++) {
                    input[j][k] = sc.nextInt();
                }
            }
            int[] r = solve(input);
            if(r == null)
                System.out.println("IMPOSSIBLE");
            else
            System.out.println(r[0] + " " + r[1]+ " " + r[2]+ " " + r[3] );

        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}