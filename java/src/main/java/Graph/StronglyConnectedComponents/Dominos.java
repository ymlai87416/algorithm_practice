package Graph.StronglyConnectedComponents;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Tom on 9/5/2016.
 *
 * TLE, change buffer reader please...
 */
public class Dominos {

    static Stack<Integer> S;
    static ArrayList<Integer>[] adj = new ArrayList[100001];
    static ArrayList<Integer>[] adjInv = new ArrayList[100001];
    static int[] dfs_num = new int[100001];
    static int[] dfs_low = new int[100001];
    static boolean[] visited = new boolean[100001];

    static int dfsNumberCounter;
    static int numSCC;

    //static List<List<Integer>> SCCList;
    static int result;

    static void tarjanSCC(int u) {
        dfs_low[u] = dfs_num[u] = dfsNumberCounter++; // dfs_low[u] <= dfs_num[u]
        S.push(u); // stores u in a vector based on order of visitation
        visited[u] = true;
        for (int j = 0; j < adj[u].size(); j++) {
            int v = adj[u].get(j);
            if (dfs_num[v] == -1)
                tarjanSCC(v);
            if (visited[v]) // condition for update
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
        }

        //the output part
        if (dfs_low[u] == dfs_num[u]) { // if this is a root (start) of an SCC
            numSCC++;
            //System.out.format("SCC %d:\n", numSCC);
            Set<Integer> scc = new TreeSet<Integer>();
            while (true) {
                int v = S.pop(); visited[v] = false;
                //System.out.format(" %d", v);  //this is to output the SCC
                scc.add(v);
                if (u == v) break;
            }

            boolean inEdge = false;
            for(Integer ele: scc){
                for(Integer outE: adjInv[ele]){
                    if(!scc.contains(outE)) {
                        inEdge = true;
                        break;
                    }
                }
            }
            if(!inEdge)
                result++;
            //System.out.println();
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        int nc = Integer.parseInt(sc.readLine());

        for(int i=0; i<adj.length; ++i) {
            adj[i] = new ArrayList<Integer>();
            adjInv[i] = new ArrayList<Integer>();
        }

        for(int ci=0; ci<nc; ++ci){
            String[] s = sc.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);

            for(int i=0; i<n; ++i){
                adjInv[i].clear();
                adj[i].clear();
            }

            for(int i=0; i<m; ++i){
                s = sc.readLine().split(" ");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);

                adj[a-1].add(b-1);
                adjInv[b-1].add(a-1);
            }

            Arrays.fill(dfs_num, -1);
            Arrays.fill(dfs_low, 0);
            Arrays.fill(visited, false);

            dfsNumberCounter = numSCC = 0;
            S = new Stack<Integer>();
            result = 0;
            for (int i = 0; i < n; i++)
                if (dfs_num[i] == -1)
                    tarjanSCC(i);

            if(result == 0) result = 1;
            System.out.println(result);
        }
    }


}
