package Graph.MSTStandard;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Tom on 11/5/2016.
 *
 * problem: https://onlinejudge.org/external/116/11631.pdf
 *
 * #UVA #Lv2 #mst
 */
public class UVA11631 {
    static class UnionFind { // OOP style
        int[] p;
        int[] rank;

        UnionFind(int N) {
            p = new int[N];
            rank = new int[N];
            for (int i = 0; i < N; i++) p[i] = i;
        }

        void init(){
            for (int i = 0; i < p.length; i++) p[i] = i;
            Arrays.fill(rank, 0);
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
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        Stack<Tuple> EdgeList = new Stack<Tuple>(); // (weight, two vertices) of the edge

        while(true){
            int m = sc.nextInt();
            int n = sc.nextInt();

            if(n==0 && m == 0) break;

            EdgeList.clear();
            int totalsum = 0;
            for(int i=0; i<n; ++i){
                int a = sc.nextInt();
                int b = sc.nextInt();
                int len = sc.nextInt();
                totalsum += len;
                EdgeList.push(new Tuple(len, a, b));
            }

            Collections.sort(EdgeList);

            int mst_cost = 0;

            UnionFind UF = new UnionFind(m);
            UF.init();

            for (int i = 0; i < n; i++) {
                Tuple front = EdgeList.get(i);
                if (!UF.isSameSet(front.second, front.third)) {
                    mst_cost += front.first;
                    UF.unionSet(front.second, front.third);
                }
            }

            System.out.println(totalsum - mst_cost);
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
}
