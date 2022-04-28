package Leetcode.BloombergMock;

import java.util.ArrayDeque;
import java.util.Queue;

//floodfill please becareful where to set the visited flag.

public class PhoneQ1 {
    int[][] grid ;
    int m,n;
    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        m =grid.length;
        n = grid[0].length;
        int maxIsland = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1)
                    maxIsland = Math.max(maxIsland, floodfill(i, j));
            }
        }

        return maxIsland;
    }

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    private int floodfill(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y});
        grid[x][y] = -grid[x][y];
        int size = 0;

        while(!q.isEmpty()){
            int[] u =  q.poll();

            //System.out.println(u[0] + " " + u[1]);
            ++size;

            for(int i=0; i<4; ++i){
                int px = u[0]+dx[i];
                int py = u[1]+dy[i];
                if(!(0 <= px && px < m)) continue;
                if(!(0 <= py && py < n)) continue;

                if(grid[px][py] == 1) {
                    grid[px][py] = -grid[px][py];
                    q.offer(new int[]{px, py});
                }
            }
        }

        return size;
    }

    public static void main(String[] args){
        int[][] grid = {{1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}};
        PhoneQ1 s = new PhoneQ1();
        System.out.println(s.maxAreaOfIsland(grid));
    }
}
