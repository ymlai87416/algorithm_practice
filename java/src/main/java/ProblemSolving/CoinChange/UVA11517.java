package ProblemSolving.CoinChange;

import java.util.Arrays;
import java.util.Scanner;

/**
 problem: https://onlinejudge.org/external/115/11517.pdf
 level:
 solution:

 #dp #coinChange

 **/


public class UVA11517 {
    static int[][] dp = new int[101][20001];
    static int price;
    static int[] coins = new int[101];
    static int ncoin;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int ci=0; ci<nc; ++ci){
            price = sc.nextInt();
            ncoin = sc.nextInt();
            for(int i=0; i<ncoin; ++i)
                coins[i] = sc.nextInt();

            for(int i=0; i<dp.length; ++i)
                Arrays.fill(dp[i], Integer.MAX_VALUE);

            dp[0][0] = 0;
            for(int i=1; i<=ncoin; ++i){
                for(int j=20000; j>=0; --j){
                    int a = j-coins[i-1];
                    if(a < 0 || dp[i-1][a] == Integer.MAX_VALUE)
                        dp[i][j] = dp[i-1][j];
                    else{
                        dp[i][j] = Math.min(dp[i-1][j], dp[i-1][a]+1);
                    }
                }
            }

            /*for(int i=0; i<=ncoin; ++i){
                for(int j=0; j<2000; ++j)
                    System.out.print(dp[i][j]);
                System.out.println();
            }*/

            for(int i=price; i<20001; ++i){
                if(dp[ncoin][i] != Integer.MAX_VALUE) {
                    System.out.format("%d %d\n", i, dp[ncoin][i]);
                    break;
                }
            }
        }
    }
}
