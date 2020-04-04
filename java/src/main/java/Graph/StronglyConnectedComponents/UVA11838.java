package Graph.StronglyConnectedComponents;

import java.util.*;

/**
 * Created by ymlai on 12/4/2017.
 */
public class UVA11838 {

    static class Pair{
        public int first;
        public int second;
        public Pair(int a, int b){
            first = a; second = b;
        }
    }
    static Stack<Integer> S;
    static int dfsNumberCounter;
    static int[] dfs_low;
    static boolean[] visited;
    static int[] dfs_num;
    static List<List<Pair>> AdjList;
    static int numSCC;
    final static int UNVISITED = -1;

    static void tarjanSCC(int u) {
        dfs_low[u] = dfs_num[u] = dfsNumberCounter++; // dfs_low[u] <= dfs_num[u]
        S.push(u); // stores u in a vector based on order of visitation
        visited[u] = true;
        for (int j = 0; j < (int)AdjList.get(u).size(); j++) {
            Pair v = AdjList.get(u).get(j);
            if (dfs_num[v.first] == UNVISITED)
                tarjanSCC(v.first);
            if (visited[v.first]) // condition for update
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[v.first]); }
        if (dfs_low[u] == dfs_num[u]) { // if this is a root (start) of an SCC
            ++numSCC;
            //System.out.format("SCC %d:", numSCC); // this part is done after recursion
            while (true) {
                int v = S.pop(); visited[v] = false;
                //System.out.format(" %d", v);
                if (u == v) break; }
            //System.out.print("\n");
        }
    }

    public static void main(String[] args){
        Scanner sc =  new Scanner(System.in);

        while(true){
            int n = sc.nextInt();
            int m = sc.nextInt();

            if(n == 0 && m == 0) break;

            dfs_low = new int[n];
            visited = new boolean[n];
            dfs_num = new int[n];
            AdjList = new ArrayList<>();
            for(int i=0; i<n; ++i)
                AdjList.add(new ArrayList<>());

            for(int i=0; i<m; ++i){
                int v = sc.nextInt();
                int w = sc.nextInt();
                int p = sc.nextInt();

                v--; w--;
                if(p == 1){
                    AdjList.get(v).add(new Pair(w, 0));
                }
                else if(p == 2){
                    AdjList.get(v).add(new Pair(w, 0));
                    AdjList.get(w).add(new Pair(v, 0));
                }
            }

            Arrays.fill(dfs_num, UNVISITED);
            Arrays.fill(dfs_low, 0);
            Arrays.fill(visited, false);
            dfsNumberCounter = numSCC = 0;
            S = new Stack<>();
            for (int i = 0; i < n; i++)
                if (dfs_num[i] == UNVISITED)
                    tarjanSCC(i);

            if(numSCC == 1)
                System.out.println(1);
            else
                System.out.println(0);
        }

    }
}
