package GoogleCodeJam.Y2016.Round1B;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by ymlai on 21/4/2017.
 */
public class A {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "A-large-practice (2)";
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

    private void solve(String S) {

        ArrayList<Integer> ans = new ArrayList<>();
        if(S.indexOf('Z') != -1){
            while(S.indexOf('Z') != -1){
                ans.add(0);
                S = remove(S, "ZERO", 1);
            }
        }
        if(S.indexOf('W') !=-1){
            while(S.indexOf('W') != -1){
                ans.add(2);
                S= remove(S, "TWO", 1);
            }
        }
        if(S.indexOf('X') != -1){
            while(S.indexOf('X') != -1){
                ans.add(6);
                S=remove(S, "SIX", 1);
            }
        }
        if(S.indexOf('G') != -1){
            while(S.indexOf('G') != -1){
                ans.add(8);
                S=remove(S, "EIGHT", 1);
            }
        }
        if(S.indexOf('S') != -1){
            while(S.indexOf('S') != -1){
                ans.add(7);
                S=remove(S, "SEVEN", 1);
            }
        }
        if(S.indexOf('V') != -1){
            while(S.indexOf('V') != -1){
                ans.add(5);
                S=remove(S, "FIVE", 1);
            }
        }
        if(S.indexOf('I') != -1){
            while(S.indexOf('I') != -1){
                ans.add(9);
                S=remove(S, "NINE", 1);
            }
        }
        if(S.indexOf('N') != -1){
            while(S.indexOf('N') != -1){
                ans.add(1);
                S=remove(S, "ONE", 1);
            }
        }
        if(S.indexOf('E') != -1){
            while(S.indexOf('E') != -1){
                ans.add(3);
                S=remove(S, "THREE", 1);
            }
        }
        if(S.indexOf('U') != -1){
            while(S.indexOf('U') != -1){
                ans.add(4);
                S=remove(S, "FOUR", 1);
            }
        }

        Collections.sort(ans);

        for(int i=0; i<ans.size(); ++i)
            out.print(ans.get(i));
        out.println();
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            String S = sc.next();

            solve(S);
        }
        sc.close();
        out.close();
    }

    private String remove(String s, String t, int times){
        String ans = s;
        for(int i=0; i<t.length(); ++i)
            ans = ans.replaceFirst(t.substring(i, i+1), "");
        return ans;
    }

    public static void main(String args[]) throws Exception {
        new A().run();
    }

}
