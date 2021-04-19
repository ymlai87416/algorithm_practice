package DataStructure.TwoDArrayManipulation;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 15/5/2016.
 *
 * problem: https://onlinejudge.org/external/108/10855.pdf
 * #2Darray
 */
public class UVA10855 {
    static int[] result =new  int[4];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int n= sc.nextInt();
            int m=sc.nextInt();

            sc.nextLine();

            if(n==0 && m==0) break;

            char[][] nn= new char[n][n];
            char[][] mm = new char[m][m];
            char[][] mm90 = new char[m][m];
            char[][] mm180 = new char[m][m];
            char[][] mm270 = new char[m][m];

            for(int i=0; i<n; ++i){
                String s = sc.nextLine();
                for(int j=0; j<n; ++j)
                    nn[i][j] = s.charAt(j);
            }

            for(int i=0; i<m; ++i){
                String s = sc.nextLine();
                for(int j=0; j<m; ++j)
                    mm[i][j] = s.charAt(j);
            }

            for(int i=0; i<m; ++i){
                for(int j=0; j<m; ++j)
                    mm90[i][j] = mm[m-j-1][i];
            }

            for(int i=0; i<m; ++i){
                for(int j=0; j<m; ++j)
                    mm180[i][j] = mm90[m-j-1][i];
            }

            for(int i=0; i<m; ++i){
                for(int j=0; j<m; ++j)
                    mm270[i][j] = mm180[m-j-1][i];
            }

            Arrays.fill(result, 0);
            for(int i=0; i<n-m+1; ++i){
                for(int j=0; j<n-m+1; ++j){
                    boolean ori0 = true;
                    boolean ori1 = true;
                    boolean ori2 = true;
                    boolean ori3 = true;
                    for(int p=0; p<m; ++p){
                        for(int q=0; q<m; ++q){
                            if(nn[i+p][j+q] != mm[p][q]) ori0 = false;
                            if(nn[i+p][j+q] != mm90[p][q]) ori1 = false;
                            if(nn[i+p][j+q] != mm180[p][q]) ori2 = false;
                            if(nn[i+p][j+q] != mm270[p][q]) ori3 = false;
                        }
                    }

                    if(ori0) result[0]++;
                    if(ori1) result[1]++;
                    if(ori2) result[2]++;
                    if(ori3) result[3]++;
                }
            }

            System.out.format("%d %d %d %d\n", result[0], result[1], result[2], result[3]);
        }
    }
}
