package DataStructure.UnionFind;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Tom on 11/5/2016.
 */
public class UVA11503 {

    static class UnionFind { // OOP style
        int[] p;
        int[] rank;
        int[] size;

        UnionFind(int N) {
            p = new int[N];
            rank = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) p[i] = i;
        }

        void init(){
            for (int i = 0; i < p.length; i++) p[i] = i;
            Arrays.fill(rank, 0);
            Arrays.fill(size, 1);
        }
        int findSet(int i) { return (p[i] == i) ? i : (p[i] = findSet(p[i])); }

        boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

        void unionSet(int i, int j) {
            if (!isSameSet(i, j)) { // if from different set
                int x = findSet(i), y = findSet(j);
                if (rank[x] > rank[y]) {
                    p[y] = x; // rank keeps the tree short
                    size[x] += size[y];
                }
                else {
                    p[x] = y;
                    size[y] += size[x];
                    if (rank[x] == rank[y])
                        rank[y]++;
                }
            }
        }

        int getSetSize(int i){
            return size[i];
        }
    };

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        UnionFind uf = new UnionFind(100000);
        int nc = sc.nextInt();
        for(int ci=0; ci<nc; ++ci){
            int nf = sc.nextInt();

            TreeMap<String, Integer> mapper = new TreeMap<String, Integer>();
            TreeMap<Integer, Integer> friend = new TreeMap<Integer, Integer>();
            int cnt = 0;
            uf.init();
            for(int i=0; i<nf; ++i){
                String a = sc.next();
                String b = sc.next();

                Integer aidx = mapper.get(a); if(aidx == null) mapper.put(a, cnt++);
                Integer bidx = mapper.get(b); if(bidx == null) mapper.put(b, cnt++);

                aidx = mapper.get(a);
                bidx = mapper.get(b);

                uf.unionSet(aidx, bidx);
                int sIdx = uf.findSet(aidx);
                int ans = uf.getSetSize(sIdx);

                System.out.println(ans);
            }
        }
    }
}
