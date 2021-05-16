package GoogleCodeJam.Y2012.Round1B.B;
/*
Just code with <=20
and the checking minus formula are all wrong...
 */

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2012\\Round1B\\B\\B-test.in";
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

    boolean debugflag = true;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }
    }
    private void debug(Object... s){
        if(debugflag) {
            //System.out.println(s);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                sb.append(s[i].toString() + " ");
            }
            System.out.println("\033[0;34m" + sb.toString() + "\033[0;30m");
        }
    }

    int N;
    int M;
    int H;
    int[][] C;
    int[][] F;

    private double solve(int H, int N, int M, int[][] C, int[][] F) {
        this.N = N;
        this.H = H;
        this.M = M;
        this.C = C;
        this.F = F;

        double ans = 0;

        ans = helper();

        return ans;
    }

    static ArrayList<Pair>[] adj = new ArrayList[20001];

    static class Pair implements Comparable<Pair> {
        double first;
        int second;

        public Pair(double first, int second) {
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

    double[] dist = new double[10001];
    double INF = 999999999;

    private int toIdx(int i, int j){
        return i * 100+j;
    }
    private int[] toCoord(int i){
        return new int[]{i/100, i%100};
    }

    int[][] delta = new int[][] { {-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private double helper(){
        int s = toIdx(0, 0);

        Arrays.fill(dist, INF);
        dist[s] = 0; // INF = 1Billion to avoid overflow

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        pq.offer(new Pair(0, s));
        double ct = 0;
        while (!pq.isEmpty()) { // main loop
            Pair front = pq.poll();
            double d = front.first;
            int u = front.second;
            if (d > dist[u]) continue; // this is a very important check

            for (int j = 0; j < 4; j++) {
                int[] uc = toCoord(u);
                int vx = uc[0] + delta[j][0];
                int vy = uc[1] + delta[j][1];
                int vi = toIdx(vx, vy);

                double duv = checkRoom(uc[0], uc[1], vx, vy, d);
                if(duv == INF) continue;

                if (dist[u] + duv < dist[vi]) {
                    dist[vi] = dist[u] + duv; // relax operation
                    pq.offer(new Pair(dist[vi],vi));
                }
            }
        } // this variant can cause duplicate items in the priority queue

        int t = toIdx(N-1, M-1);
        if(dist[t] == INF)
            return INF;
        else
            return dist[t];
    }

    private double checkRoom(int ci, int cj, int ni, int nj, double t){

        if(ni < 0 || ni >= N) return INF;
        if(nj < 0 || nj >= M) return INF;

        int curCeil =C[ci][cj];
        int curFloor = F[ci][cj];
        int nextCeil= C[ni][nj];
        int nextFloor = F[ni][nj];
        double curWater = H- t* 10;
        double t1a = (Math.max(0, curWater+50 - nextCeil) )/10;
        double t1b = nextCeil - curFloor >= 50 ? 0 : INF;
        double t1c = nextCeil - nextFloor >= 50? 0: INF;
        double t2 = curCeil - nextFloor >= 50 ? 0: INF;
        double r = max2(t1a, t1b, t1c, t2);

        if(r == INF) return INF;

        if(t == 0 && r == 0) {
            r = 0;
        }
        else{
            double curRoomWater = curWater - (t1a * 10) - curFloor;
            if (curRoomWater >= 20)
                r += 1;
            else
                r += 10;
        }


        debug(ci, cj, ni, nj, "time: ", t, "dist: ", r);
        return r;
    }

    private double max2(double... a){
        double r = -1;
        for (int i = 0; i < a.length; i++) {
            r = Math.max(r, a[i]);
        }
        return r;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int H = sc.nextInt();
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[][] C = new int[N][M];
            int[][] F= new int[N][M];
            for (int j = 0; j <N; j++) {
                for (int k = 0; k < M; k++) {
                    C[j][k] = sc.nextInt();
                }
            }
            for (int j = 0; j <N; j++) {
                for (int k = 0; k < M; k++) {
                    F[j][k] = sc.nextInt();
                }
            }
            double r = solve(H, N, M, C, F);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }


}
