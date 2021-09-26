package Facebook.Y2021.Round2;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class B {
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round2\\B-input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round2\\B-output.txt";
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
            System.out.println("\033[0;34m" + s + "\033[0;30m");
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
    ArrayList<Edge>[] adjList = new ArrayList[800001];
    int[] f = new int[800001];
    int N;
    ArrayList<Edge> edgeList = new ArrayList<>();

    private long solve() {
        //simply mark node as non-deletable
        TreeMap<Integer, Set<Integer>> freqz = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            if(freqz.containsKey(f[i]))
                freqz.get(f[i]).add(i);
            else{
                TreeSet<Integer> a = new TreeSet<>();
                a.add(i);
                freqz.put(f[i], a);
            }
        }
        Set<Integer> freqzKeySet = freqz.keySet();
        boolean[] important = new boolean[N];
        boolean hasImportant = false;
        Iterator<Integer> it =freqzKeySet.iterator();
        while(it.hasNext()){
            int curf = it.next();
            if(freqz.get(curf).size() > 1){
                Set<Integer> aa = freqz.get(curf);
                for (int iaa: aa) {
                    important[iaa] = true;
                    hasImportant = true;
                }
            }
        }


        //if no such node, remove all edge
        if(!hasImportant) return N-1;

        for (int i = 0; i < N; i++) {
            visited[i] = false;
        }


        dfs(1);

        int result = 0;
        for (Edge e: edgeList) {
            Set<Integer> tt = new TreeSet<>(e.uSide);
            tt.retainAll(e.vSide);
            if(tt.size() == 0)
                result++;
        }

        return result;
    }

    public Set<Integer> dfs(int u){
        visited[u] =true;
        TreeMap<Integer, Integer> multiset = new TreeMap<>();
        multiset.put(f[u], 1);

        for (int i = 0; i<adjList[u].size() ; i++) {
            Edge e = adjList[u].get(i);

            int v = e.u == u ? e.v : e.u;

            if(!visited[v]){
                Set<Integer> r = dfs(v);
                addToMS(multiset, r);

                if(e.u == u) e.vSide = r;
                else e.uSide = r;
            }
        }

        for (int i = 0; i < adjList[u].size(); i++) {
            Edge e = adjList[u].get(i);
            Set<Integer> mySide = minusMS(multiset, e.u==u ? e.vSide : e.uSide);
            if(e.u == u)
                e.uSide = mySide;
            else
                e.vSide = mySide;
        }

        return multiset.keySet();
    }

    public void addToMS(TreeMap<Integer, Integer> ms, Set<Integer> a){
        for(int ia: a){
            if(ms.containsKey(ia)){
                int val = ms.get(ia)+1;
                ms.put(ia, val);
            }
            else
                ms.put(ia, 1);

        }
    }

    public Set<Integer> minusMS(TreeMap<Integer, Integer> ms, Set<Integer> m){
        Set<Integer> r = new TreeSet<Integer>();
        for (int ims: ms.keySet()) {
            if(!m.contains(ims))
                r.add(ims);
            else if(ms.get(ims) > 1)
                r.add(ims);
        }
        return r;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            N = sc.nextInt();

            for (int j = 0; j <= N; j++) {
                if(adjList[j] != null)
                    adjList[j].clear();
                else
                    adjList[j] = new ArrayList<>();
            }
            edgeList.clear();

            for (int j = 0; j < N-1; j++) {
                int a = sc.nextInt()-1;
                int b = sc.nextInt()-1;

                Edge e = new Edge(a, b);
                adjList[a].add(e);
                adjList[b].add(e);
                edgeList.add(e);
            }

            for (int j = 0; j < N; j++) {
                f[j] =sc.nextInt();
            }

            long r = solve();

            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new B().run();
    }

    static class Edge{
        public int u;
        public int v;
        public Set<Integer> uSide;
        public Set<Integer> vSide;
        public Edge(int u, int v){
            this.u = u;
            this.v = v;
            this.uSide = new TreeSet<Integer>();
            this.vSide = new TreeSet<Integer>();
        }
    }
}
