package Graph.StronglyConnectedComponents;

import java.util.*;

/**
 * Created by Tom on 9/5/2016.
 */
public class Dominos {

    static Stack<Integer> S;
    static ArrayList<Integer>[] adj = new ArrayList[100001];
    static int[] dfs_num = new int[100001];
    static int[] dfs_low = new int[100001];
    static boolean[] visited = new boolean[100001];

    static int dfsNumberCounter;
    static int numSCC;

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
            while (true) {
                int v = S.pop(); visited[v] = false;
                if (u == v) break;
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();
        for(int i=0; i<adj.length; ++i)
            adj[i] = new ArrayList<Integer>();

        for(int ci=0; ci<nc; ++ci){
            int n = sc.nextInt();
            int m = sc.nextInt();

            for(int i=0; i<n; ++i){
                adj[i].clear();
            }

            for(int i=0; i<m; ++i){
                int a = sc.nextInt();
                int b = sc.nextInt();

                adj[a].add(b);
            }

            Arrays.fill(dfs_num, -1);
            Arrays.fill(dfs_low, 0);
            Arrays.fill(visited, false);

            dfsNumberCounter = numSCC = 0;
            S = new Stack<Integer>();
            for (int i = 1; i < n; i++)
                if (dfs_num[i] == -1)
                    tarjanSCC(i);

            System.out.println(numSCC);
        }
    }
}
