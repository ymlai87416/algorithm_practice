package Graph.UnweightedBFSHarder;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Tom on 13/5/2016.
 *
 * 1. Becareful of the orientation, i set the dx and dy wrong
 * 2. Becareful with the edge, the edge cannot be used
 */
public class UVA314 {
    static int[][] map = new int[51][51];
    static int[][][] visited = new int[51][51][4];

    static final int[] dx = new int[] {-1, 0, 1, 0};
    static final int[] dy = new int[] {0, 1, 0, -1};
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(a == 0 && b == 0) break;

            for(int i=0; i<a; ++i){
                for(int j=0; j<b; ++j){
                    map[i][j] = sc.nextInt();
                }
            }

            Queue<Tuple> queue = new ArrayDeque<>();

            for(int i=0; i<visited.length; ++i)
                for(int j=0; j<visited.length; ++j)
                Arrays.fill(visited[i][j], -1);

            int sx = sc.nextInt();
            int sy = sc.nextInt();
            int tx = sc.nextInt();
            int ty = sc.nextInt();
            String ori = sc.next();
            int iori;

            if(ori.compareTo("north")==0)iori = 0;
            else if(ori.compareTo("east") == 0) iori=1;
            else if(ori.compareTo("south")==0) iori=2;
            else iori=3;

            queue.add(new Tuple(sx, sy, iori));
            visited[sx][sy][iori] = 0;

            while(!queue.isEmpty()){
                Tuple u = queue.poll();

                //go 1, 2, 3
                for(int i=1; i<=3; ++i){
                    Tuple v = new Tuple(u.first+dx[u.third]*i, u.second+dy[u.third]*i, u.third);
                    if(v.first < 1 || v.first >= a || v.second < 1 || v.second >= b) continue;
                    if(visited[v.first][v.second][v.third] != -1) continue;
                    if(enterable(map, a, b, v.first, v.second)) {
                        queue.offer(v);
                        visited[v.first][v.second][v.third] = visited[u.first][u.second][u.third]+1;
                    } else break;
                }
                //left, right
                Tuple left = new Tuple(u.first, u.second, Math.floorMod(u.third+1, 4));
                if(visited[left.first][left.second][left.third] != -1) continue;
                queue.offer(left);
                visited[left.first][left.second][left.third] = visited[u.first][u.second][u.third]+1;

                Tuple right = new Tuple(u.first, u.second, Math.floorMod(u.third-1, 4));
                if(visited[right.first][right.second][right.third] != -1) continue;
                queue.offer(right);
                visited[right.first][right.second][right.third] = visited[u.first][u.second][u.third]+1;
            }

            /*for(int i=0; i<a; ++i){
                for(int j=0; j<=b; ++j){
                    System.out.print("(");
                    for(int k=0; k<4; ++k){
                        System.out.print(visited[i][j][k]);
                        if(k!=3)System.out.print(",");
                    }
                    System.out.print(") ");
                }
                System.out.println();
            }*/

            int mindist = Integer.MAX_VALUE;
            for(int i=0; i<4; ++i){
                mindist = Math.min(mindist, visited[tx][ty][i]);
            }

            System.out.println(mindist);
        }
    }

    static boolean enterable(int[][] map, int m, int n, int x, int y){
        boolean result = true;
        if(x < m && y < n) result = result && map[x][y] ==0;
        if(x > 0 && y < n) result = result && map[x-1][y] == 0;
        if(x > 0 && y > 0) result = result && map[x-1][y-1] == 0;
        if(x < m && y > 0 ) result = result && map[x][y-1] == 0;

        return result;
    }

    static class Tuple{
        int first, second, third;

        public Tuple(int first, int second, int third){
            this.first = first; this.second = second; this.third= third;
        }
    }
}
