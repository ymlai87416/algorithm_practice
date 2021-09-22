package DataStructure.GraphDataStructure;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Tom on 15/5/2016.
 * problem: https://onlinejudge.org/external/5/599.pdf
 *
 * #Lv2 #UVA #graph
 */
public class UVA599 {
    //static ArrayList<Integer>[] adj = new ArrayList[26];
    static int[] adjcnt = new int[26];
    static int[][] adj = new int[26][26];
    static boolean[] valid = new boolean[26];
    static boolean[] visited = new boolean[26];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int nc = sc.nextInt();
        sc.nextLine();

        for(int ci=0; ci<nc; ++ci){

            Arrays.fill(adjcnt, 0);

            String s;
            while(true){
                s = sc.nextLine();
                if(s.contains("*")) break;
                else{
                    char ss = s.charAt(1);
                    char tt = s.charAt(3);
                    int sidx = ss-'A';
                    int tidx = tt-'A';

                    adj[sidx][adjcnt[sidx]++] = tidx;
                    adj[tidx][adjcnt[tidx]++] = sidx;
                }
            }

            s = sc.next();
            sc.nextLine();
            StringTokenizer st = new StringTokenizer(s, ",");
            Arrays.fill(valid, false);
            Arrays.fill(visited, false);
            while(st.hasMoreTokens()) {
                valid[st.nextToken().charAt(0) - 'A'] = true;
            }

            int ntree = 0;
            int nacron = 0;
            for(int i=0; i<26; ++i){
                int r = 0;
                if(valid[i] && !visited[i]) {
                    r = dfs(i);
                    if (r == 1) nacron++;
                    else ntree++;
                }
            }

            System.out.format("There are %d tree(s) and %d acorn(s).\n", ntree, nacron);
        }
    }

    static int dfs(int s){
        Stack<Integer> stack = new Stack<Integer>();
        int cnt = 0;
        stack.push(s);
        visited[s] = true;

        while(!stack.isEmpty()){
            cnt++;
            int u = stack.pop();
            for(int i=0; i<adjcnt[u]; ++i){
                int v = adj[u][i];
                if(visited[v]) continue;
                visited[v] = true;
                stack.push(v);
            }
        }

        return cnt;
    }
}
