package Graph.DirectedAcyclicGraph.CoutingPathsInDAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ymlai on 14/4/2017.
 *
 * problem: https://onlinejudge.org/external/119/11957.pdf
 *
 * #UVA #Lv4 #dp #dag #dag_count_paths
 *
 */
public class UVA11957 {

    static List<List<Integer>> AdjList = new ArrayList<>();
    static char[][] map = new char[101][101];

    static final int VISITED = 1;
    static final int UNVISITED = -1;
    static int[] dfs_num;

    static List<Integer> ts = new ArrayList<Integer>(); // global vector to store the toposort in reverse order
    static void dfs2(int u) { // different function name compared to the original dfs
        dfs_num[u] = VISITED;
        for (int j = 0; j < AdjList.get(u).size(); j++) {
            int v = AdjList.get(u).get(j);
            if (dfs_num[v] == UNVISITED)
                dfs2(v);
        }
        ts.add(u);
    }

    static int[] dx = {1, 1};
    static int[] dy = {1, -1};

    static boolean isOnBoard(int x, int y, int n){
        if(x < 0 || x >= n) return false;
        if(y < 0 || y >= n) return false;
        return true;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int i=0; i<100*100; ++i)
            AdjList.add(new ArrayList<>());

        for(int c=0; c<nc; ++c){
            int n = sc.nextInt();

            int wx, wy;
            wx=0; wy=0;

            for(int p=n-1; p>=0; --p){
                String line = sc.next();
                for(int q=0; q<n; ++q){
                    if(line.charAt(q) == 'W'){
                        wx = p;
                        wy = q;
                    }
                    map[p][q] = line.charAt(q);
                }
            }

            for(int i=0; i<n*n; ++i)
                AdjList.get(i).clear();

            for(int i=0; i<n; ++i){
                for(int j=0; j<n; ++j){
                    for(int k=0; k<2; ++k){
                        int ni = i+dx[k];
                        int nj = j+dy[k];

                        if(!isOnBoard(ni, nj, n)) continue;

                        if(map[ni][nj] == 'B') {
                            ni = ni+dx[k];
                            nj = nj+dy[k];
                            if(isOnBoard(ni, nj, n) && map[ni][nj] != 'B')
                                AdjList.get(i*n+j).add(ni*n+nj);
                        }
                        else
                            AdjList.get(i*n+j).add(ni*n+nj);
                    }
                }
            }

            dfs_num = new int[n*n];
            ts.clear();
            Arrays.fill(dfs_num, UNVISITED);
            dfs2(wx*n+wy);

            /*
            for (int i = (int)ts.size() - 1; i >= 0; i--) // read backwards
                System.out.format(" %d", ts.get(i));
            System.out.print("\n");
            */

            int[] numPath = new int[n*n];
            Arrays.fill(numPath, 0);
            numPath[wx*n+wy]=1;

            for(int i=ts.size()-1; i>=0; --i){
                int u= ts.get(i);
                for(int j=0; j<AdjList.get(u).size(); ++j){
                    int v = AdjList.get(u).get(j);
                    numPath[v] = (numPath[v] + numPath[u]) % 1000007;
                }
            }

            long sum = 0;
            for(int i=(n-1)*n; i<n*n; ++i){
                sum = (sum + numPath[i]) % 1000007;
            }

            System.out.format("Case %d: %d\n", c+1, sum);

        }
    }

}
