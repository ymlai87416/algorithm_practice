package Graph.StronglyConnectedComponents;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by Tom on 9/5/2016.
 */
public class CallingCircles {

    static Stack<Integer> S;
    static TreeMap<String, Integer> mapper;
    static String[] reverseMap;

    static void tarjanSCC(int u) {
        dfs_low[u] = dfs_num[u] = dfsNumberCounter++; // dfs_low[u] <= dfs_num[u]
        S.push(u); // stores u in a vector based on order of visitation
        visited[u] = true;
        for (int j = 0; j < adjcnt[u]; j++) {
            int v = adj[u][j];
            if (dfs_num[v] == -1)
                tarjanSCC(v);
            if (visited[v]) // condition for update
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
        }

        //the output part
        if (dfs_low[u] == dfs_num[u]) { // if this is a root (start) of an SCC
            StringBuilder sb = new StringBuilder();
            while (true) {
                int v = S.pop(); visited[v] = false;
                sb.append(reverseMap[v]); sb.append(", ");
                if (u == v) break;
            }
            System.out.print(sb.substring(0, sb.length()-2).toString() + "\n");
        }
    }

    static int[] adjcnt = new int[26];
    static int[][] adj = new int[26][26];
    static int[] dfs_num = new int[26];
    static int[] dfs_low = new int[26];
    static boolean[] visited = new boolean[26];

    static int dfsNumberCounter;
    static int numSCC;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int ntest = 0;
        while(true){
            int n = sc.nextInt();
            int m = sc.nextInt();

            if(n == 0 && m == 0) break;

            if(ntest != 0)
                System.out.println();

            mapper = new TreeMap<String, Integer>();
            reverseMap = new String[n];
            int cnt = 0;
            Arrays.fill(adjcnt, 0);
            for(int i=0; i<adj.length; ++i)
                Arrays.fill(adj[i], 0);

            for(int i=0; i<m; ++i){
                String a = sc.next();
                String b = sc.next();

                Integer aidx = mapper.get(a);
                if(aidx == null){
                    reverseMap[cnt] = a;
                    mapper.put(a, cnt++);
                }
                Integer bidx = mapper.get(b);
                if(bidx == null){
                    reverseMap[cnt] = b;
                    mapper.put(b, cnt++);
                }

                aidx = mapper.get(a);
                bidx = mapper.get(b);

                adj[aidx][adjcnt[aidx]++] = bidx;

            }

            Arrays.fill(dfs_num, -1);
            Arrays.fill(dfs_low, 0);
            Arrays.fill(visited, false);

            System.out.format("Calling circles for data set %d:\n", ++ntest);
            dfsNumberCounter = numSCC = 0;
            S = new Stack<Integer>();
            for (int i = 0; i < n; i++)
                if (dfs_num[i] == -1)
                    tarjanSCC(i);


        }
    }
}
