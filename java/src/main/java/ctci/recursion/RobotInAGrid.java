package ctci.recursion;
import java.util.*;
public class RobotInAGrid {
    int[][] d;

    public List<String> robotInAGrid(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;

        d = new int[m][n];
        for(int i=0; i<m; ++i)
            Arrays.fill(d[i], -1);
        d[0][0] = 0;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});

        while(!q.isEmpty()){
            int[] u = q.poll();

            int nx, ny;

            //go right
            nx = u[0];
            ny = u[1]+1;
            if(ny < n && grid[nx][ny] != 1 && d[nx][ny] == -1){
                d[nx][ny] = d[u[0]][u[1]] + 1;
                q.offer(new int[]{nx, ny});
            }

//go down
            nx = u[0]+1;
            ny = u[1];
            if(nx < m && grid[nx][ny] != 1 && d[nx][ny] == -1){
                d[nx][ny] = d[u[0]][u[1]] + 1;
                q.offer(new int[]{nx, ny});
            }


        }

        //now backtrack
        List<String> result = new ArrayList<String>();

        int x = m-1;
        int y = n-1;
        int px, py;
        while(d[x][y] != 0){
            //from top?
            px = x-1; py = y;
            if( 0 <= px && d[px][py]+1 == d[x][y]){
                result.add("down");
                x = px; y=py;
                continue;
            }
            //from left?
            px = x; py = y-1 ;
            if( 0 <= py && d[px][py]+1 == d[x][y]){
                result.add("right");
                x = px; y=py;
                continue;
            }

        }
        Collections.reverse(result);
        return result;

    }


    public static void main(String[] args) {
        int[][] board = new int[][]{
                {0, 1, 1, 0},
                {0, 0, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 0}
        };

        RobotInAGrid test = new RobotInAGrid();
        System.out.println(test.robotInAGrid(board));
    }
}
