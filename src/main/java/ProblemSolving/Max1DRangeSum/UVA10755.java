package ProblemSolving.Max1DRangeSum;

import java.util.Scanner;

/**
 * Created by Tom on 21/4/2016.
 *
 * Last time runtime error. shit...
 */
public class UVA10755 {
    public static void main(String[] args){

        int[][][] dump = new int[20+1][20+1][20+1];
        long[][][] sum = new long[20+1][20+1][20+1];

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for(int i=0; i<t; ++i){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            for(int p=0; p<a; ++p){
                for(int q=0; q<b; ++q){
                    for(int r=0; r<c; ++r){
                        dump[p][q][r] = sc.nextInt();
                    }
                }
            }
            sc.nextLine();

            long max = Long.MIN_VALUE;

            for(int ep=0; ep<a; ++ep) {
                for (int eq = 0; eq < b; ++eq) {
                    for (int er = 0; er < c; ++er) {

                        //System.out.format("%d %d %d\n", ep, eq, er);

                        for (int p = 0; p < 21; ++p)
                            for (int q = 0; q < 21; ++q)
                                for (int r = 0; r < 21; ++r)
                                    sum[p][q][r] = 0;


                        for (int p = ep; p < a; ++p) {
                            for (int q = eq; q < b; ++q) {
                                long bb = 0;
                                for (int r = er; r < c; ++r) {
                                    sum[p][q][r] = bb + dump[p][q][r];
                                    bb = sum[p][q][r];
                                }
                            }
                        }

                        for (int p = ep; p < a; ++p) {
                            for (int r = er; r < c; ++r) {
                                long bb = 0;
                                for (int q = eq; q < b; ++q) {
                                    sum[p][q][r] = bb + sum[p][q][r];
                                    bb = sum[p][q][r];
                                }
                            }
                        }

                        for (int q = eq; q < b; ++q) {
                            for (int r = er; r < c; ++r) {
                                long bb = 0;
                                for (int p = ep; p < a; ++p) {
                                    sum[p][q][r] = bb + sum[p][q][r];
                                    bb = sum[p][q][r];
                                }
                            }
                        }

                        for (int p = 0; p < a; ++p) {
                            for (int q = 0; q < b; ++q) {
                                for (int r = 0; r < c; ++r) {
                                    if(sum[p][q][r] > max)
                                        max = sum[p][q][r];
                                }
                            }
                        }
                    }
                }
            }

            System.out.println(max);
            if(i != t-1) System.out.println();
        }
    }
}
