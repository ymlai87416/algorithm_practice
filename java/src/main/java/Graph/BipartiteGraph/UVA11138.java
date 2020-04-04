package Graph.BipartiteGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ymlai on 12/4/2017.
 */
public class UVA11138 {

    static List<List<Integer>> adjList;
    static int[] match;
    static int[] vis;

    private static int Aug(int l) { // return 1 if an augmenting path is found
        if (vis[l] != 0) return 0; // return 0 otherwise
        vis[l] = 1;
        for (int j = 0; j < adjList.get(l).size(); j++) {
            int r = adjList.get(l).get(j); // edge weight not needed -> vector<vi> AdjList
            if ((match[r] == -1) || Aug(match[r]) != 0) {
                match[r] = l; return 1; // found 1 matching
            } }
        return 0; // no matching
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for(int i=0; i<t; ++i){
            int MCBM = 0;

            int a = sc.nextInt();
            int b = sc.nextInt();

            adjList = new ArrayList<>();
            for(int p=0; p<a+b; ++p)
                adjList.add(new ArrayList<>());

            vis = new int[a+b];
            match = new int[a+b];
            for(int p=0; p<a+b; ++p)
                match[p] = -1;

            for(int p=0; p<a; ++p){
                for(int q=0; q<b; ++q){
                    int c = sc.nextInt();
                    if(c == 1)
                        adjList.get(p).add(a+q);
                }
            }

            for (int l = 0; l < a; l++) { // n = size of the left set
                for(int q=0; q<a; ++q)
                    vis[q] = 0;

                MCBM += Aug(l);
            }

            String result = String.format("Case %d: a maximum of %d nuts and bolts can be fitted together", i+1, MCBM);
            System.out.println(result);
        }
    }
}
