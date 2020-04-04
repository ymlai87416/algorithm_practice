package Graph.WeightedDijkstraEasier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by Tom on 8/5/2016.
 */
public class UVA10986 {
    static ArrayList<Pair>[] adj = new ArrayList[20001];

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

    static int[] dist = new int[20001];
    static int n;
    static int s;
    static int t;
    static final int INF = 1000000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < adj.length; ++i)
            adj[i] = new ArrayList<Pair>();

        int nc = sc.nextInt();

        for (int ci = 0; ci < nc; ++ci) {
            n = sc.nextInt();
            int m = sc.nextInt();
            s = sc.nextInt();
            t = sc.nextInt();

            for (int i = 0; i < n; ++i)
                adj[i].clear();

            for (int i = 0; i < m; ++i) {
                int ss = sc.nextInt();
                int tt = sc.nextInt();
                int lat = sc.nextInt();

                adj[ss].add(new Pair(tt, lat));
                adj[tt].add(new Pair(ss, lat));
            }

            Arrays.fill(dist, INF);
            dist[s] = 0; // INF = 1Billion to avoid overflow

            PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
            pq.offer(new Pair(0, s));
            while (!pq.isEmpty()) { // main loop
                Pair front = pq.poll();
                int d = front.first, u = front.second;
                if (d > dist[u]) continue; // this is a very important check
                for (int j = 0; j < adj[u].size(); j++) {
                    Pair v = adj[u].get(j); // all outgoing edges from u
                    if (dist[u] + v.second < dist[v.first]) {
                        dist[v.first] = dist[u] + v.second; // relax operation
                        pq.offer(new Pair(dist[v.first], v.first));
                    }
                }
            } // this variant can cause duplicate items in the priority queue

            if(dist[t] == INF)
                System.out.format("Case #%d: unreachable\n", ci+1);
            else
                System.out.format("Case #%d: %d\n", ci+1, dist[t]);
        }
    }
}
