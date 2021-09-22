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
public class UVA11953 {
    static char[][] battlefield = new char[101][101];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int ncase = sc.nextInt();
        for(int i=0; i<ncase; ++i){
            int nboard = sc.nextInt();
            sc.nextLine();

            for(int p=0; p<100; ++p) Arrays.fill(battlefield[p], (char)0);
            for(int p=0; p<nboard; ++p){
                String line= sc.nextLine();
                for(int q=0; q<nboard; ++q){
                    battlefield[p][q] = line.charAt(q);
                }
            }

            int ans = 0;
            for(int p=0; p<nboard; ++p){
                for(int q=0; q<nboard; ++q){
                    String boat = floodfill(battlefield, p, q, nboard, nboard, "");

                    boolean allhit=true;
                    for(int k=0; k<boat.length(); ++k){
                        allhit = allhit && boat.charAt(k) == '@';
                    }
                    if(!allhit) ans++;


                }
            }
            //debugMap(battlefield, nboard, nboard);
            System.out.format("Case %d: %d\n", i+1, ans);
        }
    }

    public static String floodfill(char[][] grid, int r, int c, int M, int N,  String boat){
        if (r < 0 || r >= M ||c < 0 || c >= N) return boat;
        if (!(grid[r][c] == 'x' || grid[r][c] == '@')) return boat;
        String ans = null;
        char cur=grid[r][c];
        grid[r][c] = 'F';
        String try1 = floodfill(grid, r+1, c, M, N, boat+cur);
        String try2 = floodfill(grid, r, c+1, M, N, boat+cur);
        if(try1.length() > try2.length()) return try1;
        else return try2;
    }

    static void debugMap(char[][] grid, int M, int N){
        System.out.println("*********");
        for(int i=0; i<M; ++i){
            for(int j=0; j<N; ++j){
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
}
