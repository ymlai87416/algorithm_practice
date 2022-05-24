package ctci.treegraph;
import java.util.*;

public class RouteBetweenNodes {

    List<List<Integer>> adjList;
    int source;
    int target;
    boolean[] visited;

    public boolean RouteBetweenNodes(List<List<Integer>> adjList, int source, int target){
        this.adjList = adjList;
        int n = adjList.size();
        this.source = source;
        this.target = target;
        visited = new boolean[n];
        Arrays.fill(visited, false);

        return dfs(source);
    }

    private boolean dfs(int u){
        visited[u] = true;

        if(u == target)
            return true;

        for(int i=0; i< adjList.get(u).size(); ++i){
            int v = adjList.get(u).get(i);

            if(!visited[v]){
                boolean r = dfs(v);
                if(r) return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

    }
}
