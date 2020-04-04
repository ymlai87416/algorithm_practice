package Facebook.Y2015.Round1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Tom on 10/1/2017.
 * 22:47, 0:10
 */
public class Problem4FastIO {
    static String   FILENAME;
    static BufferedReader sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "C:\\Users\\Tom\\Documents\\algorithm_practice\\corporate_gifting (2)";
            IN = FILENAME + ".txt";
            OUT = FILENAME + ".out";
            sc = new BufferedReader(new FileReader(new File(IN)));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    final int MAXPRESENT = 100;  //can turn down to 50 and still correct answer
    int[][] dp = new int[200001][MAXPRESENT];
    ArrayList<Integer>[] adjList = new ArrayList[200001];
    boolean[] visited = new boolean[200001];


    private void solve(int k, int[] m) {
        for(int i=0; i<=k; ++i)
            if(adjList[i] == null)
                adjList[i] = new ArrayList<Integer>();
            else
                adjList[i].clear();

        for(int i=1; i<=k; ++i)
            adjList[m[i]].add(i);

        for(int i=1; i<=k; ++i)
            visited[i] = false;

        int maxPresent = Math.min(k, MAXPRESENT);

        Stack<Integer> st = new Stack<Integer>();
        ArrayList<Integer> calcOrder = new ArrayList<Integer>();

        st.push(1);
        calcOrder.add(1);
        visited[1] = true;

        while(!st.empty()){
            int u = st.pop();

            for(int v : adjList[u]){
                if(!visited[v]) {
                    st.push(v);
                    visited[v] = true;
                    calcOrder.add(v);
                }
            }
        }
        /*System.out.println("\nCalculation orders.");
        for(int i=k-1; i>=0; --i){
            System.out.print(calcOrder.get(i) + " ");
        }
        System.out.println();*/

        //then you just pop out the elements and do one by one
        for(int i=k-1; i>=0; --i){
            int u = calcOrder.get(i);
            if(adjList[u].size() == 0){
                for(int j=1; j<maxPresent; ++j)
                    dp[u][j] = j;
            }
            else{
                for(int j=1; j<maxPresent; ++j){            //j mean the present it is going to give.
                    dp[u][j] = j;

                    for(int v : adjList[u]){
                        int smallest = Integer.MAX_VALUE;
                        for(int q=1; q<maxPresent; ++q){
                            if(j == q) continue;
                            if(smallest > dp[v][q])
                                smallest = dp[v][q];
                        }
                        dp[u][j] += smallest;
                    }
                }
            }
        }

        int smallest = Integer.MAX_VALUE;
        for(int i=1; i<maxPresent; ++i){
            if(smallest > dp[1][i])
                smallest = dp[1][i];
        }
        out.println(smallest);
        System.out.println(smallest);
    }

    int[] m = new int[200001];

    private void run() throws Exception {

        int t = Integer.parseInt(sc.readLine());
        for (int i = 1; i <= t; i++) {
            System.out.print("Case #" + i + ": ");
            out.print("Case #" + i + ": ");

            int k = Integer.parseInt(sc.readLine());

            String line = sc.readLine();
            StringTokenizer st = new StringTokenizer(line);
            for(int p=1; p<=k; ++p) {
                m[p] = Integer.parseInt(st.nextToken());
            }

            solve(k, m);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        long start_time = System.currentTimeMillis();
        new Problem4FastIO().run();
        long end_time = System.currentTimeMillis();
        long execution_time = (end_time - start_time);

        System.out.println(String.format("Total runtime: %dms", execution_time));

    }
}
