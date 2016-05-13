package ProblemSolving.LongestIncreasingSubsequence;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Tom on 6/5/2016.
 */
public class UVA11456 {

    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();
        for(int ci=0; ci<nc; ++ci){
            int car = sc.nextInt();
            int[] cars = new int[car];
            for(int j=0; j<car; ++j){
                cars[j] = sc.nextInt();
            }

            int[] lis = new int[car];

            //length of longest increasing subsequence including pos i
            for(int i=car-1; i>=0; --i){
                lis[i] = 1;
                for(int j=i+1; j<car; ++j){
                    if(cars[i] < cars[j])
                        lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }

            int[] lds = new int[car];

            //length of longest decreasing subsequence including pos i
            for(int i=car-1; i>=0; --i){
                lds[i] = 1;
                for(int j=i+1; j<car; ++j){
                    if(cars[i] > cars[j])
                        lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }

            int maxsize = 0;
            for(int i=0; i<car; ++i){
                maxsize = Math.max(maxsize, lds[i]+lis[i]-1);
            }
            System.out.println(maxsize);
        }
    }
}
