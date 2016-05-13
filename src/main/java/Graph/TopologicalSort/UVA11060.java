package Graph.TopologicalSort;

import java.util.*;

/**
 * Created by Tom on 2/5/2016.
 */
public class UVA11060 {

    static int[] adjcnt;
    static int[][] adj;
    static int[] visited;
    static int[] indegree;

    static TreeMap<String, Integer> mapper = new TreeMap<String, Integer>();
    static String[] reverse = new String[101];

    static ArrayList<Integer> ts;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        adjcnt = new int[101];
        adj = new int[101][101];
        visited = new int[101];
        indegree = new int[101];

        int nc = 0;
        while(true){
            if(!sc.hasNext()) break;

            Arrays.fill(adjcnt, 0);
            Arrays.fill(visited, 0);
            Arrays.fill(indegree, 0);
            mapper.clear();

            int nd = sc.nextInt();
            sc.nextLine();
            for(int i=0; i<nd; ++i){
                String d = sc.nextLine();
                mapper.put(d, i);
                reverse[i] = d;
            }
            int nr = sc.nextInt();
            sc.nextLine();
            for(int i=0; i<nr; ++i){
                String rule = sc.nextLine();
                StringTokenizer st = new StringTokenizer(rule);
                String a = st.nextToken();
                String b = st.nextToken();
                int ia = mapper.get(a);
                int ib = mapper.get(b);
                adj[ia][adjcnt[ia]++] = ib;
                indegree[ib]++;
            }

            /*ts = new ArrayList<Integer>();
            for(int i=nd-1; i>=0; --i){
                if (visited[i] == 0)
                    dfs2(i);
            }*/

            ArrayList<Integer> result = Kahn(nd);

            System.out.format("Case #%d: Dilbert should drink beverages in this order:", ++nc);
            for(int i=0; i < result.size(); ++i) {
                System.out.print(" ");
                System.out.print(reverse[result.get(i)]);
            }
            System.out.println(".\n");
        }
    }

    static ArrayList<Integer> Kahn(int V) {
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

    /*This cannot handle the following test case
        5
        A
        B
        C
        D
        E
        5
        C A
        D A
        B D
        E B
        E C
    */
    static void dfs2(int u){
        visited[u] = 1;
        Arrays.sort(adj[u], 0, adjcnt[u]);
        for (int j = adjcnt[u]-1; j >=0 ; j--) {
            int v = adj[u][j];
            if (visited[v] == 0)
                dfs2(v);
        }
        ts.add(u);
    }

}
