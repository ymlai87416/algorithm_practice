package Graph.GraphTraversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Tom on 23/4/2016.
 *
 * problem: https://onlinejudge.org/external/119/11902.pdf
 *
 * #UVA #Lv3 #connected_component #skip
 */
public class UVA11902 {

    static boolean[] visited;
    static int[] adjcnt;
    static int[][] adj;
    static int V;

    public static void dfs(int s, int skip) {
        visited = new boolean[V];

        visited[skip] =true;
        // depth-first search using an explicit stack
        Stack<Integer> stack = new Stack<Integer>();
        visited[s] = true;
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            int w = -1;
            for(int i=0; i<adjcnt[v]; ++i) {
                if (!visited[adj[v][i]])
                    w = adj[v][i]; break;
            }
            if(w != -1){
                visited[w] = true;
                stack.push(w);
            }
            else {
                stack.pop();
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for(int i=0; i<t; ++i){
            int size = sc.nextInt();
            V = size;
            adjcnt = new int[size];
            adj = new int[size][size];
            Arrays.fill(adjcnt, 0);

            for(int p=0; p<size; ++p){
                for(int q=0; q<size; ++q){
                    int d = sc.nextInt();
                    if(d ==1) adj[p][adjcnt[p]++] = q;
                }
            }

            System.out.println("Case " + (i+1) + ":");
            System.out.println("+---------+");

            for(int xt=0; xt<size; ++xt){
                System.out.print("|");
                System.out.print("Y");
            }
            System.out.println("|");
            System.out.println("+---------+");
            for(int p=1; p<size; ++p){
                dfs(0, p);
                visited[p] = false;
                for(int xt=0; xt<size; ++xt){
                    System.out.print("|");
                    System.out.print(visited[xt] ? "N" : "Y");
                }
                System.out.println("|");
                System.out.println("+---------+");
            }
        }
    }
}
