package Graph.UnweightedBFSHarder;

import java.util.*;

/**
 * Created by Tom on 14/5/2016.
 * Start at 22:22
 *
 * problem: https://onlinejudge.org/external/111/11101.pdf
 *
 * #UVA #Lv3 #bfs
 */
public class UVA11101 {

    static class Point implements Comparable<Point>{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y=  y;
        }


        @Override
        public int compareTo(Point o) {
            if(x < o.x)
                return -1;
            else if(x > o.x)
                return 1;
            else {
                if (y < o.y)
                    return -1;
                else if(y >o.y)
                    return 1;
                else
                    return 0;
            }
        }

        @Override
        public boolean equals(Object o){
            if(! (o instanceof Point))
                return false;
            Point po = (Point)o;
            return po.x == x && po.y == y;
        }
    }
    static int[][] d = new int[2001][2001];
    final static int INF = Integer.MAX_VALUE;
    final static int[] dx = {-1, 1, 0, 0};
    final static int[] dy = {0, 0, -1, 1};

    static boolean onMap(int x, int y){
        if(x < 0 || x > 2000) return false;
        if(y < 0 || y > 2000) return false;
        return true;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            Set<Point> mail1 = new TreeSet<>();
            Set<Point> mail2 = new TreeSet<>();

            for(int i=0; i<2001; ++i)
                Arrays.fill(d[i], INF);

            int m1= sc.nextInt();

            if(m1==0) break;

            for(int i=0; i<m1; ++i){
                int x = sc.nextInt();
                int y = sc.nextInt();

                mail1.add(new Point(x, y));
                d[x][y] = 0;
            }


            int m2= sc.nextInt();
            for(int i=0; i<m2; ++i){
                int x = sc.nextInt();
                int y = sc.nextInt();

                mail2.add(new Point(x, y));
            }

            Queue<Point> q = new ArrayDeque<>();
            for(Point p: mail1)
                q.offer(p);

            int ans = -1;
            while (!q.isEmpty()) {
                Point u = q.poll(); // queue: layer by layer!

                if(mail2.contains(u)){
                    ans = d[u.x][u.y];
                    break;
                }

                for (int i = 0; i < 4; ++i) {
                    int nx = u.x + dx[i];
                    int ny = u.y + dy[i];

                    if (onMap(nx, ny) ) {
                        if (d[nx][ny] == INF) {
                            d[nx][ny] = d[u.x][u.y] + 1;
                            q.offer(new Point(nx, ny));
                        }
                    }
                }
            }

            System.out.println(ans);
        }

    }
}
