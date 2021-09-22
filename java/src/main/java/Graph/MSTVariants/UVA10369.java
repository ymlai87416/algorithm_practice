package Graph.MSTVariants;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Tom on 11/5/2016.
 *
 * problem: https://onlinejudge.org/external/103/10369.pdf
 *
 * #UVA #Lv2 #mst
 */
public class UVA10369 {
    static int[] x = new int[501];
    static int[] y = new int[501];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Stack<Tuple> EdgeList = new Stack<Tuple>(); // (weight, two vertices) of the edge\
        int nc = sc.nextInt();

        for(int ci=0; ci<nc; ++ci){
            int s = sc.nextInt();
            int p = sc.nextInt();

            for(int i=0; i<p; ++i){
                int dx = sc.nextInt();
                int dy = sc.nextInt();

                x[i] = dx;
                y[i] = dy;
            }

            EdgeList.clear();

            for(int i=0; i<p; ++i){
                for(int j=i+1; j<p; ++j){
                    EdgeList.push(new Tuple((x[i]-x[j])*(x[i]-x[j]) + (y[i]-y[j])*(y[i]-y[j]), i, j));
                }
            }

            Collections.sort(EdgeList);

            int mst_cost = 0;
            double maxdist = 0;

            UnionFind UF = new UnionFind(p);
            UF.init();

            for (int i = 0; i < EdgeList.size(); i++) {
                if(UF.getNumSet() <= s) break;

                Tuple front = EdgeList.get(i);
                if (!UF.isSameSet(front.second, front.third)) {
                    mst_cost += front.first;
                    UF.unionSet(front.second, front.third);
                    maxdist = Math.max(maxdist, Math.sqrt(1.0*front.first));
                }
            }

            System.out.format("%.2f\n", maxdist);
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
