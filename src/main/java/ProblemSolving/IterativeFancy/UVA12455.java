package ProblemSolving.IterativeFancy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 21/4/2016.
 * Start at 0:34 and AC at 0:43, total time spent: 9 minutes.
 */
public class UVA12455 {
    static int[][] len_pos = new int[1000+1][20+1];
    static int[] nbarLen = new int[20+1];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int i=0; i<t; ++i){
            int len = sc.nextInt();

            int nbar = sc.nextInt();

            for(int j=1; j<=nbar; ++j){
                nbarLen[j] = sc.nextInt();
            }

            for(int j=0; j<len_pos.length; ++j)
                Arrays.fill(len_pos[j], 0);

            len_pos[0][0]=1;
            for(int p=0; p<len+1; ++p){
                for(int q=1; q<=nbar; ++q){
                    if(p-nbarLen[q] < 0)
                        len_pos[p][q] = len_pos[p][q-1];
                    else
                        len_pos[p][q] = Math.max(len_pos[p-nbarLen[q]][q-1], len_pos[p][q-1]);
                }
            }

            if(len_pos[len][nbar] == 0)
                System.out.println("NO");
            else
                System.out.println("YES");
        }
    }
}
