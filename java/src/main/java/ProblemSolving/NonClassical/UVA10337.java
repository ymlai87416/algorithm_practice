package ProblemSolving.NonClassical;

import java.util.Scanner;

/**
 * Created by Tom on 7/5/2016.
 */
public class UVA10337 {

    static int[][] wind = new int[1001][10];
    static int dist;
    static int[][] dp = new int[1001][10];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int ci=0; ci<nc; ++ci){
            dist = sc.nextInt();

            for(int j=9; j>=0; --j){
                for(int i=0; i<dist/100; ++i){
                    wind[i][j] = sc.nextInt();
                }
            }

            dp[dist/100][0] = 0;
            for(int j=1; j<10; ++j) dp[dist/100][j] = Integer.MAX_VALUE;
            for(int i=dist/100-1; i>=0; --i){
                dp[i][0] = Math.min(capAdd(30 - wind[i][0], dp[i+1][0]), capAdd(60 - wind[i][0], dp[i+1][1]));
                dp[i][9] = Math.min(capAdd(30 - wind[i][9], dp[i+1][9]), capAdd(20 - wind[i][9], dp[i+1][8]));
                for(int j=1; j<9; ++j){
                    dp[i][j] = Math.min(capAdd(30 - wind[i][j], dp[i+1][j]),
                            Math.min(capAdd(60 - wind[i][j], dp[i+1][j+1]), capAdd(20 - wind[i][j], dp[i+1][j-1])));
                }
            }
            System.out.println(dp[0][0]);
            System.out.println();
        }
    }

    static int capAdd(int a, int b){
        long result = (long)a+b;
        if(result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return (int)result;
    }

    /*static int solve(int X, int lat){
        if(X == dist/100 && lat != 0) return Integer.MAX_VALUE;
        if(X == dist/100 && lat == 0) return 0;

        int result;
        if(lat == 9){
            int goafter = solve(X+1, lat);
            int sinkafter = solve(X+1, lat-1);
            int go = 30 - wind[X][lat];
            int sink = 20 - wind[X][lat];

            go = goafter == Integer.MAX_VALUE ? Integer.MAX_VALUE : goafter+go;
            sink = sinkafter == Integer.MAX_VALUE ? Integer.MAX_VALUE : sinkafter+sink;
            result = Math.min(go, sink);

        } else if (lat == 0){
            int goafter = solve(X+1, lat);
            int climbafter = solve(X+1, lat+1);
            int go = 30 - wind[X][lat];
            int climb = 60 - wind[X][lat];

            go = goafter == Integer.MAX_VALUE ? Integer.MAX_VALUE : goafter+go;
            climb = climbafter == Integer.MAX_VALUE ? Integer.MAX_VALUE : climbafter+climb;
            result = Math.min(go, climb);

        } else{
            int goafter = solve(X+1, lat);
            int sinkafter = solve(X+1, lat-1);
            int climbafter = solve(X+1, lat+1);
            int go = 30 - wind[X][lat];
            int sink = 20 - wind[X][lat];
            int climb = 60 - wind[X][lat];

            go = goafter == Integer.MAX_VALUE ? Integer.MAX_VALUE : goafter+go;
            sink = sinkafter == Integer.MAX_VALUE ? Integer.MAX_VALUE : sinkafter+sink;
            climb = climbafter == Integer.MAX_VALUE ? Integer.MAX_VALUE : climbafter+climb;
            result = Math.min(go, Math.min(climb, sink));
        }

        dp[X][lat] = result;
        return dp[X][lat];
    }*/

}
