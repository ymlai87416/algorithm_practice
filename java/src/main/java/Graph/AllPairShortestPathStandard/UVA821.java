package Graph.AllPairShortestPathStandard;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

/**
 problem: https://onlinejudge.org/external/8/821.pdf
 level:
 solution: O(n^3)

 #AllPairShortestPath #Floyd

 **/



public class UVA821 {

    static int[][] AdjMat = new int[101][101];
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int ci = 0;
        while(true){
            TreeMap<Integer, Integer> map = new TreeMap();
            int V = 0;

            for(int i=0; i<AdjMat.length; ++i){
                Arrays.fill(AdjMat[i], INF);
                AdjMat[i][i] = 0;
            }
            while(true){
                int a = sc.nextInt();
                int b = sc.nextInt();

                if(a == 0 && b == 0) break;

                Integer aidx = map.get(a); if(aidx == null) map.put(a, V++);
                Integer bidx = map.get(b); if(bidx == null) map.put(b, V++);

                aidx = map.get(a);
                bidx = map.get(b);

                AdjMat[aidx][bidx] = 1;
            }

            if(V == 0) break;

            // inside int main()
            // precondition: AdjMat[i][j] contains the weight of edge (i, j)
            // or INF (1B) if there is no such edge
            // AdjMat is a 32-bit signed integer array
            for (int k = 0; k < V; k++) // remember that loop order is k->i->j
                for (int i = 0; i < V; i++)
                    for (int j = 0; j < V; j++)
                        AdjMat[i][j] = Math.min(AdjMat[i][j], capAdd(AdjMat[i][k], AdjMat[k][j]));


            long sum = 0;
            for(int i=0; i<V; ++i){
                for(int j=0; j<V; ++j){
                    if(i== j) continue;
                    sum += AdjMat[i][j];
                }
            }
            System.out.format("Case %d: average length between pages = %.3f clicks\n", ++ci, sum*1.0/(V*V-V));
        }
    }

    static int capAdd(int a, int b){
        if(a  == INF || b == INF) return INF;
        return a+b;
    }
}
