package GoogleCodeJam.Y2012.Round1C.A;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2012\\Round1C\\A\\A-test.in";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            out = new PrintStream(System.out);
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

    boolean[] visited;
    int N;
    int[] adjCnt;
    List<List<Integer>> adjList;
    private boolean solve(int N, int[] adjCnt, List<List<Integer>> adjList) {
        this.N = N; this.adjCnt = adjCnt; this.adjList = adjList;
        this.visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[j] = false;
            }
            debug("*******");
            boolean r = dfs(i);
            if(r) return true;
        }

        return false;
    }

    private boolean dfs(int u){
        visited[u] = true;

        for (int j = 0; j < adjCnt[u]; j++) {
            int v = adjList.get(u).get(j);
            if(!visited[v]){
                debug("visit from " + (u+1) + " " + (v+1));
                if(dfs(v))
                    return true;
            }
            else{
                debug("loop at " + (u+1) + " " + (v+1));
                return true;
            }
        }
        return false;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int N = sc.nextInt();
            List<List<Integer>>  adjList = new ArrayList<>();
            int[] adjCnt = new int[N];
            for (int j = 0; j < N; j++) {
                adjCnt[j] = sc.nextInt();
                List<Integer> ns = new ArrayList<>();
                for (int k = 0; k < adjCnt[j]; k++) {
                    ns.add(sc.nextInt()-1);
                }
                adjList.add(ns);
            }
            out.print("Case #" + i + ": ");
            out.println(solve(N, adjCnt, adjList) ? "Yes" : "No" );
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}