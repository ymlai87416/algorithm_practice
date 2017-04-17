package Graph.NetworkFlow.StandardMaxFlowProblem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by ymlai on 16/4/2017.
 */
public class UVA259 {
    final static int sIdx = 36;
    final static int tIdx = 37;
    final static int baseCompIdx = 26;
    final static int maxV = 38;
    final static int INF = Integer.MAX_VALUE;
    final static List<List<Integer>> AdjList = new ArrayList<>();
    final static String jobIdx = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final static String compIdx = "0123456789";

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

    public static void main(String[] args) throws Exception{
        if(args.length>=1)
            System.setIn(new FileInputStream(args[0]));

        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<maxV; ++i)
            AdjList.add(new ArrayList<>());

        while(true){
            for(int i=0; i<maxV; ++i)
                Arrays.fill(res[i], 0);

            for(int i=0; i<maxV; ++i)
                AdjList.get(i).clear();

            //init computer
            for(int i=baseCompIdx; i<baseCompIdx+10; ++i){
                AdjList.get(i).add(tIdx);
                AdjList.get(tIdx).add(i);
                res[i][tIdx] = 1;
            }

            String line = sc.readLine();
            if(line == null) break;

            int totalJobNum =0;

            while(line != null && line.trim().length() > 0){
                String[] token = line.split(" ");
                String jobdesc = token[0];
                int jIdx = jobIdx.indexOf(jobdesc.charAt(0));
                int numJob = compIdx.indexOf(jobdesc.charAt(1));
                totalJobNum += numJob;
                String canRun = token[1];

                AdjList.get(sIdx).add(jIdx);
                AdjList.get(jIdx).add(sIdx);
                res[sIdx][jIdx] = numJob;

                for(int i=0; i<canRun.length(); ++i){
                    if(canRun.charAt(i) == ';') break;
                    int comp = compIdx.indexOf(canRun.charAt(i)) + baseCompIdx;

                    AdjList.get(comp).add(jIdx);
                    AdjList.get(jIdx).add(comp);
                    res[jIdx][comp] = 1;
                }
                line = sc.readLine();
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


            if(mf < totalJobNum){
                System.out.println("!");
            }
            else{
                //check backward edge comp -> job to find the assignment
                String result ="";
                for(int i=baseCompIdx; i<baseCompIdx+10; ++i){
                    char a = '_';
                    for(int j=0; j<baseCompIdx; ++j){
                        if(res[i][j] == 1) {
                            a = jobIdx.charAt(j);
                            break;
                        }
                    }
                    result = result + a;
                }
                System.out.println(result);
            }
        }


    }
}
