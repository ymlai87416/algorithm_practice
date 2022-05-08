package Graph.NetworkFlow.StandardMaxFlowProblem;

import java.util.*;

/**
 * Created by ymlai on 16/4/2017.
 *
 * problem: https://onlinejudge.org/external/8/820.pdf
 * #UVA #Lv2 #network_flow
 */
public class UVA820 {
    final static int maxV = 101;
    final static int INF = Integer.MAX_VALUE;
    final static List<List<Integer>> AdjList = new ArrayList<>();

    static int[][] res = new int[maxV][maxV];
    static int mf, f, s, t; // global variables
    static int[] p; // p stores the BFS spanning tree from s
    static void augment(int v, int minEdge) { // traverse BFS spanning tree from s->t
        if (v == s) { f = minEdge; return; } // record minEdge in a global var f
        else if (p[v] != -1) {
            augment(p[v], Math.min(minEdge, res[p[v]][v]));
            res[p[v]][v] -= f; res[v][p[v]] += f;
        }
    }

    static BitSet vis;
    static int edmondKarp(int sIdx, int tIdx) {
        s = sIdx;
        t = tIdx;
        // inside int main(), assume that we have both res (AdjMatrix) and AdjList
        mf = 0;
        while (true) { // now a true O(VE^2) Edmonds Karp’s algorithm
            f = 0;
            vis = new BitSet(maxV); vis.set(s); // we change vi dist to bitset!
            Queue<Integer> q = new ArrayDeque<>(); q.offer(s);
            p = new int[maxV]; Arrays.fill(p, -1);
            while (!q.isEmpty()) {
                int u = q.poll();
                if (u == t) break;
                for (int j = 0; j < AdjList.get(u).size(); j++) { // AdjList here!
                    int v = AdjList.get(u).get(j); // we use vector<vi> AdjList
                    if (res[u][v] > 0 && !vis.get(v)) {
                        vis.set(v);
                        q.offer(v);
                        p[v] = u;
                    }
                }
            }
            augment(t, INF);
            if (f == 0) break;
            mf += f;
        }

        return mf;
    }

    static int fulkerson(int sIdx, int tIdx) {
        s = sIdx;
        t = tIdx;
        // inside int main(), assume that we have both res (AdjMatrix) and AdjList
        mf = 0;
        while (true) { // now a true O(VE^2) Edmonds Karp’s algorithm
            f = 0;
            vis = new BitSet(maxV); vis.set(s); // we change vi dist to bitset!
            p = new int[maxV]; Arrays.fill(p, -1);
            dfs(s, t);

            augment(t, INF);
            if (f == 0) break;
            mf += f;
        }

        return mf;
    }

    static void dfs(int u, int t){
        if(u == t) return;
        for (int j = 0; j < AdjList.get(u).size(); j++) { // AdjList here!
            int v = AdjList.get(u).get(j); // we use vector<vi> AdjList
            if (res[u][v] > 0 && !vis.get(v)) {
                vis.set(v);
                p[v] = u;
                dfs(v, t);
            }
        }
    }

    static int[] d;
    static int dinic(int sIdx, int tIdx) {
        s = sIdx;
        t = tIdx;
        // inside int main(), assume that we have both res (AdjMatrix) and AdjList
        mf = 0;
        while (true) { // now a true O(VE^2) Edmonds Karp’s algorithm
            f = 0;
            vis = new BitSet(maxV); vis.set(s); // we change vi dist to bitset!
            Queue<Integer> q = new ArrayDeque<>(); q.offer(s);
            d = new int[maxV]; d[s] = 0;
            p = new int[maxV]; Arrays.fill(p, -1);
            while (!q.isEmpty()) {
                int u = q.poll();
                if (u == t) break;
                for (int j = 0; j < AdjList.get(u).size(); j++) { // AdjList here!
                    int v = AdjList.get(u).get(j); // we use vector<vi> AdjList
                    if (res[u][v] > 0 && !vis.get(v)) {
                        vis.set(v);
                        q.offer(v);
                        d[v] = d[u] + 1;
                        p[v] = u;
                    }
                }
            }

            int sf = 0;
            int count = 0;
            /*
            while(true){
                f = 0;
                augment(t, INF);
                if (f == 0) break;
                sf += f;

                vis = new BitSet(maxV); vis.set(s);
                p = new int[maxV]; Arrays.fill(p, -1);
                dfs2(s, t);
                ++count;
            }*/

            for(int v=0; v<maxV; ++v){
                if(d[v]+1 == d[t]) {
                    f = 0;
                    augment(v, Math.min(INF, res[v][t]));
                    res[v][t] -= f; res[t][v] += f;

                    sf += f;
                }
            }

            //System.out.println("TT" + count + " "+ sf);

            if(sf == 0)break;
            mf += sf;
        }

        return mf;
    }

    static void dfs2(int u, int t){
        if(u == t) return;
        for (int j = 0; j < AdjList.get(u).size(); j++) { // AdjList here!
            int v = AdjList.get(u).get(j); // we use vector<vi> AdjList
            if (res[u][v] > 0 && !vis.get(v) && d[v] == d[u]+1) {
                vis.set(v);
                p[v] = u;
                dfs(v, t);
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<maxV; ++i)
            AdjList.add(new ArrayList<>());

        int nc = 0;
        while(true){
            int n =sc.nextInt();

            if(n==0) break;
            nc++;

            for(int i=0; i<maxV; ++i) {
                AdjList.get(i).clear();
                Arrays.fill(res[i], 0);
            }

            s = sc.nextInt();
            t = sc.nextInt();
            int nE = sc.nextInt();

            for(int i=0; i<nE; ++i){
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                int cap = sc.nextInt();

                //the 2 nodes repeat themselves.... what the fuck.
                if(!AdjList.get(v1).contains(v2))
                    AdjList.get(v1).add(v2);
                if(!AdjList.get(v2).contains(v1))
                    AdjList.get(v2).add(v1);
                res[v1][v2] += cap;
                res[v2][v1] += cap;
            }

            // inside int main(), assume that we have both res (AdjMatrix) and AdjList
            /*
            mf = 0;
            while (true) { // now a true O(VE^2) Edmonds Karp’s algorithm
                f = 0;
                BitSet vis = new BitSet(maxV); vis.set(s); // we change vi dist to bitset!
                Queue<Integer> q = new ArrayDeque<>(); q.offer(s);
                p = new int[maxV]; Arrays.fill(p, -1);
                while (!q.isEmpty()) {
                    int u = q.poll();
                    if (u == t) break;
                    for (int j = 0; j < AdjList.get(u).size(); j++) { // AdjList here!
                        int v = AdjList.get(u).get(j); // we use vector<vi> AdjList
                        if (res[u][v] > 0 && !vis.get(v)) {
                            vis.set(v);
                            q.offer(v);
                            p[v] = u;
                        }
                    }
                }
                augment(t, INF);
                if (f == 0) break;
                mf += f;
            }
             */

            //mf = fulkerson(s, t); //0.330
            //mf = edmondKarp(s, t); //0.360
            mf = dinic(s, t); //0.350

            System.out.format("Network %d\n", nc);
            System.out.format("The bandwidth is %d.\n", mf);
            System.out.println();
        }
    }
}
