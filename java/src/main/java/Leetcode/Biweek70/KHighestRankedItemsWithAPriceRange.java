package Leetcode.Biweek70;

import java.util.*;

/**
 problem: https://leetcode.com/problems/k-highest-ranked-items-within-a-price-range/
 level: medium
 solution: bfs and ordering

 #bfs #priority_queue
 **/

public class KHighestRankedItemsWithAPriceRange {

    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        //what do we get here. row # simple, col # simple, we also have to sort the item, and give them a rank, while we
        //do a travelling in the matrix, which is bfs

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        Set<Integer> prices = new TreeSet<Integer>();

        List<List<Integer>> result = new ArrayList<>();

        prices.add(grid[start[0]][start[1]]);
        pq.add(new int[]{start[0], start[1], 0});

        int n = grid.length;
        int m = grid[0].length;

        int[][] score = new int[n][m];

        boolean[][] visited = new boolean[n][m];

        for(int i=0; i<n; ++i){
            for(int j=0; j<m; ++j) {
                score[i][j] = -1;
                visited[i][j] = false;
            }
        }

        visited[start[0]][start[1]] = true;
        score[start[0]][start[1]] = 0;

        while(!pq.isEmpty()){
            int[] u = pq.poll();
            int upr = grid[u[0]][u[1]];
            prices.add(upr);
            score[u[0]][u[1]] = u[2];

            //1 is empty, 0 is wall, other is price
            if(u[0]-1 >=0 && grid[u[0]-1][u[1]] > 0 && !visited[u[0]-1][u[1]]){
                visited[u[0]-1][u[1]] = true;
                pq.add(new int[]{u[0]-1, u[1], u[2]+1});
            }
            if(u[0]+1 <n && grid[u[0]+1][u[1]] > 0 && !visited[u[0]+1][u[1]]){
                visited[u[0]+1][u[1]] = true;
                pq.add(new int[]{u[0]+1, u[1], u[2]+1});
            }
            if(u[1]-1 >=0 && grid[u[0]][u[1]-1] > 0 && !visited[u[0]][u[1]-1]){
                visited[u[0]][u[1]-1] = true;
                pq.add(new int[]{u[0], u[1]-1, u[2]+1});
            }
            if(u[1]+1 <m && grid[u[0]][u[1]+1] > 0 && !visited[u[0]][u[1]+1]){
                visited[u[0]][u[1]+1] = true;
                pq.add(new int[]{u[0], u[1]+1, u[2]+1});
            }
        }

        //finalize score, having a pirority queue would reduce complexity
        PriorityQueue<int[]> scorePq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int d1 = o1[2]; int p1 = o1[3];
                int d2 = o2[2]; int p2 = o2[3];

                //logic here
                if(d1 < d2) return -1;
                else if(d1 > d2) return 1;
                else{
                    if(p1 < p2) return -1;
                    else if(p1 > p2) return 1;
                    else{
                        if(o1[0] < o2[0]) return -1;
                        else if(o1[0] > o2[0]) return 1;
                        else return o1[1] - o2[1];
                    }
                }
            }
        });

        HashMap<Integer, Integer> priceRank = new HashMap<>();
        int order = 0;
        for (Integer p :
                prices) {
            priceRank.put(p, order);
            order+=1;
        }

        for(int i=0; i<n; ++i){
            for (int j = 0; j < m; j++) {
                if(grid[i][j] > 1 && grid[i][j] >= pricing[0] && grid[i][j] <=pricing[1] && score[i][j] != -1) {
                    int ds = score[i][j];

                    int ps = priceRank.get(grid[i][j]);
                    scorePq.add(new int[]{i, j, ds, ps});

                    //System.out.print(ds + "-" + ps + " ");
                }
                else{
                    score[i][j] = Integer.MAX_VALUE;

                    //System.out.print("X-X ");
                }
            }
            //System.out.println("");
        }

        int loopLen = Math.min(k, scorePq.size());
        for (int i = 0; i < loopLen; i++) {
            int[] c = scorePq.poll();
            List<Integer> coord = new ArrayList<Integer>();
            coord.add(c[0]); coord.add(c[1]);
            result.add(coord);
        }

        return result;
    }

    public static void main(String[] args){
        KHighestRankedItemsWithAPriceRange s = new KHighestRankedItemsWithAPriceRange();

        int[][] g1 = {{1,2,0,1},{1,3,0,1},{0,2,5,1}};
        int[] p1 = {2,5};
        int[] s1 = {0,0};

        int[][] g2 = {{1,2,0,1},{1,3,3,1},{0,2,5,1}};
        int[] p2 = {2,3};
        int[] s2 = {2,3};

        int[][] g3 = {{1,1,1},{0,0,1},{2,3,4}};
        int[] p3 = {2,3};
        int[] s3 = {0,0};

        int[][] ge1  = {{2, 0, 3}, {2, 0, 3},{2, 0, 3}};
        int[] pe1 = {2, 3};
        int[] se1 = {0, 0};

        System.out.println(s.highestRankedKItems(ge1, pe1, se1,6));
        System.out.println(s.highestRankedKItems(g1, p1, s1,3));
        System.out.println(s.highestRankedKItems(g2, p2, s2, 2));
        System.out.println(s.highestRankedKItems(g3, p3, s3, 3));

    }
}
