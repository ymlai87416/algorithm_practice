package Graph.TopologicalSort;

import java.util.*;

/**
 * Created by Tom on 2/5/2016.
 *
 * First time do a topological sort.
 */
public class UVA872 {
    static int[] adjcnt;
    static int[][] adj;
    static int[] indegree;
    static ArrayList<Integer> ts;
    static char[] reverse = new char[21];

    static boolean Kahn(int V, ArrayList<Integer> answer) {
        if(answer.size() == V){
            boolean first = true;
            for(int i=0; i<V; ++i){
                if(i!=0) System.out.print(" ");
                System.out.print(reverse[answer.get(i)]);
            }
            System.out.println();
            return true;
        }
        //this either sort all, or fail to sort any.
        boolean hasAns= false;
        for(int i=0; i<V; ++i){
            if(indegree[i] == 0){
                answer.add(i);
                indegree[i]--;
                for(int j=0; j<adjcnt[i]; ++j){
                    indegree[adj[i][j]]--;
                }
                boolean kanresult = Kahn(V, answer);
                hasAns = hasAns || kanresult;
                answer.remove(answer.size()-1);
                ++indegree[i];
                for(int j=0; j<adjcnt[i]; ++j){
                    indegree[adj[i][j]]++;
                }
            }
        }
        return hasAns;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();

        int nc = sc.nextInt();
        sc.nextLine();

        adjcnt= new int[21];
        adj = new int[21][21];
        indegree = new int[21];

        for(int ci=0; ci<nc; ++ci){

            if(!sc.hasNext()) break;
            sc.nextLine();

            Arrays.fill(adjcnt, 0);
            for(int i=0; i<21; ++i){
                Arrays.fill(adj[i], 0);
            }
            Arrays.fill(indegree, 0);

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
                indegree[t]++;
            }

            ts.clear();
            if(!Kahn(cnt, new ArrayList<Integer>())){
                System.out.println("NO");
            }
            if(ci!=nc-1) System.out.println();
        }

    }
}
