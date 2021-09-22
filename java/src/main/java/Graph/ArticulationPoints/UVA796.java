package Graph.ArticulationPoints;

import java.io.FileInputStream;
import java.util.*;

/**
 problem: https://onlinejudge.org/external/7/796.pdf
 level:
 solution: apply algorithm and print all the bridge

 #articulation_points #Lv2 #UVA

 **/


public class UVA796 {

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
                if (dfs_low[v.first] > dfs_num[u]) // for bridge
                    bridges.add(new Pair(Math.min(u, v.first), Math.max(u, v.first)));
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[v.first]); // update dfs_low[u]
            }
            else if (v.first != dfs_parent[u]) // a back edge and not direct cycle
                dfs_low[u] = Math.min(dfs_low[u], dfs_num[v.first]); // update dfs_low[u]
        }
    }

    private static void solve(int V /*, List<List<Pair>> AdjList*/){
        dfsNumberCounter = 0;
        dfs_num = new int[V];
        dfs_low = new int[V];
        dfs_parent = new int[V];
        articulation_vertex = new boolean[V];
        for(int i=0; i<V; ++i){
            dfs_num[i] = UNVISITED;
            dfs_low[i] = 0;
            dfs_parent[i] = 0;
            articulation_vertex[i] = false;
        }

        bridges = new ArrayList<Pair>();

        for (int i = 0; i < V; i++)
            if (dfs_num[i] == -1) {
                dfsRoot = i; rootChildren = 0;
                articulationPointAndBridge(i);
                articulation_vertex[dfsRoot] = (rootChildren > 1) ;  // special case
            }
        //printf("Articulation Points:\n");

        System.out.format("%d critical links\n", bridges.size());
        Collections.sort(bridges);
        for(int i=0; i<bridges.size(); ++i)
            System.out.format("%d - %d\n", bridges.get(i).first, bridges.get(i).second);


    }

    public static void main(String[] args){
        if(args.length >= 1)
            try {
                System.setIn(new FileInputStream(args[0]));
            }catch (Exception ex){System.out.println("cannot set" + ex.getMessage());};

        Scanner sc = new Scanner(System.in);

        while(true){
            if(!sc.hasNextLine()) break;

            String line = sc.nextLine();
            int V = Integer.parseInt(line);

            AdjList = new ArrayList<>();

            for(int j=0; j<V; ++j){
                AdjList.add(new ArrayList<>());
                line = sc.nextLine();

                StringTokenizer st = new StringTokenizer(line);
                int b = Integer.parseInt(st.nextToken());

                int c = Integer.parseInt(st.nextToken().replace(")", "").replace("(", ""));

                for(int i=0;  i<c; ++i){
                    int d = Integer.parseInt(st.nextToken());
                    AdjList.get(b).add(new Pair(d, 0));
                }
            }

            solve(V);

            if(sc.hasNextLine())sc.nextLine();//skip empty

            System.out.println();

        }
    }
}
