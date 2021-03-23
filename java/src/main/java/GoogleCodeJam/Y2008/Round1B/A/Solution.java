package GoogleCodeJam.Y2008.Round1B.A;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by ymlai on 11/4/2017.
 *
 * #Combination
 */
public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1B\\A\\A-large-practice";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
             */

            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1B\\A\\A-test.in";
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

    private void solve(int n) {
        int sum = 0;

        out.println(sum);
    }

    private static
    class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x; this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static int nCr(int n, int r)
    {
        return fact(n) / (fact(r) *
                fact(n - r));
    }

    // Returns factorial of n
    static int fact(int n)
    {
        int res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }

    private long solveB(int n, int A, int B, int C, int D, int x0, int y0, int M){
        long x, y;
        x = x0; y= y0;

        //there are 9 set
        HashSet<Point>[] list = new HashSet[9];
        for(int i=0; i<9; ++i)
            list[i] = new HashSet<Point>();
        int cnt = 1;
            
        int amod, bmod;
        do{
            amod = (int)(x % 3);
            bmod = (int)(y % 3);

            int index = amod*3+bmod;
            boolean rr = list[index].add(new Point((int)x, (int)y));
            //if(rr)System.out.println("Point reject.");

            x = (x * A + B) % M;
            y = (y * C + D) % M;
            ++cnt;
        }while(cnt <= n);

        long result = 0;
        for(int i=0; i<9; ++i){
            for(int j=i+1; j<9; ++j){
                for(int k=j+1; k<9; ++k){
                    int famod = (i/3 + j/3 + k/3) % 3;
                    int fbmod = (i%3 + j%3 + k%3) % 3;

                    if(famod == 0 && fbmod == 0){
                        //System.out.println(i + " " + j + " " + k);
                        //no point can be appear in 2+ set;
                        long t = 1;
                        result += t * list[i].size() * list[j].size() * list[k].size();
                    }
                }
            }
        }

        //now we choose 3 points from same set
        for(int i=0; i<9; ++i){
            //System.out.println(i + " " + i + " " + i);
            long ls = list[i].size();
            result += ls * (ls-1) / 2 * (ls-2) / 3;
        }

        //now we choose 2 points from same set, no quite possible
        for(int i=0; i<9; ++i){
            for(int j=i+1; j<9; ++j){
                int famod = (i/3 + i/3 + j/3) % 3;
                int fbmod = (i%3 + i%3 + j%3) % 3;

                if(famod == 0 && fbmod == 0){
                    System.out.println("C" + i + " " + i + " " + j);
                    //no point can be appear in 2+ set;
                    result += list[i].size() * (list[i].size()-1) * list[j].size();
                }

                famod = (i/3 + j/3 + j/3) % 3;
                fbmod = (i%3 + j%3 + j%3) % 3;

                if(famod == 0 && fbmod == 0){
                    //no point can be appear in 2+ set;
                    //System.out.println("C" + i + " " + j + " " + j);
                    result += list[i].size() * list[j].size() * (list[j].size()-1);
                }
            }
        }

        return result;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int n = sc.nextInt();
            int A = sc.nextInt(); int B = sc.nextInt(); int C = sc.nextInt(); int D= sc.nextInt();
            int x0 = sc.nextInt(); int y0 = sc.nextInt(); int M = sc.nextInt();
            long r = solveB(n, A, B, C, D, x0, y0, M);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}


/*
0 1 2
0 3 6
0 4 8
0 5 7
1 3 8
1 4 7
1 5 6
2 3 7
2 4 6
2 5 8
3 4 5
6 7 8
0 0 0
1 1 1
2 2 2
3 3 3
4 4 4
5 5 5
6 6 6
7 7 7
8 8 8

 */