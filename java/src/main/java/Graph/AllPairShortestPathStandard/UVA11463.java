package Graph.AllPairShortestPathStandard;

import java.util.Arrays;
import java.util.Scanner;

/**
 problem: https://onlinejudge.org/external/114/11463.pdf
 level:
 solution: O(n^3)

 #AllPairShortestPath #Floyd

 **/

public class UVA11463 {

    static int[][] AdjMat = new int[101][101];
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int ci=0; ci<nc; ++ci){
            for(int i=0; i<AdjMat.length; ++i) {
                Arrays.fill(AdjMat[i], INF);
                AdjMat[i][i] = 0;
            }
            int V = sc.nextInt();

            int nr = sc.nextInt();

            for(int i=0; i<nr; ++i){
                int a = sc.nextInt();
                int b = sc.nextInt();

                AdjMat[a][b] = 1;  AdjMat[b][a] = 1;
            }

            // inside int main()
            // precondition: AdjMat[i][j] contains the weight of edge (i, j)
            // or INF (1B) if there is no such edge
            // AdjMat is a 32-bit signed integer array
            for (int k = 0; k < V; k++) // remember that loop order is k->i->j
                for (int i = 0; i < V; i++)
                    for (int j = 0; j < V; j++)
                        AdjMat[i][j] = Math.min(AdjMat[i][j], capAdd(AdjMat[i][k], AdjMat[k][j]));
            int s = sc.nextInt();
            int t = sc.nextInt();

            int maxdist = Integer.MIN_VALUE;
            for(int i=0; i<V; ++i){
                maxdist = Math.max(maxdist, AdjMat[s][i] + AdjMat[i][t]);
            }

            System.out.format("Case %d: %d\n", ci+1, maxdist);
        }
    }

    static int capAdd(int a, int b){
        if(a  == INF || b == INF) return INF;
        return a+b;
    }
}
