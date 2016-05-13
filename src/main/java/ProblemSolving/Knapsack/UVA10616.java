package ProblemSolving.Knapsack;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 19/4/2016.
 */
public class UVA10616 {
    static long[][][] dptable = new long[200+1][10+1][20];
    static long[] item = new long[200+1];

    static void resetArray(){
        for(int i=0; i<=200; ++i){
            for(int j=0; j<=10; ++j)
                Arrays.fill(dptable[i][j], (short)0);
        }
    }
    public static void main(String[] args){


        Scanner sc = new Scanner(System.in);

        int cnt=0;
        while(true){
            int n = sc.nextInt();
            int q = sc.nextInt();

            if(n==0 && q == 0) break;
            System.out.format("SET %d:\n", ++cnt);

            for(int i=1; i<=n; ++i){
                item[i] = sc.nextInt();
            }

            for(int i=0; i<q; ++i){
                int d = sc.nextInt();
                int m = sc.nextInt();

                resetArray();

                dptable[0][0][0]=1; //base case: mean that 1 combination for choosing from nothing p=0, choose 0 number, and have the sum =0 which 0%d = 0. (1 case only)

                for(int p=1; p<=n; ++p){        //item
                    //add
                    for(int s=1; s<=m; ++s){         //weight
                        for(int r=0; r<d; ++r){     //sum
                            int temp = (int) (r + item[p]) % d;
                            if(temp < 0) temp+=d;
                            dptable[p][s][temp] += dptable[p-1][s-1][r];
                        }
                    }

                    for(int s=0; s<=m; ++s){         //weight
                        for(int r=0; r<d; ++r){     //sum
                            dptable[p][s][r] += dptable[p-1][s][r];
                        }
                    }
                }

/*                for(int p=1; p<=n; ++p) {
                    System.out.println("p= "+ p);
                    for (int s = 0; s <= m; ++s) {
                        for (int r = 0; r < d; ++r)
                            System.out.print (dptable[p][s][r] + " ");
                        System.out.println();
                    }
                }*/

                System.out.format("QUERY %d: %d\n", i+1, dptable[n][m][0]);
            }
        }
    }
}
