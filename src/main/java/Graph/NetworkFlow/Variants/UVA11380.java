package Graph.NetworkFlow.Variants;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by ymlai on 16/4/2017.
 *
 * Beware this question is a node question, it is wrong to model it as an edge
 *
 * See
 *    *
 *   *..
 *    @.@
 *   only one can save, but if we model with edge but not vertex, cannot find the correct answer.
 */
public class UVA11380 {
    final static int sIdx = 1801;
    final static int tIdx = 1802;
    final static int maxV = 1803;
    final static int INF = Integer.MAX_VALUE;
    final static List<List<Integer>> AdjList = new ArrayList<>();
    final static String objIdx = "*.@#";
    static final int BIGEDGE = 10000;
    final static int[] objVertexCap = {1,1,BIGEDGE,BIGEDGE};
    static char[][] map;

    static int X, Y, P;

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

    final static int[] dx = {-1, 1, 0, 0};
    final static int[] dy = {0, 0, -1, 1};

    static boolean onMap(int i, int j){
        if(i< 0|| i>=X)return false;
        if(j<0|| j>=Y) return false;
        return true;
    }

    static int coordToInIdx(int i, int j){
        return (i*Y+j)*2;
    }

    static int coordToOutIdx(int i, int j){
        return (i*Y+j)*2+1;
    }

    public static void main(String[] args) throws Exception{
        if(args.length > 0)
            System.setIn(new FileInputStream(args[0]));

        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<maxV; ++i)
            AdjList.add(new ArrayList<>());

        String line;
        while(true){
            line = sc.readLine();
            if(line == null)
                break;

            String[] token = line.split(" ");
            X = Integer.parseInt(token[0]);
            Y = Integer.parseInt(token[1]);
            P = Integer.parseInt(token[2]);

            map = new char[X][Y];

            for(int i=0; i<X; ++i){
                line = sc.readLine();
                for(int j=0; j<Y; ++j){
                    map[i][j] = line.charAt(j);
                }
            }

            for(int i=0; i<maxV; ++i) {
                AdjList.get(i).clear();
                Arrays.fill(res[i], 0);
            }

            int ni, nj;
            for(int i=0; i<X; ++i){
                for(int j=0; j<Y; ++j){
                    int srcObj = objIdx.indexOf(map[i][j]);
                    if(srcObj == -1) continue;

                    int uIn = coordToInIdx(i, j);
                    int uOut = coordToOutIdx(i, j);

                    AdjList.get(uIn).add(uOut);
                    AdjList.get(uOut).add(uIn);
                    res[uIn][uOut] = objVertexCap[objIdx.indexOf(map[i][j])];

                    for(int k=0; k<4; ++k){
                        ni = i+dx[k];
                        nj = j+dy[k];

                        if(onMap(ni, nj)){

                            int tgtObj = objIdx.indexOf(map[ni][nj]);
                            if(srcObj != -1 && tgtObj != -1 &&
                                    !(map[i][j]=='*' && map[ni][nj] =='*')){
                                int vIn = coordToInIdx(ni, nj);
                                AdjList.get(uOut).add(vIn);
                                AdjList.get(vIn).add(uOut);
                                res[uOut][vIn] = BIGEDGE;
                            }
                        }
                    }

                    if(map[i][j] == '*'){
                        AdjList.get(sIdx).add(uIn);
                        AdjList.get(uIn).add(sIdx);
                        res[sIdx][uIn] = 1;
                    }
                    else if(map[i][j] == '#'){
                        AdjList.get(tIdx).add(uOut);
                        AdjList.get(uOut).add(tIdx);
                        res[uOut][tIdx] = P;
                    }
                }
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

            line = sc.readLine();
        }
    }
}
