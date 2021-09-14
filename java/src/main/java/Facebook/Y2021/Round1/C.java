package Facebook.Y2021.Round1;

import Facebook.Y2019.Quali.B;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

import static java.lang.Integer.parseInt;

public class C {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            //IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\A2-test.in";
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round1\\blockchain_validation_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round1\\blockchain_validation_output.txt";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            if(OUT == null)
                out = new PrintStream(System.out);
            else out = new PrintStream(OUT);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.print("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private void debug(Object... s){
        if(debugflag) {
            //System.out.println(s);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                sb.append(s[i].toString() + " ");
            }
            System.out.println("\033[0;34m" + sb.toString() + "\033[0;30m");
        }
    }

    boolean[] visited = new boolean[800001];
    int[] size = new int[800001];
    HashMap<Integer, ArrayList<Edge>> nodes = new HashMap<>();
    int N;
    ArrayList<Edge> edges = new ArrayList<>();

    private long solve() {

        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if(o1.cap == o2.cap) {
                    if (o1.u == o2.u)
                        return o1.v - o2.v;
                    else
                        return o1.u - o2.u;
                }
                else
                    return o2.cap - o1.cap;
            }
        });
        
        //now add the edge one by one and then we can
        //a edge is taken out
        //u and v
        //they representing 2 tree, not yet joined.
        //tree1: x1 nodes, x1-1 edges
        //tree2: x2 nodes, x2-1 edges
        //now for each edges in both tree, do the following

        //tree1 edges separate += tree2 node * edge cap
        //tree2 edges separate += tree1 node * edge cap

        //debug("New case:\n");
        nodes.clear();

        long totalSum = 0;
        for (int i = 0; i < edges.size(); i++) {
            Edge currE = edges.get(i);
            //debug("add edge " + currE.u + " " + currE.v + "\n");

            //now traverse both tree
            for (int j = 0; j <= N; j++) {
                visited[j] =false;
                size[i] = 0;
            }
            iterativeCount(currE.u);
            iterativeCount(currE.v);

            for (int j = 0; j <=N; j++) {
                visited[j] = false;
            }
            traverse(currE.u, currE.cap, size[currE.v]);
            traverse(currE.v, currE.cap, size[currE.u]);

            currE.sep += currE.cap * size[currE.u] * size[currE.v];
            totalSum += currE.cap * size[currE.u] * size[currE.v];

            //add the edge to the graph
            addEdge(currE);

            /*
            for (int j = 0; j < edges.size(); j++) {
                if(edges.get(j).active){
                    debug("" + edges.get(j).u + " " + edges.get(j).v + " " + edges.get(j).cap + "\n");
                }
            }
            debug("+====\n");

             */

        }

        //now we have edges with O(N)
        long result = 1;
        for (int i = 0; i < edges.size(); i++) {

            //debug(totalSum - edges.get(i).sep);
            result = result * ((totalSum - edges.get(i).sep) % 1000000007) % 1000000007;
        }
        //debug("\n");

        return result;
    }

    void addEdge(Edge currE){
        currE.active = true;
        if(nodes.containsKey(currE.u)) nodes.get(currE.u).add(currE);
        else{
            ArrayList<Edge> newList = new ArrayList<>();
            newList.add(currE);
            nodes.put(currE.u, newList);
        }
        if(nodes.containsKey(currE.v)) nodes.get(currE.v).add(currE);
        else{
            ArrayList<Edge> newList = new ArrayList<>();
            newList.add(currE);
            nodes.put(currE.v, newList);
        }
    }

    void traverse(int root, int cap, int otherTreeSize){

        Stack<Integer> st = new Stack<Integer>();
        st.push(root);

        //dfs
        while(!st.isEmpty()){
            int u = st.pop();
            visited[u] = true;
            ArrayList<Edge> edges = nodes.get(u);
            if(edges == null) continue;

            for (int i = 0; i < nodes.get(u).size(); i++) {
                Edge e = nodes.get(u).get(i);
                int v = e.u == u ? e.v : e.u;

                if(visited[v]) continue;
                e.sep += 1L * cap * otherTreeSize * size[v];

                st.push(v);
            }
        }

    }


    void iterativeCount(int root){
        Stack<Integer> bottomUpOrder = new Stack<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int u = q.poll();
            visited[u] =true;
            bottomUpOrder.push(u);
            ArrayList<Edge> ue = nodes.get(u);
            boolean isLeave = true;
            if(ue != null) {
                for (int i = 0; i < ue.size(); i++) {
                    Edge e = ue.get(i);
                    int v = e.u == u ? e.v : e.u;

                    if (!visited[v]) {
                        q.offer(v);
                        isLeave = false;
                    }
                }
            }

            if(isLeave) size[u] = 1;
            else size[u] = 0;
        }

        //now we got the leave node on the top, and we can just sum it up
        while(!bottomUpOrder.isEmpty()){
            int u = bottomUpOrder.pop();
            if(size[u] == 1) continue;
            else{
                ArrayList<Edge> ue = nodes.get(u);
                for (int i = 0; i < ue.size(); i++) {
                    Edge e = ue.get(i);
                    int v = e.u == u ? e.v : e.u;
                    if(size[v] == 0) continue; //they are the root, not yet process
                    size[u] += size[v];
                }
                size[u] +=1;
            }
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            N = sc.nextInt();

            int u, v, c;

           edges.clear();
            for (int j = 0; j < N-1; j++) {
                u = sc.nextInt(); v = sc.nextInt(); c = sc.nextInt();
                Edge e = new Edge(u, v, c);
                edges.add(e);
            }

            long r = solve();
            out.println(r);

        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new C().run();
    }

    static class Edge{
        public int cap;
        public int u;
        public int v;
        public long sep;
        public boolean active;
        public Edge(int u, int v, int cap){
            this.u = u; this.v = v; this.cap = cap;
            active = false;
        }
    }

}
