package GoogleCodeJam.Y2010.Round1A.C;

/**
 * Created by Tom on 9/4/2016.
 */
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
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2010\\Round1A\\C\\C-test.in";
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

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private int gcd(int a, int b){
        if(a % b == 0) return b;
        return gcd(b, a%b);
    }

    int[][] dp = new int[100][100];

    private boolean game(int A, int B){
        if(dp[A][B] !=-1) return dp[A][B] == 0  ? false: true;
        int x, y;
        x  = Math.max(A, B); y = Math.min(A, B);
        int mk = x / y;
        boolean canWin = false;
        for (int i = mk; i >=1; --i) {

            if(x-i*y == 0) continue; //lose

            boolean temp = !game(x-i*y, y);
            if(temp)
                canWin = true;
        }

        dp[A][B] = canWin ? 1: 0;

        return canWin;
    }

    private boolean game2(int A, int B){
        int x, y;
        x  = Math.max(A, B); y = Math.min(A, B);
        int mk = x / y;

        for (int i = mk; i >=1; --i) {
            int ax = x-i*y;
            int ay = y;

            if(ax == 0) continue;
            if(ax == ay) return true;
            if(ax < ay && ay % ax == 0) continue;
            if(ay < ax && ax % ay == 0) continue;

            return !game2(ax, ay);
        }
        return false;
    }

    private void solveObs(){

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(game(3, 10));
        System.out.println(game(10, 3));


        char [][] bb = new char[100][100];
        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                bb[i][j] = game(i, j) ? 'T' : 'F';
            }
        }

        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                System.out.print(bb[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int solve(int A1, int A2, int B1, int B2) {
        int ans = 0;

        // A  win if they are not relatively prime
        for (int i = A1; i <= A2; i++) {
            for (int j = B1; j <= B2; j++) {
                /*
                int g = gcd(Math.max(i, j), Math.min(i,j));
                if(g != 1)
                    ans++;

                 */
                if(game2(i, j))
                    ans++;
            }
        }
        
        return ans;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));

        if(true){
            solveObs();
            return;
        }
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int A1 = sc.nextInt();
            int A2 = sc.nextInt();
            int B1 =sc.nextInt();
            int B2 =sc.nextInt();
            System.out.println(solve(A1, A2, B1, B2));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}