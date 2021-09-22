package Graph.EulerianGraph;

import java.util.*;

 /**
 * Created by ymlai on 14/4/2017.
  *
  * You have to model a graph that all edge must be passed.
  * Build a graph on color instead of bead.
  *
  * #UVA #Lv3 #eulerian_graph
  *
 */
public class UVA10596 {
    static int[] cnt = new int[201];
    static boolean[] visited = new boolean[201];
    static List<List<Integer>> AdjList = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<201; ++i)
            AdjList.add(new ArrayList<>());

        while(sc.hasNextInt()){
            int N= sc.nextInt();
            int R = sc.nextInt();

            for(int i=0; i<N; ++i){
                cnt[i] = 0;
                AdjList.get(i).clear();
            }

            for(int i=0; i<R; ++i){
                int a = sc.nextInt();
                int b = sc.nextInt();
                cnt[a]++;
                cnt[b]++;

                AdjList.get(a).add(b);
                AdjList.get(b).add(a);
            }

            boolean possible=true;
            for(int i=0; i<N; ++i){
                if(cnt[i] % 2 != 0) {
                    possible = false;
                    break;
                }
            }

            boolean canVisitAll = true;
            Arrays.fill(visited, false);

            int s = -1;
            for(int i=0; i<N; ++i)
                if(cnt[i] > 0){
                    s = i;
                    break;
                }

            if(s != -1){
                dfs(s);

                for(int i=0; i<N; ++i) {
                    if(cnt[i] == 0) continue;
                    canVisitAll = canVisitAll && visited[i];
                }
            }
            else
                canVisitAll = false;

            //System.out.println(canVisitAll + " " + possible);

            if(possible && canVisitAll)
                System.out.println("Possible");
            else
                System.out.println("Not Possible");
        }
    }

     static void dfs(int s){
         Stack<Integer> st = new Stack<Integer>();
         visited[s]  =true;
         st.push(s);

         while(!st.empty()){
             int u = st.pop();
             for(int j=0; j<AdjList.get(u).size(); ++j){
                 int v = AdjList.get(u).get(j);
                 if(!visited[v]) {
                     visited[v] = true;
                     st.add(v);
                 }
             }
         }
     }
}
