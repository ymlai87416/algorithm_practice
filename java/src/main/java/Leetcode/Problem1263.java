package Leetcode;

import java.util.*;

public class Problem1263 {

    char[][] grid;
    int m;
    int n;
    HashMap<Integer, Integer> visited;
    int ttx;
    int tty;

    int[][] diff = new int[][]{ {-1, 0}, {1, 0}, {0, 1}, {0, -1} };

    public int minPushBox(char[][] grid) {

        //do a bfs state = box x,y and player x,y
        //when push, check if player can go from box left, right, up, down
        //after a push, this is a new state and store in queue
        //each state check if the box is ok.

        int px=0, py=0;
        int bx=0, by=0;

        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;

        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(grid[i][j] == 'B'){
                    bx = i;
                    by = j;
                    grid[i][j] = '.';
                }
                if(grid[i][j] == 'S'){
                    px = i;
                    py = j;
                    grid[i][j] = '.';
                }
                if(grid[i][j] == 'T'){
                    ttx = i;
                    tty = j;
                    grid[i][j] = '.';
                }
            }
        }

        System.out.println("found: " + Arrays.asList(bx, by, px, py, ttx, tty) );

        Queue<int[]> q = new ArrayDeque<>();
        visited = new HashMap<>();

        q.offer(new int[]{bx, by, px, py});
        visited.put(stateCode(bx, by, px, py), 0);
        //total number of state = 20 * 20 * 20 * 20; = 160000

        while(!q.isEmpty()){

            int[] u = q.poll();
            int dist = visited.get(stateCode(u[0], u[1], u[2], u[3]));
            if(u[0] == ttx && u[1] == tty)
                return dist;

            System.out.println("Current state: " + Arrays.asList(u[0],u[1],u[2],u[3] ));

            grid[u[0]][u[1]] = '#';
            for(int i=0; i<4; ++i){
                int nx = u[0] + diff[i][0];
                int ny = u[1] + diff[i][1];
                if(isValid(nx, ny) && grid[nx][ny] =='.') //that means can stand people
                {
                    int dst_bx = u[0] - diff[i][0];
                    int dst_by = u[1] - diff[i][1];

                    if(isValid(dst_bx, dst_by) && grid[dst_bx][dst_by] == '.'){
                        tx = u[2];
                        ty = u[3];
                        emptyV();
                        vvv[nx][ny] = true;
                        boolean canReach = dfsP(nx, ny);

                        if(canReach){
                            //storekeeper push the box at his location
                            int nState = stateCode(dst_bx, dst_by, bx, by);
                            if(!visited.containsKey(nState)){
                                visited.put(nState, dist+1);
                                q.offer(new int[]{dst_bx, dst_by, bx, by});
                            }
                        }
                    }
                }
            }
            grid[u[0]][u[1]] = '.';

        }

        return -1;

    }

    private boolean isValid(int x, int y){
        return (0 <= x && x < m) && (0 <= y && y <n);
    }

    int tx, ty;
    boolean[][] vvv = new boolean[21][21];
    private void emptyV(){
        for(int i=0; i<21; ++i) Arrays.fill(vvv[i], false);
    }
    private boolean dfsP(int x, int y){
        if(x == tx && y == ty) return true;
        for(int i=0; i<4; ++i){
            int nx = x + diff[i][0];
            int ny = y + diff[i][1];
            if(isValid(nx, ny) && grid[nx][ny] =='.' && !vvv[nx][ny]){
                vvv[nx][ny] =true;
                if(dfsP(nx, ny))
                    return true;
            }
        }
        return false;
    }

    private int stateCode(int bx, int by, int px, int py){
        return bx * 1_000_000 + by * 10_000 + px * 100 + py;
    }

    public static void main(String[] args){
        Problem1263 c  = new Problem1263();
        char[][] grid =
                {{'#','.','.','#','T','#','#','#','#'},
                {'#','.','.','#','.','#','.','.','#'},
                {'#','.','.','#','.','#','B','.','#'},
                {'#','.','.','.','.','.','.','.','#'},
                {'#','.','.','.','.','#','.','S','#'},
                {'#','.','.','#','.','#','#','#','#'}};
        System.out.println(c.minPushBox(grid));
    }
}
