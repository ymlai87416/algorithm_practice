package Graph.MSTStandard;

import com.sun.javafx.geom.Edge;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Tom on 10/5/2016.
 */
public class UVA11228 {

    static int[] x = new int[1001];
    static int[] y = new int[1001];
    static int[][] dist = new int[1001][1001];
    static boolean[] visited = new boolean[1001];
    static int[] state = new int[1001];

    static int n;
    static int r;

    static class UnionFind { // OOP style
        int[] p;
        int[] rank;
        int[] size;

        UnionFind(int N) {
            p = new int[N];
            rank = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) p[i] = i;
        }

        void init(){
            for (int i = 0; i < p.length; i++) p[i] = i;
            Arrays.fill(rank, 0);
            Arrays.fill(size, 1);
        }
        int findSet(int i) { return (p[i] == i) ? i : (p[i] = findSet(p[i])); }

        boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

        void unionSet(int i, int j) {
            if (!isSameSet(i, j)) { // if from different set
                int x = findSet(i), y = findSet(j);
                if (rank[x] > rank[y]) {
                    p[y] = x; // rank keeps the tree short
                    size[x] += size[y];
                }
                else {
                    p[x] = y;
                    size[y] += size[x];
                    if (rank[x] == rank[y])
                        rank[y]++;
                }
            }
        }

        int getSetSize(int i){
            return size[i];
        }
    }

    static void dfs(int u){
        visited[u] = true;

        for(int i=0; i<n; ++i){
            if(!visited[i] && dist[u][i] <= r * r) {
                state[i] = state[u];
                dfs(i);
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int ci=0; ci<nc; ++ci){
            n = sc.nextInt();
            r = sc.nextInt();

            for(int i=0; i<n; ++i){
                x[i] = sc.nextInt();
                y[i] = sc.nextInt();
            }

            for(int i=0; i<n; ++i){
                dist[i][i] = 0;
                for(int j=i+1; j<n; ++j){
                    int distx = (x[j]-x[i])*(x[j]-x[i]) + (y[j]-y[i])*(y[j]-y[i]);
                    dist[i][j] = distx;
                    dist[j][i] = distx;
                }
            }

            Arrays.fill(visited, false);
            int cnt = 0;
            for(int i=0; i<n; ++i){
                if(!visited[i]){
                    state[i] = cnt++;
                    dfs(i);
                }
            }

            Stack<Tuple> EdgeList = new Stack<Tuple>(); // (weight, two vertices) of the edge
            int E = 0;
            for(int i=0; i<n; ++i){
                for(int j=i+1; j<n; ++j){
                    EdgeList.push(new Tuple(Math.sqrt(dist[i][j]), i, j));
                    ++E;
                }
            }

            Collections.sort(EdgeList);

            double mst_cost = 0;
            double road_cost = 0;
            double railway_cost = 0;

            UnionFind UF = new UnionFind(n);
            UF.init();

            for (int i = 0; i < E; i++) {
                Tuple front = EdgeList.get(i);
                if (!UF.isSameSet(front.second, front.third)) {
                    mst_cost += front.first;
                    if(state[front.second] == state[front.third])
                        road_cost += front.first;
                    else
                        railway_cost += front.first;
                    UF.unionSet(front.second, front.third);
                } }


            System.out.format("Case #%d: %d %.0f %.0f\n", ci+1, cnt, road_cost, railway_cost);
        }
    }

    static class Tuple implements Comparable<Tuple>{
        double first;
        int second, third;
        public Tuple(double a, int b, int c){
            first = a;
            second = b;
            third = c;
        }

        @Override
        public int compareTo(Tuple o) {
            if(first < o.first) return -1;
            else if(first > o.first) return 1;
            else{
                if(second < o.second) return -1;
                else if(second > o.second) return 1;
                else{
                    if(third < o.third) return -1;
                    else if(third > o.third) return 1;
                    else return 0;
                }
            }
        }
    }
}
