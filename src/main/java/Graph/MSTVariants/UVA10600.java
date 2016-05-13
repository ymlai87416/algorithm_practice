package Graph.MSTVariants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Tom on 11/5/2016.
 */
public class UVA10600 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int ci=0; ci<nc; ++ci){
            int n = sc.nextInt();
            int m = sc.nextInt();

            ArrayList<Tuple> edgeList = new ArrayList<Tuple >();
            for(int i=0; i<m; ++i){
                int a = sc.nextInt();
                int b = sc.nextInt();
                int c = sc.nextInt();

                edgeList.add(new Tuple(c, a-1, b-1));
            }

            Collections.sort(edgeList);

            int mst_cost = 0;

            UnionFind UF = new UnionFind(n);
            ArrayList<Tuple> result = new ArrayList<Tuple>();

            UF.init();

            for (int i = 0; i < edgeList.size(); i++) {
                Tuple front = edgeList.get(i);
                if (!UF.isSameSet(front.second, front.third)) {
                    mst_cost += front.first;
                    result.add(front);
                    UF.unionSet(front.second, front.third);
                }
            }

            int second_mst_cost = Integer.MAX_VALUE;

            for(int p=0; p<result.size(); ++p){
                Tuple ban = result.get(p);
                int temp_mst_cost = 0;
                int mst_edges = 0;
                UF.init();
                for (int i = 0; i < edgeList.size(); i++) {
                    Tuple front = edgeList.get(i);
                    if(front == ban) continue;
                    if (!UF.isSameSet(front.second, front.third)) {
                        temp_mst_cost += front.first;
                        UF.unionSet(front.second, front.third);
                        mst_edges++;
                    }
                }

                if(mst_edges != n-1) temp_mst_cost = Integer.MAX_VALUE;
                second_mst_cost = Math.min(second_mst_cost, temp_mst_cost);
            }


            System.out.format("%d %d\n", mst_cost, second_mst_cost);
        }
    }

    static class Tuple implements Comparable<Tuple>{
        int first;
        int second, third;
        public Tuple(int a, int b, int c){
            first = a;
            second = b;
            third = c;
        }

        @Override
        public int compareTo(Tuple o) {
            if(first < o.first) return -1;
            else if(first > o.first) return 1;
            else{
                if(second < o.second) return -1;
                else if(second > o.second) return 1;
                else{
                    if(third < o.third) return -1;
                    else if(third > o.third) return 1;
                    else return 0;
                }
            }
        }
    }

    static class UnionFind { // OOP style
        int[] p;
        int[] rank;
        int numSet;

        UnionFind(int N) {
            p = new int[N];
            rank = new int[N];
            for (int i = 0; i < N; i++) p[i] = i;
        }

        void init(){
            for (int i = 0; i < p.length; i++) p[i] = i;
            Arrays.fill(rank, 0);
            numSet = p.length;
        }
        int findSet(int i) { return (p[i] == i) ? i : (p[i] = findSet(p[i])); }

        boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

        void unionSet(int i, int j) {
            if (!isSameSet(i, j)) { // if from different set
                int x = findSet(i), y = findSet(j);
                if (rank[x] > rank[y]) {
                    p[y] = x; // rank keeps the tree short
                }
                else {
                    p[x] = y;
                    if (rank[x] == rank[y])
                        rank[y]++;
                }
                numSet--;
            }
        }

        int getNumSet(){return numSet;}
    }
}
