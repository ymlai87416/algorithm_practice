package GoogleCodeJam.Y2017.Round1A;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by ymlai on 15/4/2017.
 */
public class A {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "A-large";
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


    private void solve(int r, int c, char[][] cake) {
        long ans = 0;

        //rule fill the column
        //rule fill empty column

        //right fill
        for(int i=0; i<r; ++i){
            char s = cake[i][0];
            for(int j=1; j<c; ++j){
                if(cake[i][j] == '?')
                    cake[i][j] = s;
                else
                    s = cake[i][j];
            }
        }

        //left fill
        for(int i=0; i<r; ++i){
            char s = cake[i][c-1];
            for(int j=c-2; j>=0; --j){
                if(cake[i][j] == '?')
                    cake[i][j] = s;
                else
                    s = cake[i][j];
            }
        }

        //upward
        for(int i=0; i<c; ++i){
            char s = cake[0][i];
            for(int j=1; j<r; ++j) {
                if (cake[j][i] == '?')
                    cake[j][i] = s;
                else
                    s = cake[j][i];
            }
        }

        //downward
        for(int i=0; i<c; ++i){
            char s = cake[r-1][i];
            for(int j=r-2; j>=0; --j) {
                if (cake[j][i] == '?')
                    cake[j][i] = s;
                else
                    s = cake[j][i];
            }
        }

        out.println();
        for(int i=0; i<r; ++i){
            for(int j=0; j<c; ++j)
                out.print(cake[i][j]);
            out.println();
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int r = sc.nextInt();
            int c = sc.nextInt();

            char[][] cake = new char[r][c];

            for(int p=0; p<r; ++p){
                String line = sc.next();

                for(int q=0; q<c; ++q){
                    cake[p][q] = line.charAt(q);
                }
            }


            solve(r, c, cake);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new A().run();
    }
}
