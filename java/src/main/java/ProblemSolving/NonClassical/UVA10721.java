package ProblemSolving.NonClassical;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 8/5/2016.
 */
public class UVA10721{
    static long[][][] dp = new long[51][51][51];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<51; ++i)
            for(int j=0; j<51; ++j)
                Arrays.fill(dp[i][j], -1);

        while(true){
            if(!sc.hasNext()) break;
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();

            sc.nextLine();

            System.out.println(solve(n,m,k));
        }
    }

    static long solve(int n, int k, int m){
        if(n==k) return 1;
        if(n<k) return 0;
        if(k ==1 && n > m) return 0;
        if(k ==1 && n <= m) return 1;

        if(dp[n][k][m] != -1) return dp[n][k][m];
        long result = 0;

        for(int i=1; i<=m; ++i){
            result += solve(n-i, k-1, m);
        }

        dp[n][k][m]=result;
        return result;
    }
}
