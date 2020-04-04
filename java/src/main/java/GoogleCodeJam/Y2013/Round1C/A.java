package GoogleCodeJam.Y2013.Round1C; /**
 * Created by Tom on 9/4/2016.
 *
 * this example contains how to read a file.
 */

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class A {
    static String   FILENAME;
    static BufferedReader  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "A-large-practice (7)";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new BufferedReader(new FileReader(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false)); ;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private String vowels = "aeiou";
    private String S;

    private boolean isVowel(char c){
        return vowels.indexOf(c) >=0;
    }

    private void solve(String s, int n) {
        int ans = 0;

        int[] next = new int[s.length()];
        Arrays.fill(next,-1);

        int cc = 0;

        if(n==1&& !isVowel(s.charAt(s.length()-1))) {
            next[s.length() - 1] = s.length() - 1;
        }

        if(!isVowel(s.charAt(s.length()-1)))
            cc=1;

        for(int i=s.length()-2; i>=0; --i){
            if(isVowel(s.charAt(i))) {
                next[i] = next[i + 1];
                cc=0;
            }
            else{
                cc++;
                if(cc >=n)
                    next[i] = i;
                else
                    next[i] = next[i+1];
            }
        }

        /*for(int i=0; i<s.length(); ++i){
            System.out.print(next[i] + " ");
        }
        System.out.println();*/

        long cnt = 0;
        for(int i=0; i<s.length(); ++i){
            if(next[i] == -1) continue;
            int st = next[i] + n -1;
            cnt += (s.length()-st);
        }

        out.println(cnt);
    }


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = Integer.parseInt(sc.readLine());
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String s = sc.readLine();
            String[] token = s.split(" ");


            solve(token[0], Integer.parseInt(token[1]));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new A().run();
    }

}