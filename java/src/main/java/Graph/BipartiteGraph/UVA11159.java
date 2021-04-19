package Graph.BipartiteGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 problem: https://onlinejudge.org/external/111/11159.pdf
 level:
 solution: This is to find independence set, formula: Maximum bipartite matching = V - Maximum independent set.

 #MCBM #bipartiteGraph

 **/

public class UVA11159 {
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
            int[] aset = new int[a];
            for(int p=0; p<a; ++p)
                aset[p] = sc.nextInt();

            int b = sc.nextInt();
            int[] bset = new int[b];
            for(int p=0; p<b; ++p)
                bset[p] = sc.nextInt();

            adjList = new ArrayList<>();
            for(int p=0; p<a+b; ++p)
                adjList.add(new ArrayList<>());

            vis = new int[a+b];
            match = new int[a+b];
            for(int p=0; p<a+b; ++p)
                match[p] = -1;

            for(int p=0; p<a; ++p){
                for(int q=0; q<b; ++q){
                    if(bset[q] == 0 || (aset[p] == 0 && bset[q] == 0) || (aset[p] != 0 && bset[q] % aset[p] == 0)) {
                        adjList.get(p).add(a + q);
                    }
                }
            }

            for (int l = 0; l < a; l++) { // n = size of the left set
                for(int q=0; q<a; ++q)
                    vis[q] = 0;

                MCBM += Aug(l);
            }

            String result = String.format("Case %d: %d", i+1, MCBM);
            System.out.println(result);
        }
    }
}
