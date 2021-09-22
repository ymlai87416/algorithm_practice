package Graph.Tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by ymlai on 12/4/2017.
 *
 * identify the diameter of this tree
 * problem: https://onlinejudge.org/external/104/10459.pdf
 *
 * #UVA #tree #Lv4 #tree_diameter
 */
public class UVA10459 {

    static int[] dfs_num; // global variable, initially all values are set to UNVISITED
    static List<List<Integer>> AdjList;
    final static int UNVISITED = -1;

    static class DfsContent{
        public int first;
        public int second;
        public Iterator<Integer> itr;
        public DfsContent(int a, int b){
            first = a;
            second = b;
        }

        public DfsContent(int a, int b, Iterator<Integer> ii){
            first = a;
            second = b;
            itr = ii;
        }
    }

    static class Pair{
        public int first;
        public int second;
        public Pair(int a, int b){
            first = a;
            second = b;
        }
    }

    static int dp[][] = new int[5001][5001];

    //stackoverflow.... for 1024 elements shit.
    static int dfs(int root, int u) { // DFS for normal usage: as graph traversal algorithm
        if(dp[root][u] != -1)
            return dp[root][u];

        dfs_num[u] = 1; // important: we mark this vertex as visited

        dp[root][u] = 0;

        for (int j = 0; j < AdjList.get(u).size(); j++) { // default DS: AdjList
            Integer v = AdjList.get(u).get(j); // v is a (neighbor, weight) pair
            if (dfs_num[v] == UNVISITED) // important check to avoid cycle
                dp[root][u] = Math.max(dp[root][u], dfs(u, v)); // recursively visits unvisited neighbors of vertex u
        }
        dp[root][u]++;
        return dp[root][u];
    } // for simple graph traversal, we ignore the weight stored at v.second


    public static void dfsIter(int root, int s) {
        // depth-first search using an explicit stack
        Stack<DfsContent> stack = new Stack<>();
        dfs_num[s] = 1;
        stack.push(new DfsContent(s, root, AdjList.get(s).iterator()));
        while (!stack.isEmpty()) {
            DfsContent v = stack.peek();
            int w = -1;

            while(v.itr.hasNext()) {
                int wt = v.itr.next();
                if(dfs_num[wt] == UNVISITED && dp[v.first][wt] == -1) {
                    w = wt;
                    break;
                }
            }

            if(w != -1){
                //push to stack for processing
                dfs_num[w] = 1;
                stack.push(new DfsContent(w, v.first, AdjList.get(w).iterator()));
            }
            else {
                //post processing
                dp[v.second][v.first] = 0;
                for(int i=0; i<AdjList.get(v.first).size(); ++i) {
                    int nxt= AdjList.get(v.first).get(i);
                    if(nxt == v.second) continue;
                    dp[v.second][v.first] = Math.max(dp[v.second][v.first], dp[v.first][nxt]);
                }
                dp[v.second][v.first]++;
                stack.pop();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        if(args.length >= 1){
            try {
                System.setIn(new FileInputStream(args[0]));
            }catch (Exception ex) { ex.printStackTrace();}
        }

        AdjList = new ArrayList<>();
        for(int i=0; i<5001; ++i)
            AdjList.add(new ArrayList<>());

        Scanner sc = new Scanner(System.in);

        while(true ){
            if(!sc.hasNextInt()){
                break;
            }

            int N = sc.nextInt();
            dfs_num = new int[N];

            for(int i=0; i<N; ++i)
                AdjList.get(i).clear();

            for(int i=0; i<N; ++i){
                int a = sc.nextInt();
                for(int j=0; j<a; ++j){
                    int b = sc.nextInt();
                    AdjList.get(i).add(b-1);
                }
            }

            TreeMap<Integer, List<Integer>> treeLen = new TreeMap<>();

            for(int i=0; i<N; ++i)
                Arrays.fill(dp[i], -1);
            Arrays.fill(dp[5000], -1);

            for(int i=0; i<N; ++i){
                Arrays.fill(dfs_num, UNVISITED);

                dfsIter(5000, i);

                int maxLen = dp[5000][i];

                if(treeLen.containsKey(maxLen))
                    treeLen.get(maxLen).add(i);
                else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    treeLen.put(maxLen, list);
                }
            }

            System.out.print("Best Roots  :");
            int first = treeLen.firstKey();
            List<Integer> bestNode = treeLen.get(first);
            for(Integer n : bestNode)
                System.out.format(" %d", n+1);
            System.out.println();

            System.out.print("Worst Roots :");
            int last = treeLen.lastKey();
            List<Integer> worstNode = treeLen.get(last);
            for(Integer n : worstNode)
                System.out.format(" %d", n+1);
            System.out.println();
        }
    }
}
