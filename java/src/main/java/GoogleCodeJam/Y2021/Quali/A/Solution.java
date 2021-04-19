package GoogleCodeJam.Y2021.Quali.A;

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
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2021\\Quali\\A\\A-test.in";
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

    private int reverseSort(int[] v){
        int cost = 0;
        for (int i = 0; i < v.length -1; i++) {
            int minpos = i;
            int minv = v[i];
            for (int j = i+1; j < v.length; j++) {
                if(v[j] < minv){
                    minv = v[j];
                    minpos = j;
                }
            }

            cost += minpos-i + 1;
            for (int j = i ,k=minpos; j<k; j++, k--) {
                int t = v[j];
                v[j] = v[k];
                v[k] = t;
            }

            /*System.out.print("D: ");
            for (int j = 0; j < v.length; j++) {
                System.out.print(v[j] + " ");
            }
            System.out.println();*/
        }

        return cost;
    }

    private int solve(int[] v) {
        int ans = reverseSort(v);
        return ans;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            int[] v = new int[N];
            for (int j = 0; j < N; j++) {
                v[j] = sc.nextInt();
            }
            System.out.println(solve(v));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}