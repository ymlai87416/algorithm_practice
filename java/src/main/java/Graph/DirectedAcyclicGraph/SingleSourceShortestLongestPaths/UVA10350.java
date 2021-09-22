package Graph.DirectedAcyclicGraph.SingleSourceShortestLongestPaths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ymlai on 14/4/2017.
 *
 * problem: https://onlinejudge.org/external/103/10350.pdf
 *
 * #UVA #Lv4 #dp #dag #dag_longest_path
 */
public class UVA10350 {

    static int[] dfs_num = new int[1801];
    static List<List<Integer>> AdjList = new ArrayList<>();
    static int[][] t = new int[1801][1801];

    static final int VISITED = 1;
    static final int UNVISITED = -1;
    static final int INF = Integer.MAX_VALUE;

    static int[] d = new int[1801];
    //static int[] p = new int[10001];
    static boolean[] visited = new boolean[1801];

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

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<1800; ++i)
            AdjList.add(new ArrayList<>());

        while(sc.hasNext()){
            String caseName =sc.next();

            int n = sc.nextInt();
            int m = sc.nextInt();

            for(int i=0; i<n*m; ++i) {
                AdjList.get(i).clear();
            }

            for(int i=0; i<n-1; ++i){
                for(int j=0; j<m; ++j){
                    int u = i*m+j;
                    for(int k=0; k<m; ++k){
                        int v = (i+1)*m+k;
                        AdjList.get(u).add(v);
                        t[u][v] = sc.nextInt();
                    }
                }
            }

            ts.clear();
            Arrays.fill(dfs_num, UNVISITED);
            for (int i = 0; i < m; i++) // this part is the same as finding CCs
                if (dfs_num[i] == UNVISITED)
                    dfs2(i);

            /*
            for (int i = (int)ts.size() - 1; i >= 0; i--) // read backwards
                System.out.format(" %d", ts.get(i));
            System.out.print("\n");
            */

            //find the shortest path from the DAG
            Arrays.fill(d, INF);
            for(int i=0; i<m; ++i)  //source are hole in ground floor
                d[i] = 0;

            for(int i=ts.size()-1; i>=ts.size()-1-m; --i){  //only first m elements in DAG needed to be processed
                for(int j=i; j>=0; --j){
                    int u = ts.get(j);
                    for(int k=0; k<AdjList.get(u).size(); ++k){
                        int v = AdjList.get(u).get(k);

                        if(d[v] > d[u]+t[u][v]+2){
                            d[v] = d[u]+t[u][v]+2;
                            //p[v] = u;
                        }
                    }
                }
            }

            int minLen = Integer.MAX_VALUE;
            for(int i=(n-1)*m; i<n*m; ++i){
                minLen = Math.min(minLen, d[i]);
            }

            System.out.format("%s\n%d\n", caseName, minLen);
        }
    }
}
