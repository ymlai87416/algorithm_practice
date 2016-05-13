package ProblemSolving.NonClassical;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 7/5/2016.
 *
 * Don't introduce more than enough variable... this question require no sorting.
 */
public class UVA10943 {

    static int dp[][] = new int[101][101];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(a == 0 && b == 0) break;

            for(int i=0; i<dp.length; ++i)
                    Arrays.fill(dp[i], -1);
            int result = solve(a, b, a);

            System.out.println(result);
        }
    }

    static int solve(int curInt, int K, int N){
        if(K == 1) return 1;
        if(dp[curInt][K] != -1) return dp[curInt][K];
        int result = 0;
        for(int i=0; i<=curInt; ++i){
            result = result + solve(curInt-i, K-1, N);
            result = result % 1000000;
        }
        dp[curInt][K] = result;
        return result;
    }
}
