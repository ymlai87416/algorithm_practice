package GoogleCodeJam.Y2020.Round1B.B;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
                    */

            //IN = "C:\\GitProjects\\algorithm_practice\\src\\main\\java\\GoogleCodeJam\\Y2020\\Quali\\D\\D-test.in";
            IN = null;
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

    int wall=2000000000;

    private void solve(int a, int b){
        int nTries = 300;

        int grid = wall / a;
        int fx=-1, fy=-1;
        int cx, cy;
        //check for all square center
        for(int i=0; i<grid; ++i){
            for(int j=0; j<grid; ++j){
                cx = (i+1) * a;
                cy = (j+1) * a;

                System.out.println(cx + " " + cy);
                System.out.flush();

                //read back
                String result = sc.nextLine();
                if(result.compareTo("CENTER")== 0){
                    return ;
                }
                else if(result.compareTo("HIT") == 0){
                    fx = cx;
                    fy = cy;
                    break;
                }
                nTries -=1;
            }
        }

        int lx, ly, rx, ry, tx, ty, bx, by;
        //then check for boundary, top and bottom, using binary search, start from B, and
        //assume we get (x, y)
        //left (x-?, y)
        int minv, maxv, midv;
        minv = Math.max(fx-b, 0);
        maxv = fx;
        while(minv < maxv){
            midv = (minv+maxv)/2;
            System.out.println(midv + " " + fy);
            System.out.flush();

            String result = sc.nextLine();
            if(result.compareTo("CENTER")== 0)
                return ;
            else if (result.compareTo("HIT") == 0)
                minv = midv;
            else
                maxv = midv;
        }
        lx = maxv; ly = fy;

        //right (x+?, y)
        minv = fx;
        maxv = Math.min(fx+b, wall);
        while(minv < maxv){
            midv = (minv+maxv)/2;
            System.out.println(midv + " " + fy);
            System.out.flush();

            String result = sc.nextLine();
            if(result.compareTo("CENTER")== 0)
                return ;
            else if (result.compareTo("HIT") == 0)
                minv = midv;
            else
                maxv = midv;
        }
        rx = maxv; ry = fy;

        //top
        minv = Math.max(fy-b, 0);
        maxv = fy;
        while(minv < maxv){
            midv = (minv+maxv)/2;
            System.out.println(fx + " " + midv);
            System.out.flush();

            String result = sc.nextLine();
            if(result.compareTo("CENTER")== 0)
                return ;
            else if (result.compareTo("HIT") == 0)
                minv = midv;
            else
                maxv = midv;
        }
        tx = fx; ty = maxv;

        //bottom
        minv = fy;
        maxv = Math.min(fy+b, wall);
        while(minv < maxv){
            midv = (minv+maxv)/2;
            System.out.println(fy + " " + midv);
            System.out.flush();

            String result = sc.nextLine();
            if(result.compareTo("CENTER")== 0)
                return ;
            else if (result.compareTo("HIT") == 0)
                minv = midv;
            else
                maxv = midv;
        }
        bx = fx; by = maxv;
        //then output center



        return ;
    }

    private String solveBig(int t, int b){
        //first we scan for each square of radius A
        //the we scan for the top/bottom/left/right
        //return the answer
        return "";
    }

    private void run() throws Exception {
        int t = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            solve(a, b);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}