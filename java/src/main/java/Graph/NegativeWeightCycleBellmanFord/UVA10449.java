package Graph.NegativeWeightCycleBellmanFord;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 12/5/2016.
 */
public class UVA10449 {
    static int[] busy = new int[201];
    static ArrayList<Pair>[] adj = new ArrayList[201];
    static final int INF = Integer.MAX_VALUE;
    static int[] dist = new int[201];

    static class Pair implements Comparable<Pair> {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            if (first < o.first) return -1;
            else if (first > o.first) return 1;
            else {
                if (second < o.second) return -1;
                else if (second > o.second) return 1;
                return 0;
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<adj.length; ++i)
            adj[i] = new ArrayList<Pair>();

        int cnt = 0;
        while(true){

            if(!sc.hasNextInt()) break;

            for(int i=0; i<adj.length; ++i)
                adj[i].clear();

            int n = sc.nextInt();
            for(int i=0; i<n; ++i){
                busy[i] = sc.nextInt();
            }

            int m = sc.nextInt();
            for(int i=0; i<m; ++i){
                int a = sc.nextInt();
                int b = sc.nextInt();
                a--; b--;

                adj[a].add(new Pair(b, (busy[b]-busy[a]) * (busy[b]-busy[a]) * (busy[b]-busy[a])));
            }

            int q = sc.nextInt();

            System.out.format("Set #%d\n", cnt+1);

            Arrays.fill(dist, INF);
            dist[0] = 0;
            for (int i = 0; i < n - 1; i++) // relax all E edges V-1 times
                for (int u = 0; u < n; u++) // these two loops = O(E), overall O(VE)
                    for (int j = 0; j < adj[u].size(); j++) {
                        Pair v = adj[u].get(j); // record SP spanning here if needed
                        dist[v.first] = Math.min(dist[v.first], capAdd(dist[u],v.second)); // relax
                    }

            for(int qi=0; qi<q; ++qi){
                int qq = sc.nextInt(); qq--;
                if(dist[qq] < 3 || dist[qq] == INF)
                    System.out.println("?");
                else
                    System.out.println(dist[qq]);
            }

            sc.nextLine();

            cnt++;
        }
    }

    static int capAdd(int a, int b){
        if(a == INF || b == INF) return INF;
        else return a+b;
    }
}
