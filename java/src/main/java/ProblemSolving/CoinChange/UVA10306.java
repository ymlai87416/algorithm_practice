package ProblemSolving.CoinChange;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 problem: https://onlinejudge.org/external/103/10306.pdf
 level:
 solution:

 #dp #coinChange

 **/
public class UVA10306 {
    static int[] infoValue = new int[41];
    static int[] convValue = new int[41];
    static int m, S;
    static int[][] dp = new int[301][301];
    static TreeMap<Integer, Integer> combo;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();
        for(int ci=0; ci<nc; ++ci){
            m = sc.nextInt();
            S = sc.nextInt();

            for(int i=0; i<m; ++i){
                infoValue[i] = sc.nextInt();
                convValue[i] = sc.nextInt();
            }

            combo = new TreeMap<Integer, Integer>();

            for(int i=0; i<=S; ++i){
                int a = (int)Math.sqrt(S*S-i*i);
                if(a*a+i*i == S*S)
                    combo.put(i, a);
            }
            for(int i=0; i<dp.length; ++i)
                Arrays.fill(dp[i], -2);

            int minCoin = Integer.MAX_VALUE;
            for(Map.Entry<Integer, Integer> entry : combo.entrySet()){
                int result = minCoin(entry.getKey(), entry.getValue());
                if(result == -1) continue;
                if(result < minCoin)
                    minCoin = result;
            }

            if(minCoin == Integer.MAX_VALUE)
                System.out.println("not possible");
            else
                System.out.println(minCoin);
        }
    }

    static int minCoin(int X, int Y){
        if(X < 0 || Y < 0) return -1;
        if(X == 0 && Y == 0) return 0;

        int result = Integer.MAX_VALUE;
        if(dp[X][Y] != -2) return dp[X][Y];

        //System.out.println("minCoin " + X + " " + Y);

        for(int i=0; i<m; ++i){
            int temp = minCoin(X-infoValue[i], Y-convValue[i]);
            if(temp == -1) continue;
            result = Math.min(temp+1, result);
        }

        if(result == Integer.MAX_VALUE) result = -1;
        dp[X][Y] = result;
        return result;
    }
}
