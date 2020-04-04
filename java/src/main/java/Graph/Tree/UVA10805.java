package Graph.Tree;

import java.util.*;

/**
 * Created by ymlai on 12/4/2017.
 *
 * iterative depth first means nothing, it just choose path beside a tree
 *
 * "In every tree there exists a point (possibly in the ‘middle’ of some edge) such that the distance of the furthest vertex from it is exactly half the diameter of the tree."
 */
public class UVA10805 {
    static int[][] AdjMat = new int[2760][2760];
    static List<List<Integer>> AdjList2 = new ArrayList<>();
    static int INF = Integer.MAX_VALUE;

    static class Pair{
        int first;
        int second;
        public Pair(int a, int b){
            first = a;
            second = b;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int p=0; p<nc; ++p){
            int n = sc.nextInt();
            int m = sc.nextInt();

            for(int i=0; i<n+m;++i) {
                Arrays.fill(AdjMat[i], INF);
            }

            for(int j=0; j<m; ++j) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                AdjMat[a][n+j] = 1;
                AdjMat[n+j][a] = 1;
                AdjMat[b][n+j] = 1;
                AdjMat[n+j][b] = 1;
            }

            for (int k = 0; k < n+m; k++) // remember that loop order is k->i->j
                for (int i = 0; i < n+m; i++)
                    for (int j = 0; j < n+m; j++)
                        AdjMat[i][j] = Math.min(AdjMat[i][j], capAdd(AdjMat[i][k], AdjMat[k][j]));

            int solution = Integer.MAX_VALUE;
            for (int i = 0; i < n+m; i++) {  //edge node or origin node -> origin node only
                int first = 0, second = 0;
                for (int j = 0; j < n; j++)
                    if (AdjMat[i][j] >= first) {
                        second = first;
                        first = AdjMat[i][j];
                    }
                    else if (AdjMat[i][j] > second)
                        second = AdjMat[i][j];

                solution = Math.min(solution, first + second);
            }

            System.out.format("Case #%d:\n", p+1);
            System.out.println(solution/2);
            System.out.println();
        }
    }

    static int capAdd(int a, int b){
        if(a  == INF || b == INF) return INF;
        return a+b;
    }
}
