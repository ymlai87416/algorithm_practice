package Leetcode;

import java.util.*;

public class NumberOfIslandII {

    public static void main(String[] args){
        NumberOfIslandII c = new NumberOfIslandII();
        //int[][]  a = new int[][]{{0,0},{0,1},{1,2},{2,1}};
        int[][]  a = new int[][]{{2,2},{2,1},{1,3},{0,4}};
        c.numIslands2(3,6, a);

    }

    int[][] diff = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {

        UnionFind uf = new UnionFind(positions.length);
        HashMap<Integer, Integer> hm =  new HashMap<>();

        List<Integer> a = new ArrayList<>();
        int cnt = 0;

        for(int i=0; i<positions.length; ++i){
            int cx = positions[i][0];
            int cy = positions[i][1];

            int gridNum = cx * n + cy;
            if(hm.containsKey(gridNum)){
                a.add(a.get(a.size()-1));
                continue;
            }

            hm.put(gridNum, cnt);
            for(int j=0; j<diff.length; ++j){
                int nx =cx+diff[j][0];
                int ny=cy+diff[j][1];

                if(0 <= nx && nx < m && 0 <=ny && ny < n){
                    int nGridNum =nx*n + ny;

                    if(hm.containsKey(nGridNum)){
                        int nSet = hm.get(nGridNum);
                        uf.unionSet(nSet, cnt);

                    }
                }

            }

            //after all the linkage, we need to output the number of island
            int island = 0;
            for(int j=0; j<= cnt; ++j){
                if(uf.findSet(j) == j) island++;
            }
            a.add(island);

            ++cnt;
        }

        return a;
    }

    static class UnionFind{


        int[] p;
        int[] rank;
        int[] size;

        public UnionFind(int N){
            p = new int[N];
            rank = new int[N];
            size = new int[N];
            Arrays.fill(rank, 0);
            Arrays.fill(size, 1);
            for(int i=0; i<N; ++i)
                p[i] = i;
        }

        int findSet(int a){

            if(p[a] ==a)
                return a;
            //System.out.println("D " + a + " " + p[a]);
            p[a] = findSet(p[a]);  //the path compresion
            return p[a];
        }

        void unionSet(int a, int b){
            //System.out.println("D unionSet");
            int as = findSet(a);
            int bs = findSet(b);
            if(as == bs) return;

            if(rank[as] < rank[bs]){
                p[as] = bs;
                size[bs] += size[as];
            }
            else{
                p[bs] = as;
                size[as] += size[bs];
                if(rank[as] == rank[bs])
                    rank[as]++;
            }
        }

        int getSize(int a){
            return size[a];
        }
    }
}
