package Graph.NetworkFlow.Variants;

import java.util.*;

/**
 * Created by ymlai on 16/4/2017.
 *
 * Time 17:28
 */
public class UVA11506 {

    final static int sIdx = 102;
    final static int tIdx = 103;
    final static int maxV = 104;
    final static int INF = 10000000;
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

    static int nodeIn(int n){
        return n*2;
    }

    static int nodeOut(int n){
        return n*2+1;
    }

    static int M, W;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<maxV; ++i)
            AdjList.add(new ArrayList<>());

        while(true){
            M = sc.nextInt();
            W = sc.nextInt();

            if(M == 0 && W == 0)
                break;

            for(int i=0; i<maxV; ++i){
                AdjList.get(i).clear();
                Arrays.fill(res[i], 0);
            }

            //boss computer
            AdjList.get(nodeIn(1)).add(nodeOut(1));
            AdjList.get(nodeOut(1)).add(nodeIn(1));
            res[nodeIn(1)][nodeOut(1)] = INF;

            AdjList.get(sIdx).add(nodeIn(1));
            AdjList.get(nodeIn(1)).add(sIdx);
            res[sIdx][nodeIn(1)] = INF;

            //server
            AdjList.get(nodeIn(M)).add(nodeOut(M));
            AdjList.get(nodeOut(M)).add(nodeIn(M));
            res[nodeIn(M)][nodeOut(M)] = INF;

            AdjList.get(nodeOut(M)).add(tIdx);
            AdjList.get(tIdx).add(nodeOut(M));
            res[nodeOut(M)][tIdx] = INF;

            for(int i=0; i<M-2; ++i){
                int u = sc.nextInt();
                int c = sc.nextInt();

                AdjList.get(nodeIn(u)).add(nodeOut(u));
                AdjList.get(nodeOut(u)).add(nodeIn(u));
                res[nodeIn(u)][nodeOut(u)] = c;
            }

            for(int i=0; i<W; ++i){
                int u = sc.nextInt();
                int v = sc.nextInt();
                int c = sc.nextInt();

                AdjList.get(nodeOut(u)).add(nodeIn(v));
                AdjList.get(nodeOut(v)).add(nodeIn(u));

                res[nodeOut(u)][nodeIn(v)] = c;
                res[nodeOut(v)][nodeIn(u)] = c;
                AdjList.get(nodeIn(v)).add(nodeOut(u));
                AdjList.get(nodeIn(u)).add(nodeOut(v));
            }

            s = sIdx;
            t = tIdx;
            // inside int main(), assume that we have both res (AdjMatrix) and AdjList
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

            System.out.println(mf);
        }
    }
}
