package GoogleCodeJam.Y2022.Quali.D;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/GoogleCodeJam/Y2022/Quali/D/D-test.in";
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

    private long solve(int N, int[] val, int[] in, int[] next) {
        //sort the shit first
        int[] tsort = new int[N+1];
        //bfs
        int ptr = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=0; i<=N; ++i){
            if(in[i] == 0) {
                q.offer(i);
                tsort[ptr++] = i;
            }
        }
        while(!q.isEmpty()){
            int u = q.poll();
            int v = next[u];
            in[v]--;
            if(in[v] == 0) {
                q.offer(v);
                tsort[ptr++] = v;
            }
        }

        //debug
        /*
        for(int i=0; i<=N; ++i)
            System.out.print(tsort[i] + " ");
        System.out.println();
         */

        //now we have the sort, we do it
        HashMap<Integer, List<Integer>> junction = new HashMap<>();
        long result = 0;
        for(int i=0; i<=N; ++i){
            int u = tsort[i];
            if(val[u] == -1){
                //add all sum to the answer
                List<Integer> ll = junction.get(u);
                for(Integer li : ll)
                    result += li;
                //end here
                break;
            }
            int pass;
            if(junction.containsKey(u)){
                //find the smallest, and pass it to next
                List<Integer> ll = junction.get(u);
                int smallest = ll.get(0);
                long sum = 0;
                for(Integer li: ll) {
                    smallest = Math.min(li, smallest);
                    sum += li;
                }

                //add up all and minus the smallest
                result = result +sum - smallest;

                if(smallest < val[u])
                    pass = val[u];
                else
                    pass = smallest;
            }
            else
                pass = val[u];

            int v = next[u];
            if(junction.containsKey(v))
                junction.get(v).add(pass);
            else{
                ArrayList<Integer> al = new ArrayList<>();
                al.add(pass);
                junction.put(v, al);
            }
        }

        return result;
    }


    int[] v = new int[100_001];
    List<Integer>[] adjList = new List[100_001];
    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            int[] v = new int[N+1];
            int[] in = new int[N+1];
            int[] next = new int[N+1];
            v[0] = -1;
            next[0] = 0;
            for (int j = 1; j <= N; j++) {
                v[j] = sc.nextInt();
            }
            for (int j = 0; j < N; j++) {
                int a = sc.nextInt();
                in[a]++;
                next[j+1] = a;
            }

            System.out.println(solve(N, v, in, next));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}