package Graph.DirectedAcyclicGraph.ConvertingGeneralGraphToDAG;

import java.util.Arrays;
import java.util.Scanner;

/**
 problem: https://onlinejudge.org/external/9/907.pdf
 level:
 solution: Minimize the maximum (deal to day left) walking distance per day.

 #dp #convertGeneralGraphToDAG

 **/
public class UVA907 {

    static int[][] dp = new int[603][301];
    static int dpFunc(int start, int night){

        if(dp[start][night] != -1)
            return dp[start][night];

        if(night==0){
            int sum =0;
            for(int i=start+1; i<n+2; ++i)
                sum+=dist[i];

            dp[start][night] = sum;
            return sum;
        }

        if(start==n+1)
            return 0;

        int minDist = Integer.MAX_VALUE;
        int walked = 0;
        for(int i=start+1; i<n+2; ++i){
            walked += dist[i];
            minDist = Math.min(minDist, Math.max(dpFunc(i, night-1),walked));
        }
        dp[start][night] = minDist;

        return minDist;
    }

    static int n;
    static int k;
    static int[] dist;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextInt()){
            n = sc.nextInt();
            k = sc.nextInt();
            dist = new int[n+2];

            dist[0] = 0;
            for(int i=1; i<n+2; ++i){
                dist[i] = sc.nextInt();
            }

            for(int i=0; i<n+2; ++i)
                Arrays.fill(dp[i], -1);

            int ans = dpFunc(0, k) ;

            System.out.println(ans);
        }
    }
}
