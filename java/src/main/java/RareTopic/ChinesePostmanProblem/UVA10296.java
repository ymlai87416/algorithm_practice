package RareTopic.ChinesePostmanProblem;

/**
 problem: https://onlinejudge.org/external/102/10296.pdf
 level:
 solution: A connected graph has an Euler cycle if and only if every vertex has even degree,
 use minimum weight perfect matching to find additional edges

 #UVA #Lv4 #chinese_postman_problem

 **/

import java.io.File;
import java.io.PrintStream;
import java.util.*;


public class UVA10296 {


    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\RareTopic\\ChinesePostmanProblem\\UVA10296.in";
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

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    int N;
    ArrayList<Pair>[] adjList;


    private int solve(int N, ArrayList<Pair>[] adjList) {
        int ans = 0;

        HashSet<Integer> oddNodes = new HashSet<>();
        //build a subgraph of odd node and do a minimum weight perfect matching
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = 0; j < adjList[i].size(); j++) {
                if(adjList[i].get(j).first == i) continue;
                else ++cnt;
            }
            if(cnt %2==1)
                oddNodes.add(i);
        }


        //dijkstra
        //new adjlist
        ArrayList<Pair>[] adjListOdd=  new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjListOdd[i] = new ArrayList<>();
        }

        this.N = N;
        for (int n: oddNodes) {
            int[] a = dijkstra(n, adjList);
            for (int m:oddNodes){
                if(n >= m) continue;
                adjListOdd[n].add(new Pair(m, a[m]));
                adjListOdd[m].add(new Pair(n, a[m]));
            }
        }

        this.adjList = adjListOdd;

        //new additional path from minimum weight matching
        for (int i = 0; i < dp.length; i++) {
            dp[i] = INF;
        }
        //set all none related nodes to 1
        int bitmask = 0;
        for (int i = 0; i < N; i++) {
            if(oddNodes.contains(i)) continue;
            bitmask = bitmask | (1 << i);
        }
        ans = dpHelper(bitmask);

        //add up each path in original graph
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < adjList[i].size(); j++) {
                if(i <= adjList[i].get(j).first)
                    ans  += adjList[i].get(j).second;
            }
        }

        return ans;
    }

    private int[] dijkstra(int e, ArrayList<Pair>[] adj){
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[e] = 0; // INF = 1Billion to avoid overflow

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        pq.offer(new Pair(0, e));
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

        return dist;
    }


    int[] dp = new int[1048576];
    static int INF = 999999999;

    static int NumberOfSetBits(int i)
    {
        i=i-((i>>>1)&0x55555555);
        i=(i&0x33333333)+((i>>>2)&0x33333333);
        return(((i+(i>>>4))&0x0F0F0F0F)*0x01010101)>>>24;
    }

    private int dpHelper(int bitmask){
        //pair up someone still not in a group and then return
        if(dp[bitmask] != INF) return dp[bitmask];
        if(NumberOfSetBits(bitmask) == N) return 0;

        int result = INF;
        for (int i = 0; i < N; i++) {
            if((bitmask & (1 << i)) != 0) continue;
            for (Pair p: adjList[i]) {
                if(i==p.first) continue;
                if((bitmask & (1 << p.first)) != 0) continue;
                int temp = dpHelper(bitmask | (1 << i) | (1 << p.first)) + p.second;
                if(temp < result)
                    result = temp;
            }
        }

        dp[bitmask] =result;
        return result;
    }

    static class Pair implements Comparable<Pair>{
        public int first, second;
        public Pair(int a, int b){this.first = a; this.second = b;}

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

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        while(true){
            int N = sc.nextInt();
            if(N==0) break;
            int M = sc.nextInt();
            ArrayList<Pair>[] adjList = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                adjList[i]  = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                int u = sc.nextInt();
                int v =sc.nextInt();
                int w = sc.nextInt();

                adjList[u-1].add(new Pair(v-1, w));
                if(u!=v)  //allow node to point to itself
                    adjList[v-1].add(new Pair(u-1, w));
            }

            //out.print("Case #" + i + ": ");
            System.out.println(solve(N, adjList));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new UVA10296().run();
    }

}
