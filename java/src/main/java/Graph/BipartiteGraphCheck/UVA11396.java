package Graph.BipartiteGraphCheck;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Tom on 3/5/2016.
 */
public class UVA11396 {
    static int[] adjcnt = new int[301];
    static int[][] adj = new int[301][301];
    static boolean[] visited = new boolean[301];
    static int[] color = new int[301];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true) {
            Arrays.fill(visited, false);
            Arrays.fill(adjcnt, 0);
            int nc = sc.nextInt();
            if (nc == 0) break;

            while (true) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                if (a == 0 && b == 0) break;
                a--; b--;
                adj[a][adjcnt[a]++] = b;
                adj[b][adjcnt[b]++] = a;

            }


            Arrays.fill(color, -1);

            boolean isBipartite = true;
            for(int i=0; i<nc; ++i){
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

            if(isBipartite) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static void debugColor(int V){
        for(int i=0; i<V; ++i)
            System.out.print(color[i] + " ");
        System.out.println();
    }
}
