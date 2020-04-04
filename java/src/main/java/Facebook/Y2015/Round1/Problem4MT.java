package Facebook.Y2015.Round1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Tom on 10/1/2017.
 * 22:47, 0:10
 */
public class Problem4MT {
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


    private int solve(int k, int[] m) {

        int[][] dp = new int[k+1][MAXPRESENT];
        ArrayList<Integer>[] adjList = new ArrayList[k+1];
        boolean[] visited = new boolean[k+1];


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

        return smallest;

    }



    private void run() throws Exception {

        int t = Integer.parseInt(sc.readLine());

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        Future<Integer>[] fs = new Future[t];
        for(int i=0; i<t; ++i){
            int k = Integer.parseInt(sc.readLine());
            int[] m = new int[k+1];

            String line = (sc.readLine());
            StringTokenizer st = new StringTokenizer(line);
            for(int p=1; p<=k; ++p) {
                m[p] = Integer.parseInt(st.nextToken());
            }

            fs[i] = executorService.submit(() -> solve(k, m));
        }

        for (int i = 1; i <= t; i++) {
            System.out.print("Case #" + i + ": ");
            out.print("Case #" + i + ": ");

            int smallest = fs[i-1].get();

            out.println(smallest);
            System.out.println(smallest);
        }
        sc.close();
        out.close();

        executorService.shutdown();
    }

    public static void main(String args[]) throws Exception {
        long start_time = System.currentTimeMillis();
        new Problem4MT().run();
        long end_time = System.currentTimeMillis();
        long execution_time = (end_time - start_time);

        System.out.println(String.format("Total runtime: %dms", execution_time));

    }
}
