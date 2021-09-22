package ProblemSolving.BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

/**
 problem: https://onlinejudge.org/external/121/12192.pdf
 level:
 solution:

 #binary_search #UVA #Lv3 #skip

 **/

public class UVA12192 {
    static int[][] hill = new int[501][501];
    static int[][] hilld = new int[1000][501];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int n = sc.nextInt();
            int m = sc.nextInt();

            if(n == 0 && m == 0) break;

            for(int i=0; i<n; ++i){
                for(int j=0; j<m; ++j){
                    hill[i][j] = sc.nextInt();
                }
            }

            int nq = sc.nextInt();

            for(int qi=0; qi<nq; ++qi){
                int lowerb = sc.nextInt();
                int upperb = sc.nextInt();

                int maxS = 0;
                for(int i=0; i<n; ++i){
                    int idx = findUpperBound(hill[i], 0, m, lowerb);
                    if(idx == m) continue;
                    if(!(i+maxS-1<n && idx+maxS-1<m)) continue;
                    int j = 1;
                    for(; i+j-1<n && idx+j-1<m; ++j)
                        if(hill[i+j-1][idx+j-1] > upperb){
                            break;
                        }
                    maxS = Math.max(maxS, --j);
                }

                System.out.println(maxS);
            }
            System.out.println("-");
        }
    }

    static int findUpperBound(int[] array, int start, int end, int obj){
        int idx = Arrays.binarySearch(array, start, end, obj);  //no guarantee which can be found
        if(idx >= 0) {
            while(idx-1 >=0 && array[idx-1] == obj) --idx;
            return idx;
        }
        else return -idx -1;
    }
}
