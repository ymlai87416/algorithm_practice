package Graph.DirectedAcyclicGraph.CoutingPathsInDAG;

import java.util.*;

/**
 * Created by ymlai on 14/4/2017.
 */
public class UVA988 {

    static List<List<Integer>> AdjList;
    static Set<Integer> deathSet = new TreeSet<>();


    static final int VISITED = 1;
    static final int UNVISITED = -1;
    static int[] dfs_num;

    static List<Integer> ts = new ArrayList<Integer>(); // global vector to store the toposort in reverse order
    static void dfs2(int u) { // different function name compared to the original dfs
        dfs_num[u] = VISITED;
        for (int j = 0; j < AdjList.get(u).size(); j++) {
            int v = AdjList.get(u).get(j);
            if (dfs_num[v] == UNVISITED)
                dfs2(v);
        }
        ts.add(u);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        boolean first = true;

        while(sc.hasNextInt()){
            if(!first) System.out.println();
            else first = false;

            int E = sc.nextInt();

            AdjList = new ArrayList<>();
            deathSet.clear();

            for(int i=0; i<E; ++i){
                AdjList.add(new ArrayList<>());

                int n = sc.nextInt();
                if(n == 0) deathSet.add(i);

                for(int j=0; j<n; ++j){
                    AdjList.get(i).add(sc.nextInt());
                }
            }

            dfs_num = new int[E];
            ts.clear();
            Arrays.fill(dfs_num, UNVISITED);
            dfs2(0);

            /*
            for (int i = (int)ts.size() - 1; i >= 0; i--) // read backwards
                System.out.format(" %d", ts.get(i));
            System.out.print("\n");
            */

            int[] numPath = new int[E];
            Arrays.fill(numPath, 0);
            numPath[0]=1;

            for(int i=ts.size()-1; i>=0; --i){
                int u= ts.get(i);
                for(int j=0; j<AdjList.get(u).size(); ++j){
                    int v = AdjList.get(u).get(j);
                    numPath[v] += numPath[u];
                }
            }

            long sum = 0;
            for(Integer d: deathSet){
                sum += numPath[d];
            }

            System.out.println(sum);
        }
    }
}
