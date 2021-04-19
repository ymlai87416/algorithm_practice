package GoogleCodeJam.Y2010.Round1A.B;
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
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2010\\Round1A\\B\\B-test.in";
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

    boolean debugflag = true;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    int D ;
    int I;
    int M;
    int N;
    int[] m;
    int[][] dp = new int[101][256];
    private int solve(int D, int I, int M, int N, int[] matrix) {

        this.D = D; this.I = I; this.M = M; this.N = N; this.m = matrix;

        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 256; j++) {
                dp[i][j] = -1;
            }
        }

        int ans = dpHelper(0, -1);

        //debug
        /*
        for (int i = 0; i <=N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j <=101; j++) {
                sb.append(dp[i][j] + "\t");
            }
            debug(sb.toString());
        }*/
        
        return ans;
    }
    
    //just ensure 0.... s-1 is ok
    private int dpHelper(int s, int last) {
        
        if(s == N) return 0;
        int result = Integer.MAX_VALUE;

        if(last != -1 && dp[s][last] != -1) return dp[s][last];

        //debug("f(" + s + ", "+ last + ")");

        int temp;
        //satisfy then ok
        if(last == -1 || Math.abs(last - m[s]) < M)
            temp = dpHelper(s+1, m[s]);
        else
            temp = Integer.MAX_VALUE;
        if(result > temp)
            result = temp;

        // delete. ok
        temp = dpHelper(s+1, last)+D;
        if(result > temp)
            result = temp;

        //change and insert
        for (int i = 0; i < 256; i++) {
            int minChange = Math.abs(m[s] - i);

            if(last != -1){
                //find out last -> i, now many shit
                int diff = Math.abs(last - i);
                int t;

                //Note: M = 0, watch carefully.
                if(M != 0) {
                    if (diff == 0) t = 0;
                    else t = diff % M == 0 ? diff / M - 1 : diff / M + 1 - 1;
                    temp = dpHelper(s + 1, i) + I * t + minChange;
                    if (result > temp)
                        result = temp;
                }
                else if(M== 0 && last==i){
                    temp = dpHelper(s + 1, i)  + minChange;
                    if (result > temp)
                        result = temp;
                }
            }
            else{
                //no need to add
                temp = dpHelper(s + 1, i) + minChange;
                if (result > temp)
                    result = temp;
            }
        }
        
        
        /*
        // can insert if need to
        if(last != -1) {
            if (last < m[s]) {
                for (int i = 1; i < m[s] - last; i++) {
                    int t = i % M == 0 ? i / M : i / M + 1;
                    temp = dpHelper(s, last+i) + I * t;

                    if (result > temp)
                        result = temp;
                }
            } else if (last > m[s]) {
                for (int i = 1; i < last - m[s]; i++) {
                    int t = i % M == 0 ? i / M : i / M + 1;
                    temp = dpHelper(s, last-i) + I * t;

                    if (result > temp)
                        result = temp;
                }
            }
        }

        //what is the case of add and then modify?

        // can modify: range = last-M, last+M
        if(last != -1) {  //has previous value
            for (int i = -M; i <= M; ++i) {
                if (last + i < 0 || last + i > 255) continue;
                temp = dpHelper(s + 1, last + i) + Math.abs(i);
                if (result > temp)
                    result = temp;
            }
        }
        else{  //no previos value
            for (int i = 0; i < 256; ++i) {
                temp = dpHelper(s + 1, i) + Math.abs(i);
                if (result > temp)
                    result = temp;
            }
        }*/

        if(last != -1)
            dp[s][last] = result;

        return result;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int D = sc.nextInt();
            int I = sc.nextInt();
            int M = sc.nextInt();
            int N = sc.nextInt();

            int[] m=  new int[N];

            for (int j = 0; j < N; j++) {
                m[j] = sc.nextInt();
            }
            out.print("Case #" + i + ": ");
            out.println(solve(D, I, M, N, m));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}