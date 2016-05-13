package Graph.MSTStandard;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Tom on 11/5/2016.
 */
public class UVA11747 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        UnionFind UF = new UnionFind(1001);

        while(true){
            int n = sc.nextInt();
            int m = sc.nextInt();

            if(n == 0 && m == 0)
                break;

            Stack<Tuple> EdgeList = new Stack<Tuple>(); // (weight, two vertices) of the edge

            for(int i=0; i<m; ++i){
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();

                EdgeList.push(new Tuple(w, u, v));
            }

            Collections.sort(EdgeList);

            int mst_cost = 0;

            Stack<Tuple> result = new Stack<Tuple>();

            UF.init();

            for (int i = 0; i < m; i++) {
                Tuple front = EdgeList.get(i);
                if (!UF.isSameSet(front.second, front.third)) {
                    mst_cost += front.first;
                    UF.unionSet(front.second, front.third);
                } else {
                    result.add(EdgeList.get(i));
                }
            }

            if(result.size() == 0)
                System.out.println("forest");
            else {
                Collections.sort(result);
                //Collections.reverse(result);

                for(int i=0; i<result.size(); ++i) {
                    if (i != 0) System.out.print(" ");
                    System.out.print(result.get(i).first);
                }
                System.out.println();
            }
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
}
