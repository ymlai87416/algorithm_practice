package Graph.DirectedAcyclicGraph.SingleSourceShortestLongestPaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by ymlai on 14/4/2017.
 *
 * 10000 X 10000 solution too slow for 3s.
 *
 * problem: 10285
 *
 * #UVA #Lv2 #dp #dag #dag_longest_path
 */
public class UVA10285 {
    static int[][] h = new int[100][100];
    static int[] dfs_num = new int[10001];
    static List<List<Integer>> AdjList = new ArrayList<>();

    static final int VISITED = 1;
    static final int UNVISITED = -1;
    static final int INF = Integer.MAX_VALUE;

    static int[] d = new int[10001];
    //static int[] p = new int[10001];
    static boolean[] visited = new boolean[10001];

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

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        int nc = Integer.parseInt(sc.readLine());

        for(int i=0; i<10001; ++i)
            AdjList.add(new ArrayList<>());

        for(int c=0; c<nc; ++c){
            String line = sc.readLine();
            String[] token = line.split(" ");
            String place = token[0];
            int R = Integer.parseInt(token[1]);
            int C = Integer.parseInt(token[2]);

            for(int i=0; i<R; ++i){
                line = sc.readLine();
                token = line.split(" ");
                for(int j=0; j<C; ++j){
                    h[i][j] = Integer.parseInt(token[j]);
                }
            }

            int[] dx = {0, -1, 1, 0};
            int[] dy = {-1, 0, 0, 1};

            for(int i=0; i<R; ++i){
                for(int j=0; j<C; ++j){
                    AdjList.get(i*C+j).clear();

                    for(int k=0; k<4; ++k){
                        int nR = i+dx[k];
                        int nC = j+dy[k];

                        if(nR < 0 || nR >= R)
                            continue;
                        if(nC < 0 || nC >= C)
                            continue;

                        if(h[nR][nC] < h[i][j])
                            AdjList.get(i*C+j).add(nR*C+nC);
                    }
                }
            }

            // inside int main()
            ts.clear();
            Arrays.fill(dfs_num, UNVISITED);
            for (int i = 0; i < R*C; i++) // this part is the same as finding CCs
                if (dfs_num[i] == UNVISITED)
                    dfs2(i);
            // alternative, call: reverse(ts.begin(), ts.end()); first

            /*
            for (int i = (int)ts.size() - 1; i >= 0; i--) // read backwards
                System.out.format(" %d", ts.get(i));
            System.out.print("\n");
            */

            //Collections.reverse(ts);

            //find the longest path from the DAG
            int maxLen = 0;
            Arrays.fill(visited, false);

            for(int i=ts.size()-1; i>=0; --i){
                if(visited[ts.get(i)]) continue;

                Arrays.fill(d, INF);
                int u = ts.get(i);
                //Arrays.fill(p, -1);
                d[u] = 0;
                visited[u] = true;

                int min = 0;
                for(int j=i; j>=0; --j){
                    u = ts.get(j);
                    for(int k=0; k<AdjList.get(u).size(); ++k){
                        int v = AdjList.get(u).get(k);

                        if(d[v] > d[u]-1){
                            d[v] = d[u]-1;
                            visited[v] = true;
                            //p[v] = u;
                            min= Math.min(min, d[v]);
                        }
                    }
                }

                min = -min;
                if(min > maxLen)
                    maxLen = min;
            }

            System.out.format("%s: %d\n", place, maxLen+1);
        }
    }
}
