package Graph.TopologicalSort;

import java.util.*;

/**
 * Created by Tom on 1/5/2016.
 *
 * Using DFS can only give you one combination, but I need to generate all possible sort sequence.
 *
 * #skip
 */
public class UVA872WA {

    static int[] adjcnt;
    static int[][] adj;
    static ArrayList<Integer> ts;
    static int[] dfs_num;

    static void dfs2(int u) { // different function name compared to the original dfs
        dfs_num[u] = 1;
        for (int j = 0; j < adjcnt[u]; j++) {
            int v = adj[u][j];
            if (dfs_num[v] == 0)
                dfs2(v);
        }
        ts.add(u);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
        char[] reverse = new char[51];

        int nc = sc.nextInt();
        sc.nextLine();

        adjcnt= new int[51];
        adj = new int[51][51];
        dfs_num = new int[51];

        for(int ci=0; ci<nc; ++ci){

            Arrays.fill(adjcnt, 0);
            for(int i=0; i<50; ++i){
                Arrays.fill(adj[i], 0);
            }

            ts = new ArrayList<Integer>();

            int cnt = 0;
            String nodes = sc.nextLine();
            StringTokenizer st = new StringTokenizer(nodes);
            while(st.hasMoreTokens()){
                String t = st.nextToken();
                map.put(t.charAt(0), cnt);
                reverse[cnt] = t.charAt(0);
                cnt++;
            }

            String order = sc.nextLine();
            st = new StringTokenizer(order);
            while(st.hasMoreTokens()){
                String o = st.nextToken();
                char cs= o.charAt(0);
                char ct = o.charAt(2);
                int s = map.get(cs);
                int t = map.get(ct);
                adj[s][adjcnt[s]++] = t;
            }
        }

        ts.clear();
        Arrays.fill(dfs_num, 0);
        for (int i = 0; i < map.size(); i++)
            if (dfs_num[i] == 0)
                dfs2(i);
        for (int i = ts.size() - 1; i >= 0; i--)
            System.out.format(" %s", reverse[ts.get(i)]);
        System.out.println("\n");
    }
}

