package Graph.BipartiteGraphCheck;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;


/**
 problem: https://onlinejudge.org/external/110/11080.pdf
 level:
 solution: AC

 #bipartite_graph_checking #bipartite_graph #Lv2 #UVA

 **/

public class UVA11080 {

    static int[] adjcnt = new int[201];
    static int[][] adj = new int[201][201];
    static int[] color = new int[201];
    static boolean[] visited = new boolean[201];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int ci=0; ci<nc; ++ci){
            Arrays.fill(adjcnt, 0);
            Arrays.fill(color, -1);
            Arrays.fill(visited, false);
            int vx = sc.nextInt();
            int e = sc.nextInt();

            for(int j=0; j<e; ++j){
                int s = sc.nextInt();
                int t = sc.nextInt();

                adj[s][adjcnt[s]++] = t;
                adj[t][adjcnt[t]++] = s;
            }

            boolean isBipartite = true;
            for(int i=0; i<vx; ++i){
                //System.out.println("Enter as " + i);
                //debugColor(nc);

                if(color[i] != -1) continue; //already colored
                Queue<Integer> q = new ArrayDeque<Integer>();
                q.offer(i);

                color[i] = 0;
                // addition of one boolean flag, initially true
                while (!q.isEmpty() & isBipartite) { // similar to the original BFS routine
                    int u = q.poll();
                    for (int j = 0; j < adjcnt[u]; j++) {
                        int v = adj[u][j];
                        if (color[v] == -1) { // but, instead of recording distance,
                            color[v] = 1 - color[u]; // we just record two colors {0, 1}
                            q.offer(v);
                        } else if (color[v] == color[u]) { // u & v.first has same color
                            isBipartite = false;
                            break;
                        }
                    }
                    //debugColor(nc);
                } // we have a coloring conflict
                if(!isBipartite) break;
            }

            //debugColor(vx);

            if(isBipartite){
                int minG = 0;
                Arrays.fill(visited, false);
                for(int j=0; j<vx; ++j) {
                    Pair result;
                    if(!visited[j]) {
                        result = dfs(j);
                        if(result.a == 0) minG += result.b;
                        else if(result.b == 0) minG += result.a;
                        else minG += Math.min(result.a, result.b);
                    }
                }

                System.out.println(minG);
            }
            else System.out.println("-1");
        }
    }

    static class Pair{
        int a; int b;
        public Pair(int a, int b){
            this.a = a; this.b = b;
        }
    }

    static Pair dfs(int u){
        visited[u] =true;
        //System.out.println("Visit " + u);
        Pair result = new Pair(color[u]==0?1:0,color[u]==1?1:0);
        for(int i=0; i<adjcnt[u]; ++i){
            int v = adj[u][i];
            if(!visited[v]) {
                Pair temp  = dfs(v);
                result.a += temp.a;
                result.b += temp.b;
            }
        }
        return result;
    }

    static void debugColor(int V){
        for(int i=0; i<V; ++i)
            System.out.print(color[i] + " ");
        System.out.println();
    }
}
