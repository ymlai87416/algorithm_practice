package Graph.UnweightedBFSHarder;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Tom on 14/5/2016.
 */
public class UVA11101 {
    static int[] visited = new int[10000];
    static int[] but = new int[10];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int ci=0;
        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            if(a == 0 && b == 0 && c == 0) break;


            for(int i=0; i<c; ++i){
                but[i] = sc.nextInt();
            }

            Arrays.fill(visited, -1);
            Queue<Integer> queue = new ArrayDeque<>();
            visited[a] = 0;
            queue.add(a);

            while(!queue.isEmpty()){
                int u = queue.poll();

                for(int i=0; i<c; ++i){
                    int v = u+but[i];
                    v = v% 10000;
                    if(visited[v] != -1) continue;
                    queue.add(v);
                    visited[v] = visited[u]+1;
                }
            }

            if(visited[b] == -1)
                System.out.format("Case %d: Permanently Locked\n", ++ci);
            else
                System.out.format("Case %d: %d\n", ++ci, visited[b]);
        }
    }
}
