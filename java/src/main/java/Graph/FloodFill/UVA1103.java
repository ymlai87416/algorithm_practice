package Graph.FloodFill;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by Tom on 25/4/2016.
 *
 * problem: https://onlinejudge.org/external/11/1103.pdf
 *
 * #UVA #Lv3 #flood_fill
 */

public class UVA1103 {

    static char[][] pic = new char[200][400];
    static TreeMap<Character, char[]> lookup = new TreeMap<>();

    static int dr[] = new int[]{1, 1, 0, -1, -1, -1, 0, 1};
    static int dc[] = new int[]{0, 1, 1, 1, 0, -1, -1, -1};

    static int R, C;

    static int floodfill(int r, int c, char c1, char c2){
        if (r < 0 || r >= R || c < 0 || c >= C) return 0;
        if(pic[r][c] != c1) return 0;
        int ans = 1;
        pic[r][c] = c2;
        for(int d=0; d<8; ++d){
            ans += floodfill(r+dr[d], c+dc[d], c1, c2);
        }
        return ans;
    }

    public static void main(String[] args){
        lookup.put('0', new char[]{0,0,0,0});
        lookup.put('1', new char[]{0,0,0,1});
        lookup.put('2', new char[]{0,0,1,0});
        lookup.put('3', new char[]{0,0,1,1});
        lookup.put('4', new char[]{0,1,0,0});
        lookup.put('5', new char[]{0,1,0,1});
        lookup.put('6', new char[]{0,1,1,0});
        lookup.put('7', new char[]{0,1,1,1});
        lookup.put('8', new char[]{1,0,0,0});
        lookup.put('9', new char[]{1,0,0,1});
        lookup.put('a', new char[]{1,0,1,0});
        lookup.put('b', new char[]{1,0,1,1});
        lookup.put('c', new char[]{1,1,0,0});
        lookup.put('d', new char[]{1,1,0,1});
        lookup.put('e', new char[]{1,1,1,0});
        lookup.put('f', new char[]{1,1,1,1});

        Scanner sc = new Scanner(System.in);

        while(true){
            int R = sc.nextInt();
            int C = sc.nextInt();
            C = C * 4;
            sc.nextLine();

            if(R == 0 && C == 0) break;

            for(int i=0; i<200; ++i)
                Arrays.fill(pic[i], (char)0);

            for(int i=0; i<R; ++i){
                String s = sc.nextLine();

                for(int j=0; j<s.length(); ++j){
                    char ss = s.charAt(j);
                    char[] bits = lookup.get(ss);
                    for(int k=0; k<4; ++i){
                        pic[i][j*4+k] = bits[k];
                    }
                }
            }


            //flood fill

            //find neighbour


        }
    }
}
