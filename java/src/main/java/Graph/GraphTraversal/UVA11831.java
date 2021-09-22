package Graph.GraphTraversal;

import java.util.Scanner;

/**
 * Created by Tom on 24/4/2016.
 *
 * Begin at 23:54 and end at 00: 25, total time is 31 minutes, next time module with is x and which is y, and careful of %, better use floor mod.
 *
 * problem: https://onlinejudge.org/external/118/11831.pdf
 *
 * #UVA #Lv2 #graph_traversal
 */
public class UVA11831 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int s = sc.nextInt();

            sc.nextLine();
            if(n==0 && m==0 && s == 0) break;

            int rx=0, ry=0, ro=0;

            int[][] map = new int[n][m];
            for(int i=0; i<n; ++i){
                String ss = sc.nextLine();
                for(int j=0; j<m; ++j){             //n is the y direction
                    map[i][j] = ss.charAt(j);       //m is the x direction

                    if(map[i][j] == 'N'){
                        rx = j;
                        ry = i;
                        ro = 0;
                    }
                    else if(map[i][j] == 'L'){
                        rx = j;
                        ry = i;
                        ro = 1;
                    }
                    else if(map[i][j] == 'S'){
                        rx = j;
                        ry = i;
                        ro = 2;
                    }
                    else if(map[i][j] == 'O'){
                        rx = j;
                        ry = i;
                        ro = 3;
                    }
                }
            }

            String instrs = sc.nextLine();
            int nsticker = 0;
            for(int i=0; i<s; ++i){
                char in = instrs.charAt(i);
                //System.out.print(in + ": ");
                switch(in){
                    case 'D':
                        ro = Math.floorMod(ro+1, 4);
                        break;
                    case 'E':
                        ro = Math.floorMod(ro-1, 4);
                        break;
                    case 'F':
                        int nx = rx;
                        if(ro == 1) ++nx; else if(ro == 3) --nx;
                        int ny=ry;
                        if(ro == 0) --ny; else if(ro == 2) ++ny;

                        if(nx < 0 || nx >= m || ny < 0 || ny >= n || map[ny][nx] == '#'){
                            //do nothing.
                            //System.out.println("Stuck at " + rx + " " + ry);
                        } else{
                            rx = nx; ry = ny;
                            //System.out.println("move to  " + nx + " " + ny);
                        }
                        if(map[ry][rx] == '*') {
                            ++nsticker;
                            map[ry][rx] = '.';
                            //System.out.println("pick one " + nx + " " + ny);
                        }
                        break;
                }
            }

            System.out.println(nsticker);
        }
    }
}
