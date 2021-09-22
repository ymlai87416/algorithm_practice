package ProblemSolving.TSP;

import java.util.Arrays;
import java.util.Scanner;

/**
 problem: https://onlinejudge.org/external/104/10496.pdf
 level:
 solution: Thinking for day, just circumvent the TLE using difference case. instead of formula a general case... time spends: 1 day.

 #dp #traveling_salesman_problem #UVA #Lv2

 **/

public class UVA10496 {
    static int[][] dist = new int[11][11];
    static int[] xcoord = new int[11];
    static int[] ycoord = new int[11];
    static int point;

    static boolean[][] vis = new boolean[11][1 << 11]; // is_visited
    static int[][] val = new int[11][1 << 11]; // cost at particular state


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int ci=0; ci<nc; ++ci){
            int w = sc.nextInt();
            int h = sc.nextInt();

            int sx= sc.nextInt();
            int sy = sc.nextInt();

            xcoord[0] = sx; ycoord[0] = sy;

            point = sc.nextInt();

            for(int i=0; i<dist.length; ++i)
                Arrays.fill(dist[i], 0);

            for(int i=0; i<point; ++i){
                int px = sc.nextInt();
                int py = sc.nextInt();
                xcoord[i+1] = px;
                ycoord[i+1] = py;
            }

            for(int i=0; i<=point; ++i){
                for(int j=0; j<=point; ++j){
                    dist[i][j] = Math.abs(xcoord[i] - xcoord[j]) + Math.abs(ycoord[i] - ycoord[j]);
                }
            }

            ++point;
            for(int i=0; i<vis.length; ++i)
                Arrays.fill(vis[i], false);

            int minDist = Integer.MAX_VALUE;
            for(int i=1; i<point; ++i){
                int temp = dp(i, 0) + dist[0][i];
                minDist = Math.min(minDist, temp);
            }

            /*for(int i=0; i<=point; ++i){
                for(int j=0; j< 1<<(point); ++j){
                    System.out.print(val[i][j] + " ");
                }
                System.out.println();
            }*/

            System.out.format("The shortest path has length %d\n", minDist);
        }
    }

    // start -> ? ? ??? -> end -> start
    //end = the last node, mask = 0 => nodes left, 1 => node set
    static int dp (int end, int mask)
    {
        int bitSet = NumberOfSetBits(mask);
        if(bitSet == point-1){
            if((mask & 1) == 0)   //mask left only starting position not set.
                return 0;
            else
                return Integer.MAX_VALUE;
        }

        if((mask & (1 << end)) == 1) return Integer.MAX_VALUE;
        if(end == 0) return Integer.MAX_VALUE;      //end cannot be 0 as long as the size > 1

        if (vis[end][mask]) return val[end][mask];
        vis[end][mask] = true;

        int ans = Integer.MAX_VALUE;
        int cost;

        for ( int i = 0; i < point; i++ ) {
            if(i == end) continue;
            if ((mask & (1 << i)) == 0 ) {
                int temp = dp (i, mask | (1 << end));
                if(temp == Integer.MAX_VALUE) continue;
                cost = temp + dist[i][end];
                if ( ans > cost ) ans = cost;
            }
        }

        return val[end][mask] = ans;
    }

    static int NumberOfSetBits(int i)
    {
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        return (((i + (i >>> 4)) & 0x0F0F0F0F) * 0x01010101) >>> 24;
    }
}
