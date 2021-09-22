package Graph.GraphTraversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Tom on 25/4/2016.
 *
 * stap at 07, total time waste 4 minutes.
 *
 * problem: https://onlinejudge.org/external/124/12442.pdf
 *
 * #UVA #Lv3 #graph_traversal #skip
 */

public class UVA12442 {

    static int[] adjcnt = new int[50001];
    static int[][] adj  = new int[50001][2];
    //static ArrayList<Integer>[] adj = new ArrayList[50001];
    static boolean[] visited = new boolean[50001];

    static int[] dp = new int[50001];

    static int dfs(int s, int depth){
        Stack<Integer> st = new Stack<Integer>();
        visited[s]  =true;
        st.add(s);

        int maxd = 0;
        while(!st.empty()){
            ++maxd;
            int u = st.pop();
            for(int j=0; j<adjcnt[u]; ++j){
                int v = adj[u][j];
                if(dp[v] != -1) return maxd+dp[v];
                if(!visited[v]) {
                    visited[v] = true;
                    st.add(v);
                }
            }
        }

        return dp[s] = maxd;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();


        for(int i=0; i<nt; ++i){
            int ncomm = sc.nextInt();

            Arrays.fill(visited, false);
            Arrays.fill(adjcnt, 0);
            /*for(int j=0; j<ncomm; ++j) {
                Arrays.fill(adj[j], 0);
            }*/

            for(int j=0; j<ncomm; ++j){
                int u = sc.nextInt();
                int v = sc.nextInt();
                adj[u-1][adjcnt[u-1]++] = (v-1);
            }

            Arrays.fill(dp, -1);

            int ans = Integer.MIN_VALUE;
            int to = 1;
            for(int j=0; j<ncomm; ++j){
                Arrays.fill(visited, false);
                int dd = dfs(j, 0);
                if(dd > ans){
                    ans = dd;
                    to = j;
                }
            }
            System.out.format("Case %d: %d\n", i+1, to+1);
        }
    }
}
