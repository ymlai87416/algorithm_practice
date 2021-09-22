package Graph.FloodFill;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 1/5/2016.
 *
 * problem: https://onlinejudge.org/external/110/11094.pdf
 *
 * #UVA #Lv2 #flood_fill
 */

public class UVA11094 {
    static char[][] map = new char[21][21];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            if(!sc.hasNext()) break;
            int m = sc.nextInt(); int n=sc.nextInt();

            sc.nextLine();
            Character a= null, b = null;

            for(int i=0; i<20; ++i) Arrays.fill(map[i], (char)0);
            for(int i=0; i<m; ++i){
                String l = sc.nextLine();
                for(int j=0;j<n; ++j){
                    map[i][j] = l.charAt(j);
                    if(a == null) a = map[i][j];
                    else if(b == null && map[i][j] != a) b = map[i][j];
                }
            }

            int kx = sc.nextInt(); int ky = sc.nextInt();
            Character land, water;
            if(map[kx][ky] == a) {land = a; water = b;}
            else {land = b; water = a;}

            if(water == null){
                sc.nextLine();
                sc.nextLine();
                System.out.println(0);
                continue;
            }

            char token;
            for(token='0'; token==land || token==water; ++token);

            floodfill(map, kx, ky, m, n, land, token);

            int size = 0;
            int tempsize;
            for(int i=0; i<m; ++i){
                for(int j=0; j<n; ++j){
                    if(map[i][j] == land) {
                        tempsize = floodfill(map, i, j, m, n, land, token);
                        if(tempsize > size)
                            size = tempsize;
                    }
                }
            }

            //debugMap(map, m, n);

            sc.nextLine();
            sc.nextLine();
            System.out.println(size);
        }
    }

    static int dr[] = {1,0,-1, 0}; // trick to explore an implicit 2D grid
    static int dc[] = {0,1, 0,-1}; // S,SE,E,NE,N,NW,W,SW neighbors

    static int floodfill(char[][] grid , int r, int c, int M, int N, char c1, char c2){
        if (r < 0 || r >= M) return 0;
        if (c == -1) c = N-1;
        else if (c == N) c = 0;
        if (grid[r][c] != c1) return 0;
        int ans = 1;
        grid[r][c] = c2;
        for (int d = 0; d < 4; d++)
            ans += floodfill(grid, r + dr[d], c + dc[d], M, N, c1, c2);
        return ans;
    }


    public static void debugMap(char[][] grid, int M, int N){
        System.out.println("*********");
        for(int i=0; i<M; ++i){
            for(int j=0; j<N;++j)
                System.out.print(grid[i][j]);
            System.out.println();
        }
    }

}
