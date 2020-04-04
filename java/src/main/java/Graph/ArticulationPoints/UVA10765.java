package Graph.ArticulationPoints;

import java.io.FileInputStream;
import java.util.*;

/**
 * Created by ymlai on 12/4/2017.
 */
public class UVA10765{
    static class Pair implements Comparable<Pair>{                 //in dfs: v is a (neighbor, weight) pair
        public int first;
        public int second;

        public Pair(int a, int b){
            first = a; second = b;
        }

        @Override
        public int compareTo(Pair o) {
            if(first < o.first)
                return -1;
            else if(first > o.first)
                return 1;
            else{
                if(second < o.second)
                    return -1;
                else if(second > o.second)
                    return 1;
                else return 0;
            }
        }
    }

    static int[] dfs_num;
    static int[] dfs_low;
    static int[] dfs_parent;
    static boolean[] articulation_vertex;
    static int dfsNumberCounter;
    static List<List<Pair>> AdjList;
    static int dfsRoot = 0;
    static int rootChildren = 0;
    static List<Pair> bridges;

    final static int UNVISITED = -1;

    static void articulationPointAndBridge(int u) {
        dfs_low[u] = dfs_num[u] = dfsNumberCounter++; // dfs_low[u] <= dfs_num[u]
        for (int j = 0; j < (int)AdjList.get(u).size(); j++) {
            Pair v = AdjList.get(u).get(j);
            if (dfs_num[v.first] == UNVISITED) { // a tree edge
                dfs_parent[v.first] = u;
                if (u == dfsRoot) rootChildren++; // special case if u is a root
                articulationPointAndBridge(v.first);
                if (dfs_low[v.first] >= dfs_num[u]) // for articulation point
                    articulation_vertex[u] = true; // store this information first
                //if (dfs_low[v.first] > dfs_num[u]) // for bridge
                //    bridges.add(new Pair(Math.min(u, v.first), Math.max(u, v.first)));
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[v.first]); // update dfs_low[u]
            }
            else if (v.first != dfs_parent[u]) // a back edge and not direct cycle
                dfs_low[u] = Math.min(dfs_low[u], dfs_num[v.first]); // update dfs_low[u]
        }
    }

    static boolean[] visited;

    static int dfs(int V, int s){
        visited = new boolean[V];
        for(int i=0; i<V; ++i)
            visited[i] = false;

        if(s != -1)
            visited[s] = true;

        int nRoot = 0;
        for(int i=0; i<V; ++i){
            if(!visited[i]){
                nRoot++;
                Stack<Integer> st = new Stack<Integer>();
                visited[i]  =true;
                st.add(i);

                while(!st.empty()){
                    int u = st.pop();
                    for(int j=0; j<AdjList.get(u).size(); ++j){
                        Pair v = AdjList.get(u).get(j);
                        if(!visited[v.first]) {
                            visited[v.first] = true;
                            st.add(v.first);
                        }
                    }
                }
            }
        }

        return nRoot;
    }

    public static void main(String[] args){
        if(args.length >= 1)
            try {
                System.setIn(new FileInputStream(args[0]));
            }catch (Exception ex){System.out.println("cannot set" + ex.getMessage());};

        Scanner sc = new Scanner(System.in);

        while(true){
            int V, m;
            V = sc.nextInt();
            m = sc.nextInt();

            if(V == 0 && m == 0)
                break;

            dfsNumberCounter = 0;
            AdjList = new ArrayList<>();
            dfs_num = new int[V];
            dfs_low = new int[V];
            dfs_parent = new int[V];
            articulation_vertex = new boolean[V];
            for(int i=0; i<V; ++i){
                dfs_num[i] = UNVISITED;
                dfs_low[i] = 0;
                dfs_parent[i] = 0;
                articulation_vertex[i] = false;
                AdjList.add(new ArrayList<>());
            }

            while(true){
                int a, b;
                a = sc.nextInt();
                b = sc.nextInt();

                if(a == -1 && b == -1) break;

                AdjList.get(a).add(new Pair(b, 0));
                AdjList.get(b).add(new Pair(a, 0));
            }

            for (int i = 0; i < V; i++)
                if (dfs_num[i] == -1) {
                    dfsRoot = i; rootChildren = 0;
                    articulationPointAndBridge(i);
                    articulation_vertex[dfsRoot] = (rootChildren > 1) ;  // special case
                }
            //printf("Articulation Points:\n");

            int originPigeon = dfs(V, -1);
            List<Pair> result = new ArrayList<Pair>();
            for(int i=0; i<V; ++i){
                if(articulation_vertex[i]){
                    int pigeon = dfs(V, i);
                    result.add(new Pair(i, pigeon));
                }
                else{
                    result.add(new Pair(i, originPigeon));
                }
            }

            result.sort(new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    if(o1.second < o2.second) return 1;
                    else if(o1.second > o2.second) return -1;
                    else{
                        if(o1.first > o2.first) return 1;
                        else if(o1.first < o2.first) return -1;
                        else return 1;
                    }
                }
            });

            for(int i=0; i<m; ++i){
                System.out.format("%d %d\n", result.get(i).first, result.get(i).second);
            }

            System.out.println();
        }
    }

}
