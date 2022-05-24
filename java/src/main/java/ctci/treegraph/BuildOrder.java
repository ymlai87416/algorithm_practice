package ctci.treegraph;
import java.util.*;

public class BuildOrder {
    List<String> order;
    List<List<Integer>> adjList;
    HashMap<String, Integer> lookup;

    List<String> projects;

    int[] visited;
    final int NOT_VISITED=0;
    final int EXPLORING=1;
    final int VISITED=2;

    public List<String> buildOrder(List<String> projects, List<String[]> dependencies){
        order = new ArrayList<>();
        adjList = new ArrayList<>();
        lookup = new HashMap<>();
        this.projects = projects;
        int N = projects.size();

        for(int i=0; i<projects.size(); ++i){
            lookup.put(projects.get(i), i);
            adjList.add(new ArrayList<Integer>());
        }

        for(int i=0; i<dependencies.size(); ++i){
            int u = lookup.get(dependencies.get(i)[0]);
            int v = lookup.get(dependencies.get(i)[1]);
            adjList.get(u).add(v);
        }

        visited = new int[N];
        Arrays.fill(visited, NOT_VISITED);

        for(int i=0; i<N; ++i)  //does it matters if we start in any orders?
            if(visited[i] == NOT_VISITED) {
                boolean hasCycle = dfs(i);
                if(hasCycle) return null;
            }

        Collections.reverse(order);

        return order;
    }

    private boolean dfs(int u){
        visited[u] = EXPLORING;

        for(int i=0; i<adjList.get(u).size(); ++i){
            int v = adjList.get(u).get(i);
            if(visited[v] == NOT_VISITED) {
                boolean r = dfs(v);
                if(r)
                    return true;
            }
            else if(visited[v] == EXPLORING)
                return true;
        }

        order.add(projects.get(u));
        visited[u] = VISITED;
        return false;
    }


    public static void main(String[] args) {
        BuildOrder test = new BuildOrder();

        List<String> projects  = List.of("a", "b","c","d","e","f");
        List<String[]> dependencies = List.of(new String[]{"a", "d"}, new String[]{"f", "b"},
                new String[]{"b", "d"},
                new String[]{"f", "a"},new String[]{"d", "c"});

        var result = test.buildOrder(projects, dependencies);

        System.out.println(result);
    }
}
