package Graph.DirectedAcyclicGraph.SingleSourceShortestLongestPaths;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ymlai on 14/4/2017.
 *
 * problem: https://onlinejudge.org/external/4/452.pdf
 *
 * #UVA #Lv3 #dp #dag #dag_longest_path
 */
public class UVA452 {

    static int[][] h = new int[27][27];
    static int[] dfs_num = new int[27];
    static List<List<Integer>> AdjList = new ArrayList<>();
    static String idx = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static int[] w = new int[27];

    static final int VISITED = 1;
    static final int UNVISITED = -1;
    static final int INF = Integer.MAX_VALUE;

    static int[] d = new int[27];
    //static int[] p = new int[10001];
    static boolean[] visited = new boolean[27];

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

    public static void main(String[] args) throws Exception{
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        int nc = Integer.parseInt(sc.readLine());

        sc.readLine();
        for(int i=0; i<27; ++i){
            AdjList.add(new ArrayList<>());
        }

        for(int c = 0; c<nc; ++c){
            String line = sc.readLine();

            for(int i=0; i<27; ++i){
                AdjList.get(i).clear();
            }

            Arrays.fill(w, INF);
            while(line != null && line.trim().length() != 0){
                String[] token = line.split(" ");

                int taskIdx = idx.indexOf(token[0].trim().charAt(0));
                w[taskIdx] = Integer.parseInt(token[1]);

                if(token.length == 3){
                    token[2] = token[2].trim();
                    for(int i=0; i<token[2].length(); ++i){
                        AdjList.get(taskIdx).add(idx.indexOf(token[2].charAt(i)));
                    }
                }

                line = sc.readLine();
            }

            ts.clear();
            Arrays.fill(dfs_num, UNVISITED);
            for (int i = 0; i < 27; i++) // this part is the same as finding CCs
            {
                if (w[i] == INF)
                    continue;
                if (dfs_num[i] == UNVISITED)
                    dfs2(i);
            }

            /*
            for (int i = (int)ts.size() - 1; i >= 0; i--) // read backwards
                System.out.format(" %d", ts.get(i));
            System.out.print("\n");
            */

            //find the longest path from the DAG
            Arrays.fill(d, INF);

            for(int i=ts.size()-1; i>=0; --i){
                int u = ts.get(i);
                //Arrays.fill(p, -1);
                if(d[u] == INF)
                    d[u] = -w[u];

                int min = 0;
                for(int j=i; j>=0; --j){
                    u = ts.get(j);
                    for(int k=0; k<AdjList.get(u).size(); ++k){
                        int v = AdjList.get(u).get(k);

                        if(d[v] > d[u]-w[v]){
                            d[v] = d[u]-w[v];
                            //p[v] = u;
                            min= Math.min(min, d[v]);
                        }
                    }
                }
            }

            int maxLen = d[0];
            for(int i=1; i<27; ++i){
                maxLen = Math.min(maxLen, d[i]);
            }

            System.out.println(-maxLen);
            if(c != nc-1)
                System.out.println();
        }
    }

}
