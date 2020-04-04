package Graph.UnweightedBFSEasier;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Tom on 8/5/2016.
 *
 * Start from 22:32 and finished at 23:01, should be shorter...
 */
public class UVA924 {
    static int[] adjcnt = new int[2501];
    static int[][] adj = new int[2501][2501];
    static int[] visited = new int[2501];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nemploy = sc.nextInt();

        for(int i=0; i<nemploy; ++i){
            int nc = sc.nextInt();
            for(int j=0; j<nc; ++j){
                adj[i][adjcnt[i]++] = sc.nextInt();
            }
        }

        int nq = sc.nextInt();
        for(int i=0; i<nq; ++i){
            int firstboom = 0;
            int maxboom = 0;

            int src = sc.nextInt();

            Arrays.fill(visited, -1);

            Queue<Integer> queue = new ArrayDeque<>();

            visited[src] = 0;
            queue.add(src);

            while(!queue.isEmpty()){
                int u = queue.poll();

                for(int p=0; p<adjcnt[u]; ++p){
                    int v = adj[u][p];
                    if(visited[v] != -1) continue;
                    queue.add(v);
                    visited[v] = visited[u]+1;
                }
            }

            Arrays.sort(visited, 0, nemploy);
            int cnt = 0;
            while(visited[cnt] == -1) ++cnt;
            ++cnt;  //skip 0

            if(cnt == nemploy)
                System.out.println(0);
            else{
                int ccnt = 1;
                for(int p=cnt+1; p<nemploy; ++p){
                    if(visited[p] == visited[p-1]){
                        ccnt++;
                    } else{
                        if(maxboom < ccnt){
                            firstboom = visited[p-1];
                            maxboom = ccnt;
                        }
                        ccnt = 1;
                    }
                }
                if(maxboom < ccnt) {
                    firstboom = visited[nemploy-1];
                    maxboom = ccnt;
                }

                System.out.println(maxboom + " " + firstboom);
            }
        }
    }
}
