package Facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    An small experiment why hackercup always ask 8,000,000 nodes, have to increase stack size to 512MB in order the recursive
    DFS to run...

 */

public class LargeTreeDFS {

    int limit = 8000000;

    List<Integer>[] adjList = new ArrayList[limit];
    boolean[] visited = new boolean[limit];

    private void createLargeTree(){

        for (int i = 0; i < limit; i++) {
            adjList[i] = new ArrayList<>();
            if(i!=limit-1)
                adjList[i].add(i+1);
        }

    }

    private void dfs(int u){
        visited[u] = true;
        for (int i = 0; i < adjList[u].size(); i++) {
            int v = adjList[u].get(i);
            if(!visited[v])
                dfs(adjList[u].get(i));
        }
    }

    int[] dfs_num = new int[limit];
    int[] dfs_parent = new int[limit];

    final int EXPLORED =1; final int VISITED =2; final int UNVISITED=0;

    private void graphCheck(int u){
        dfs_num[u] = EXPLORED; // color u as EXPLORED instead of VISITED
        for (int j = 0; j < (int)adjList[u].size(); j++) {
            int v = adjList[u].get(j);
            if (dfs_num[v] == UNVISITED) { // Tree Edge, EXPLORED->UNVISITED
                dfs_parent[v] = u; // parent of this children is me
                graphCheck(v);
            }
            /*
            else if (dfs_num[v] == EXPLORED) { // EXPLORED->EXPLORED
                if (v == dfs_parent[u]) // to differentiate these two cases
                    printf(" Two ways (%d, %d)-(%d, %d)\n", u, v, v, u);
                else // the most frequent application: check if the graph is cyclic
                    printf(" Back Edge (%d, %d) (Cycle)\n", u, v);
            }
            else if (dfs_num[v] == VISITED) // EXPLORED->VISITED
                printf(" Forward/Cross Edge (%d, %d)\n", u, v);

             */
        }
        dfs_num[u] = VISITED; // after recursion, color u as VISITED (DONE)
    }


    int[] L = new int[2*limit];
    int[] E = new int[2*limit];
    int[] H = new int[limit];
    int idx;

    private void dfs2(int cur, int depth){
        H[cur] = idx;
        E[idx] = cur;
        L[idx++] = depth;
        for (int i = 0; i < adjList[cur].size(); i++) {
            dfs2(adjList[cur].get(i), depth+1);
            E[idx] = cur; // backtrack to current node
            L[idx++] = depth;
        }
    }

    private void buildRMQ(){
        idx = 0;
        Arrays.fill(H, 0);
        dfs2(0, 0);
    }

    public void run(){
        createLargeTree();
        //dfs(0);
        //graphCheck(0);  //normal setting: stack broken at 1024
                            //-Xss512m 36sec for this case, 256m stack overflow.
        buildRMQ(); //under a minute
    }

    public static void main(String[] args){
        new LargeTreeDFS().run();

    }
}
