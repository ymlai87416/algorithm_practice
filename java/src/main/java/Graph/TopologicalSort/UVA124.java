package Graph.TopologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

/**
 * Created by Tom on 9/5/2016.
 *
 * problem: https://onlinejudge.org/external/1/124.pdf
 *
 * #UVA #topological_sort #Lv2
 */

public class UVA124 {

    //uva11060
    int[] adjcnt = new int[101];
    int[][] adj = new int[101][101];
    int[] visited = new int[101];
    int[] indegree = new int[101];
    ArrayList<Integer> ts;

    ArrayList<Integer> Kahn(int V) {
        //this either sort all, or fail to sort any.
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        ArrayList<Integer> answer = new ArrayList<Integer>();

        for(int i=0; i<V; ++i)
            if(indegree[i] == 0){
                queue.offer(i);
                indegree[i]--;
            }
        while(!queue.isEmpty()){
            int u= queue.poll();

            for(int j=0; j<adjcnt[u]; ++j){
                indegree[adj[u][j]]--;
            }

            //check for indegree = 0, speed up avoid scanning from 0...V
            for(int i=0; i<adjcnt[u]; ++i){
                int v = adj[u][i];
                if(indegree[v] == 0) {
                    queue.offer(v);
                    indegree[v]--;
                }
            }
            answer.add(u);
        }
        return answer;
    }

     void Kahn2(int V, Stack<Integer> path, ArrayList<ArrayList<Integer>> result) {
        //this either sort all, or fail to sort any.
        ArrayList<Integer> next = new ArrayList<>();

         for(int i=0; i<V; ++i)
             if(indegree[i] == 0){
                 next.add(i);
             }

        if(next.size() == 0) {
            result.add(new ArrayList<>(path));
        }

        for(int u: next){
            for(int j=0; j<adjcnt[u]; ++j){
                indegree[adj[u][j]]--;
            }
            path.push(u);
            indegree[u]--;  //avoid re-use

            Kahn2(V, path, result);

            for(int j=0; j<adjcnt[u]; ++j){
                indegree[adj[u][j]]++;
            }
            path.pop();
            indegree[u]++;
        }

    }

    /*
    answer is ts, cannot use if all requirement must be satisfied before proceed.
    */

    void dfs2(int u){
        visited[u] = 1;
        Arrays.sort(adj[u], 0, adjcnt[u]);
        for (int j = adjcnt[u]-1; j >=0 ; j--) {
            int v = adj[u][j];
            if (visited[v] == 0)
                dfs2(v);
        }
        ts.add(u);
    }

    private void run() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nc = 0;
        while(true) {
            HashMap<String, Integer> mapper = new HashMap<>();
            ArrayList<String> mapper2 = new ArrayList<>();
            String aline = br.readLine();
            if(aline == null) return;
            String bline = br.readLine();
            for (int i = 0; i < adj.length ; i++) {
                Arrays.fill(adj[i], 0);
            }
            Arrays.fill(adjcnt, 0);
            Arrays.fill(indegree, 0);
            Arrays.fill(visited, 0);

            StringTokenizer st = new StringTokenizer(aline);
            int cnt = 0;
            while(st.hasMoreTokens()){
                String tt = st.nextToken();
                mapper.put(tt, cnt);
                mapper2.add(tt);
                ++cnt;
            }

            st = new StringTokenizer(bline);

            while(st.hasMoreTokens()){
                String t1 = st.nextToken();
                String t2 = st.nextToken();
                int i1 = mapper.get(t1);
                int i2 = mapper.get(t2);
                adj[i1][adjcnt[i1]++] = i2;
                indegree[i2]++;
            }

            ArrayList<ArrayList<Integer>> result = new ArrayList<>();
            Kahn2(cnt, new Stack<>(), result);

            ArrayList<String> resultStr = new ArrayList<>();
            for(ArrayList<Integer> r: result){
                String t = "";
                for(Integer ii: r){
                    t = t+mapper2.get(ii);
                }

                resultStr.add(t);
            }

            if(nc > 0) System.out.println();
            Collections.sort(resultStr);
            for (int j = 0; j < resultStr.size(); j++) {
                System.out.println(resultStr.get(j));
            }
            ++nc;
        }
    }

    public static void main(String[] args) throws IOException {
        UVA124 sol = new UVA124();
        sol.run();
    }
}
