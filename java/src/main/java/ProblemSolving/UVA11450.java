package ProblemSolving;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 7/5/2016.
 */
public class UVA11450 {

    static int[][] dp = new int[201][21];
    static int[] modelcnt = new int[21];
    static int[][] modelprice = new int[21][21];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int ci=0; ci<nc; ++ci){
            int m = sc.nextInt();
            int c = sc.nextInt();

            for(int i=0; i<c; ++i){
                int k = sc.nextInt();
                modelcnt[i] = k;

                for(int j=0; j<k; ++j) {
                    modelprice[i][j] = sc.nextInt();
                }
            }

            for(int i=0; i<dp.length; ++i) Arrays.fill(dp[i], -1);
            int result = solve(m, c-1);

            if(result == -1)
                System.out.println("no solution");
            else
                System.out.println(result);
        }
    }

    static int solve(int m, int c){
        if(m < 0 ) return -1;
        if(c < 0) return 0;
        if(dp[m][c] != -1) return dp[m][c];

        //System.out.println("Solving " + m + " " + c);

        int maxm = -1;
        for(int i=0; i<modelcnt[c]; ++i){
            int result = solve(m-modelprice[c][i], c-1);
            if(result == -1) continue;
            maxm = Math.max(maxm, result+modelprice[c][i]);
        }

        dp[m][c] = maxm;
        return dp[m][c];
    }
}
