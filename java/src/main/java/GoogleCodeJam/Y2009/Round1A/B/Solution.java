package GoogleCodeJam.Y2009.Round1A.B;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2009\\Round1A\\B\\B-test.in";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            out = new PrintStream(System.out);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    static class Pair implements Comparable<Pair> {
        long first;
        int second;

        public Pair(long first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo( Pair o) {
            if (first < o.first) return -1;
            else if (first > o.first) return 1;
            else {
                if (second < o.second) return -1;
                else if (second > o.second) return 1;
                return 0;
            }
        }
    }

    static long[] dist = new long[1601];
    static final long INF = 1000000000000000000l;

    /*
        given N and M
        x can be 0..2N-1
        y can be 0..2M-1
     */
    private int intersection2Idx(int x, int y){
        return x * 40 + y;
    }

    private int[] idx2Intersection(int idx){
        return new int[] {idx / 40, idx % 40};
    }

    private long solve(int n, int m, int[][] S, int[][] W, int[][] T) {

        //source = 0, 0 and target = 2N-1, 2M-1
        for (int i = 0; i < dist.length; i++) {
            dist[i] = INF;
        }

        dist[0] = 0; // INF = 1Billion to avoid overflow

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        pq.offer(new Pair(0, 0));
        while (!pq.isEmpty()) { // main loop
            Pair front = pq.poll();
            long d = front.first;
            int u = front.second;
            if (d > dist[u]) continue; // this is a very important check

            ArrayList<Pair> adj = new ArrayList<>();
            int[] pos = idx2Intersection(u);

            long Si = S[pos[0]/2][pos[1]/2];
            long Wi = W[pos[0]/2][pos[1]/2];
            long Ti = T[pos[0]/2][pos[1]/2];

            long r = (d-Ti) % (Si+Wi);
            while(r < 0) r += (Si+Wi);
            long waitS, waitE;
            if(r < Si){
                waitS = 0;
                waitE = Si-r;
            }
            else{
                waitS = (Si+Wi)-r;
                waitE = 0;
            }


            if(pos[0] -1 >= 0) // W
                if(pos[0] %2== 1)
                    adj.add(new Pair(1+waitS, intersection2Idx(pos[0]-1, pos[1])));
                else
                    adj.add(new Pair(2, intersection2Idx(pos[0]-1, pos[1])));

            if(pos[0] +1 < 2*n) // E
                if(pos[0] % 2 == 0)
                    adj.add(new Pair(1+waitS, intersection2Idx(pos[0]+1, pos[1])));
                else
                    adj.add(new Pair(2, intersection2Idx(pos[0]+1, pos[1])));

            if(pos[1] -1 >= 0)
                if(pos[1] % 2 == 1)
                    adj.add(new Pair(1+waitE, intersection2Idx(pos[0], pos[1]-1)));
                else
                    adj.add(new Pair(2, intersection2Idx(pos[0], pos[1]-1)));


            if(pos[1] +1 < 2*m)
                if(pos[1] % 2 == 0)
                    adj.add(new Pair(1+waitE, intersection2Idx(pos[0], pos[1]+1)));
                else
                    adj.add(new Pair(2, intersection2Idx(pos[0], pos[1]+1)));

            //debug
            /*
            System.out.println("-----");
            System.out.println("Cur loc: " + pos[0] + " " + pos[1]);
            System.out.println("Cur step: " + d);
            System.out.println("Wait E: " + waitE + " Wait S: " + waitS);
            for (int i = 0; i < adj.size(); i++) {
                int[] pos2 = idx2Intersection(adj.get(i).second);
                System.out.println("N loc: " + pos2[0] + " " + pos2[1] + " Wait time: " + adj.get(i).first) ;
            }
            System.out.println("-----");
            */

            for (int j = 0; j < adj.size(); j++) {
                Pair v = adj.get(j); // all outgoing edges from u
                if (dist[u] + v.first < dist[v.second]) {
                    dist[v.second] = dist[u] + v.first; // relax operation
                    pq.offer(new Pair(dist[v.second], v.second));
                }
            }
        } // this variant can cause duplicate items in the priority queue

        return dist[intersection2Idx(2*n-1, 2*m-1)];
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {

            int n= sc.nextInt();
            int m= sc.nextInt();
            int[][] S = new int[n][m];
            int[][] W = new int[n][m];
            int[][] T = new int[n][m];

            for (int j = n-1; j >=0; j--) {
                for (int k = 0; k < m; k++) {
                    S[j][k] = sc.nextInt();
                    W[j][k] = sc.nextInt();
                    T[j][k] = sc.nextInt();
                }
            }

            out.print("Case #" + i + ": ");
            long r=  solve(n, m, S, W, T);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}