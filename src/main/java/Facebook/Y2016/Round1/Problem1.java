package Facebook.Y2016.Round1;

import Geometry.PointsAndLines.UVA920;

import java.io.*;
import java.util.*;

//start at 7:38, end at 8:20....
//you are missing the word "ORDER LIST" which you cannot change the given order.
public class Problem1 {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "C:\\Users\\Tom\\Documents\\algorithm_practice\\coding_contest_creation (1)";
            IN = FILENAME + ".txt";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void solve(int c, int k, int[] problems) {

        int addp = 0;
        int p = problems[0];
        int cp = 1;
        for(int i=1; i<k; ++i){
            if(problems[i] <= p){
                //new contest
                if(cp == 4){
                    cp = 1;
                    p = problems[i];

                    System.out.println("New contest: " + p );
                }
                else {
                    addp += 4 - cp;
                    p = problems[i];
                    cp = 1;
                    System.out.println("Content rebuild.");
                    System.out.println("New contest (equal): " + p );
                }
            }
            else if(problems[i] -p > 10){
                if(cp == 4) {
                    cp = 1;
                    p = problems[i];
                    System.out.println("New contest: " + p );
                }
                else {
                    addp += 1;
                    p = p + 10;
                    cp += 1;
                    --i;
                    System.out.println("Add to contest (new): " + p );
                }
            }
            else{
                if(cp == 4) {
                    cp = 1;
                    p = problems[i];
                    System.out.println("New contest: " + p );
                }
                else {
                    cp += 1;
                    p = problems[i];
                    System.out.println("Add to contest: " + p );
                }
            }
        }

        if(cp != 4)
            addp += 4-cp;

        System.out.println(addp);
        out.println(addp);
    }

    int[] p = new int[100000];

    private void run() throws Exception {

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int k = sc.nextInt();

            for(int j=0; j<k; ++j){
                p[j] = sc.nextInt();
            }

            solve(i, k , p);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Problem1().run();
    }
}
