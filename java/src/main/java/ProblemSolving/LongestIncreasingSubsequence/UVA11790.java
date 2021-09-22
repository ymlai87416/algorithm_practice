package ProblemSolving.LongestIncreasingSubsequence;

import java.util.Arrays;
import java.util.Scanner;

/**
 problem: https://onlinejudge.org/external/117/11790.pdf
 level:
 solution:

 #dp #longest_increasing_subsequence #Lv3

 **/
public class UVA11790 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();
        for(int ci=0; ci<nc; ++ci){
            int nb = sc.nextInt();

            int h[] = new int[nb];
            int w[] = new int[nb];

            for(int i=0; i<nb; ++i)
                h[i] = sc.nextInt();
            for(int i=0; i<nb; ++i)
                w[i] = sc.nextInt();

            int[] lis = new int[nb];
            int[] lds = new int[nb];

            for(int i=0; i<nb; ++i){
                lis[i] = w[i];
                lds[i] = w[i];
            }

            for(int i=0; i<nb; ++i){
                for(int j=i+1; j<nb; ++j){
                    if(h[i] < h[j])
                        lis[j] = Math.max(lis[j], lis[i] + w[j]);
                }
            }

            for(int i=0; i<nb; ++i){
                for(int j=i+1; j<nb; ++j){
                    if(h[i] > h[j])
                        lds[j] = Math.max(lds[j], lds[i] + w[j]);
                }
            }

            int maxlis = 0;
            for(int i=0; i<nb; ++i)
                maxlis = Math.max(maxlis, lis[i]);
            int maxlds = 0;
            for(int i=0; i<nb; ++i)
                maxlds = Math.max(maxlds, lds[i]);

            if(maxlis >= maxlds){
                System.out.format("Case %d. Increasing (%d). Decreasing (%d).\n", ci+1, maxlis, maxlds);
            } else{
                System.out.format("Case %d. Decreasing (%d). Increasing (%d).\n", ci+1, maxlds, maxlis);
            }

        }
    }
}
