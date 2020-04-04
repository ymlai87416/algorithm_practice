package Graph.DirectedAcyclicGraph.CoutingPathsInDAG;

import java.util.*;

/**
 * Created by ymlai on 14/4/2017.
 */
public class UVA10401 {

    static List<List<Integer>> AdjList = new ArrayList<>();

    static final int VISITED = 1;
    static final int UNVISITED = -1;
    static int[] dfs_num;

    static final String txtIndex = "123456789ABCDEF";

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
        Scanner  sc = new Scanner(System.in);

        for(int i=0; i<15*15; ++i)
            AdjList.add(new ArrayList<>());

        while(sc.hasNext()){
            String s = sc.next();
            s = s.trim();

            int n= s.length();

            for(int i=0; i<n*n; ++i)
                AdjList.get(i).clear();

            for(int i=0, j=1; j<s.length(); ++i, ++j){
                char ipos = s.charAt(i);
                char jpos = s.charAt(j);

                Set<Integer> si = new TreeSet<Integer>();
                Set<Integer> sj = new TreeSet<Integer>();

                if(ipos == '?')
                    for(int p=0; p<n; ++p)
                        si.add(p);
                else
                    si.add(txtIndex.indexOf(ipos));

                if(jpos == '?')
                    for(int p=0; p<n; ++p)
                        sj.add(p);
                else
                    sj.add(txtIndex.indexOf(jpos));

                for(Integer eleI: si){
                    for(Integer eleJ: sj){
                        if(Math.abs(eleI-eleJ) >= 2)
                            AdjList.get(i*n+eleI).add(j*n+eleJ);
                    }
                }
            }

            dfs_num = new int[n*n];
            ts.clear();
            Arrays.fill(dfs_num, UNVISITED);
            for(int i=0; i<n; ++i)
                dfs2(i);

            /*
            for (int i = (int)ts.size() - 1; i >= 0; i--) // read backwards
                System.out.format(" %d", ts.get(i));
            System.out.print("\n");
            */

            long[] numPath = new long[n*n];
            Arrays.fill(numPath, 0);

            for(int i=0; i<n; ++i)
                numPath[i]=1;

            for(int i=ts.size()-1; i>=0; --i){
                int u= ts.get(i);
                for(int j=0; j<AdjList.get(u).size(); ++j){
                    int v = AdjList.get(u).get(j);
                    numPath[v] += numPath[u];
                }
            }

            long sum = 0;
            for(int i=(n-1)*n; i<n*n; ++i){
                sum += numPath[i];
            }

            System.out.println(sum);
        }
    }

}
