package GoogleCodeJam.Y2017.Round1B.C;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by ymlai on 22/4/2017.
 */
public class C {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "C-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false)); ;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    static final long INF = 9000000000000000000L;
    static final double INFD = 1e15;

    static class Pair implements Comparable<Pair> {
        long first;
        int second;

        public Pair(long first, int second) {
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

    static class Pair2 {
        int first; //node
        double second; //cost

        public Pair2(int first, double second) {
            this.first = first;
            this.second = second;
        }
    }

    static class PairPQ implements Comparable<PairPQ> {
        double first;
        int second;

        public PairPQ(double first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(PairPQ o) {
            if (first < o.first) return -1;
            else if (first > o.first) return 1;
            else {
                if (second < o.second) return -1;
                else if (second > o.second) return 1;
                return 0;
            }
        }
    }

    private long[] dijstkra(int N, int e, int[][] adj){
        long[] dist = new long[N];

        Arrays.fill(dist, INF);
        dist[e] = 0; // INF = 1Billion to avoid overflow

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        pq.offer(new Pair(0, e));
        while (!pq.isEmpty()) { // main loop
            Pair front = pq.poll();
            long d = front.first;
            int u = front.second;
            if (d > dist[u]) continue; // this is a very important check
            for (int j = 0; j < N; j++) {
                if(adj[u][j] == -1) continue;
                int v = j; // all outgoing edges from u
                if (dist[u] + adj[u][v] < dist[v]) {
                    dist[v] = dist[u] + adj[u][v]; // relax operation
                    pq.offer(new Pair(dist[v], v));
                }
            }
        } // this variant can cause duplicate items in the priority queue

        return dist;
    }

    private double[] dijstkra2(int N, int e, List<Pair2>[] adjList){
        double[] dist = new double[N];

        Arrays.fill(dist, INFD);
        dist[e] = 0; // INF = 1Billion to avoid overflow

        PriorityQueue<PairPQ> pq = new PriorityQueue<PairPQ>();
        pq.offer(new PairPQ(0.0, e));
        while (!pq.isEmpty()) { // main loop
            PairPQ front = pq.poll();
            double d = front.first;
            int u = front.second;
            if (d > dist[u]) continue; // this is a very important check
            for (int j = 0; j < adjList[u].size(); j++) {
                Pair2 v = adjList[u].get(j); // all outgoing edges from u
                if (dist[u] + v.second < dist[v.first]) {
                    dist[v.first] = dist[u] + v.second; // relax operation
                    pq.offer(new PairPQ(dist[v.first], v.first));
                }
            }
        } // this variant can cause duplicate items in the priority queue

        return dist;
    }


    List<Pair2>[] AdjList = new ArrayList[10100];

    private int node(int city, int horse, int N){
        return city*(N) + horse;
    }

    private void solve(int N, int Q, long[] E, long[] S, int[] U, int[] V) {
        int ans = 0;

        for(int i=0; i<N*N; ++i)
            AdjList[i] = new ArrayList<>();

        for(int i=0; i<N; ++i){
            long[] dist = dijstkra(N, i, dist1);
            //linked up all the matrix

            for(int j=0; j<N; ++j){
                if(i==j) continue;
                if(dist[j] <= E[i]){

                    int u = node(i, i, N);
                    int v = node(j, i, N);
                    int lv = node(j, j, N);
                    double w = 1.0*dist[j]/S[i];
                    //System.out.format("Adding node %d %d to %d %d (%.3f) and %d %d\n", i, i, j, i, w, j, j);
                    Pair2 p = new Pair2(v, w);
                    AdjList[u].add(p);
                    Pair2 lp = new Pair2(lv, 0);
                    AdjList[v].add(lp);
                }
            }
        }

        //all city-horse to city-local-horse
        for(int i=0; i<Q; ++i){
            int u = node(U[i], U[i], N);
            int vbegin = node(V[i], 0, N);
            int vend = node(V[i], N , N);
            double minTime = INFD;
            double[] dist = dijstkra2(N*N, u, AdjList);
            for(int j=vbegin; j<vend; ++j){
                if(minTime > dist[j])
                    minTime = dist[j];
            }
            out.format(" %.6f", minTime);
        }
        out.println();
    }

    static int[][] dist1 = new int[101][101];

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ":");
            System.out.println("Case #" + i + ": ");

            int N, Q;

            N = sc.nextInt();
            Q = sc.nextInt();

            long[] E = new long[N];
            long[] S = new long[N];

            int[] U = new int[Q];
            int[] V = new int[Q];

            for (int j=0; j<N; ++j){
                E[j] = sc.nextInt();
                S[j] = sc.nextInt();
            }


            for(int j=0; j<N; ++j)
                for(int k=0; k<N; ++k)
                    dist1[j][k] =sc.nextInt();

            for(int j=0; j<Q; ++j){
                U[j]=sc.nextInt();
                V[j] = sc.nextInt();
                U[j]--;
                V[j]--;
            }


            solve(N, Q, E, S, U, V);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new C().run();
    }

}
