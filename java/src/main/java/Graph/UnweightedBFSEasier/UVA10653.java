package Graph.UnweightedBFSEasier;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Tom on 8/5/2016.
 *
 *  Start at 23:06 -> 23:21, totol of 15 minutes.
 *
 *  problem: https://onlinejudge.org/external/106/10653.pdf
 *
 * #UVA #bfs #Lv2
 */
public class UVA10653 {

    static boolean[][] map = new boolean[1001][1001];
    static int[][] visited = new int[1001][1001];

    static class Pair implements Comparable<Pair>{
        int x; int y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }


        @Override
        public int compareTo(Pair o) {
            if(this.x < o.x) return -1;
            else if(this.x > o.x) return 1;
            else {
                if(this.y <o.y) return -1;
                else if(this.y > o.y) return 1;
                else return 0;
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int r = sc.nextInt();
            int c = sc.nextInt();

            if(r==0 && c == 0) break;

            int rbomb = sc.nextInt();

            for(int i=0; i<map.length; ++i) {
                Arrays.fill(map[i], false);
                Arrays.fill(visited[i], -1);
            }

            for(int i=0; i<rbomb; ++i){
                int rownum = sc.nextInt();
                int bombs = sc.nextInt();

                for(int j=0; j<bombs; ++j){
                    int colnum = sc.nextInt();
                    map[rownum][colnum] = true;
                }
            }

            int sx = sc.nextInt();
            int sy = sc.nextInt();
            Pair start = new Pair(sx, sy);
            int dx = sc.nextInt();
            int dy = sc.nextInt();
            Pair end = new Pair(dx, dy);

            Queue<Pair> queue = new ArrayDeque<Pair>();

            queue.add(start);
            visited[start.x][start.y] = 0;

            int[] ddx = new int[]{-1, 0, 1, 0};
            int[] ddy = new int[]{0, -1, 0, 1};
            while(!queue.isEmpty()){
                Pair u = queue.poll();
                if(u.compareTo(end) == 0) break;

                for(int i=0; i<4; ++i){
                    Pair v = new Pair(u.x + ddx[i], u.y+ddy[i]);
                    if(v.x < 0 || v.x >= r || v.y < 0 || v.y >= c) continue;
                    if(map[v.x][v.y]) continue;
                    if(visited[v.x][v.y] != -1) continue;

                    queue.add(v);
                    visited[v.x][v.y] = visited[u.x][u.y] +1;
                }

            }

            System.out.println(visited[dx][dy]);

        }
    }
}
