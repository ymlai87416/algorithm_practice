package Leetcode.Weekly297;

import java.util.*;

public class B {
    public int minPathCost(int[][] grid, int[][] moveCost) {

        Comparator<Pair> comp = new Comparator<>(){
            public int compare(Pair a, Pair b){
                return a.first - b.first;
            }
        };

        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        for(int i=0; i<m; ++i){
            Arrays.fill(dist[i], -1);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(comp);
        for(int i=0; i<n; ++i){
            dist[0][i] = grid[0][i];
            pq.offer(new Pair(dist[0][i], 0, i));
        }




        while(!pq.isEmpty()){
            Pair u= pq.poll();
            System.out.println("update " + u.second + " " + u.third + "  " + dist[u.second][u.third] + "x" + u.first);
            if(u.first != dist[u.second][u.third])
                continue;

            int uv = grid[u.second][u.third];

            for(int i=0; i<n; ++i){
                int nd = u.first+ moveCost[uv][i] + grid[u.second+1][i];

                if(grid[u.second+1][i] == -1 || grid[u.second+1][i] > nd){
                    grid[u.second+1][i] =nd;
                    Pair v = new Pair(nd, u.second+1, i);
                    System.out.println("update " + u.second + " " + u.third + " " + nd);
                    pq.offer(v);
                }
            }
        }

        int md = Integer.MAX_VALUE;
        for(int i=0; i<n; ++i){
            md = Math.min(md, dist[m-1][i]);
        }

        return md;
    }

    public static void main(String[] args) {
        B b = new B();
        int[][] grid = new int[][]{{5,3},{4,0},{2,1}};
        int[][] moveCost = new int[][]{{9,8},{1,5},{10,12},{18,6},{2,4},{14,3}};
        b.minPathCost(grid, moveCost);
    }
}

class Pair{
    int first;
    int second;
    int third;

    public Pair(int first, int second, int third){
        this.first = first;
        this.second = second;
        this.third = third;
    }
}

