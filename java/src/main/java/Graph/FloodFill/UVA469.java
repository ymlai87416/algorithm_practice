package Graph.FloodFill;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * problem: https://onlinejudge.org/external/4/469.pdf
 *
 * #UVA #Lv2 #flood_fill
 */

public class UVA469 {

    int dr[] = {1,1,0,-1,-1,-1, 0, 1}; // trick to explore an implicit 2D grid
    int dc[] = {0,1,1, 1, 0,-1,-1,-1}; // S,SE,E,NE,N,NW,W,SW neighbors

    int floodfill(char[][] grid, int r, int c, char c1, char c2) { // returns the size of CC
        int R=grid.length; int C=R > 0? grid[0].length: 0;
        if (r < 0 || r >= R || c < 0 || c >= C) return 0; // outside grid
        if (grid[r][c] != c1) return 0; // does not have color c1
        int ans = 1; // adds 1 to ans because vertex (r, c) has c1 as its color
        grid[r][c] = c2; // now recolors vertex (r, c) to c2 to avoid cycling!
        for (int d = 0; d < 8; d++)
            ans += floodfill(grid, r + dr[d], c + dc[d], c1, c2);
        return ans; // the code is neat due to dr[] and dc[]
    }

    public void run(){
        Scanner sc = new Scanner(System.in);
        int nc = sc.nextInt();
        sc.nextLine();
        for(int i=0; i<nc; ++i){
            sc.nextLine();

            ArrayList<String> input = new ArrayList<>();

            while(!sc.hasNextInt()){
                input.add(sc.nextLine());
            }

            //make the grid
            char[][] grid = new char[input.size()][input.get(0).length()];
            for(int p=0; p<input.size(); ++p){
                for(int q=0; q<input.get(p).length(); ++q){
                    grid[p][q] = input.get(p).charAt(q);
                }
            }

            while(sc.hasNextInt()){
                int r = sc.nextInt();
                int c = sc.nextInt();
                //clear the grid back from . to W

                for (int j = 0; j < grid.length; j++) {
                    for (int k = 0; k < grid[j].length ; k++) {
                        if(grid[j][k] == '.') grid[j][k] = 'W';
                    }
                }

                int ans = floodfill(grid, r-1, c-1, 'W', '.');
                System.out.println(ans);
            }
            if(i < nc-1)
                System.out.println();
            sc.nextLine();
        }
    }

    public static void main(String[] args) {
        UVA469 sol = new UVA469();
        sol.run();
    }
}
