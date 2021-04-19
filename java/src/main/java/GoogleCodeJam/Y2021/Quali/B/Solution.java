package GoogleCodeJam.Y2021.Quali.B;

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
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2021\\Quali\\B\\B-test.in";
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

    private int solve(int X, int Y, String c) {

        char[] cc =c.toCharArray();
        //this is a greedy, no need to change until it is necessary
        char prev = cc[0];
        for (int i = 0; i < cc.length; i++) {
            if(cc[i] == '?'){
                cc[i] = prev;
            } else prev = cc[i];
        }

        prev = cc[cc.length-1];
        for (int i = cc.length-1; i >=0 ; i--) {
            if(cc[i] == '?') cc[i]=prev;
            else prev = cc[i];
        }

        //System.out.println(new String(cc));

        int cost = 0;
        prev = cc[0];
        for(int i=1; i<cc.length; ++i){
            if(prev == 'C' && cc[i] == 'J') cost+=X;
            else if(prev == 'J' && cc[i] == 'C') cost+=Y;
            prev = cc[i];
        }


        return cost;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int X = sc.nextInt();
            int Y = sc.nextInt();
            String c = sc.next();
            System.out.println(solve(X, Y, c));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}