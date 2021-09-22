package ProblemSolving.Max1DRangeSum;

import java.util.Scanner;

/**
 problem: https://onlinejudge.org/external/107/10755.pdf
 level:
 solution:  AC

 #dp #max_1d_range_sum #UVA #Lv3

 **/

public class UVA10755 {
    public static void main(String[] args){

        long[][][] dump = new long[20+1][20+1][20+1];
        long[][][] sum = new long[20+1][20+1][20+1];

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for(int i=0; i<t; ++i) {
            try {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int c = sc.nextInt();

                for (int p = 0; p < a; ++p) {
                    for (int q = 0; q < b; ++q) {
                        for (int r = 0; r < c; ++r) {
                            dump[p][q][r] = sc.nextLong();
                        }
                    }
                }
                //sc.nextLine();

                for (int p = 0; p < 21; ++p)
                    for (int q = 0; q < 21; ++q)
                        for (int r = 0; r < 21; ++r)
                            sum[p][q][r] = 0;

                for (int j = 0; j < a; j++) {
                    for (int k = 0; k < b; k++) {
                        for (int l = 0; l < c; l++) {
                            sum[j+1][k+1][l+1] =
                                    sum[j][k+1][l+1] + sum[j+1][k][l+1] + sum[j+1][k+1][l]
                                            - 2* sum[j][k][l]
                                            - (sum[j][k][l+1] - sum[j][k][l])
                                            - (sum[j+1][k][l] - sum[j][k][l])
                                            - (sum[j][k+1][l] - sum[j][k][l])
                                            + dump[j][k][l];
                        }
                    }
                }

                long max = Long.MIN_VALUE;
                //sum(x1~x2, y1~y2, z1~z2) = sum(0~x2, 0~y2, 0~z2) - sum(0-(x1-1), 0~(y1-1), 0~(z1-1)
                for (int j = 0; j < a; j++) {
                    for (int k = 0; k < b; k++) {
                        for (int l = 0; l < c; l++) {
                            for (int m = j; m < a; m++) {
                                for (int n = k; n < b; n++) {
                                    for (int o = l; o < c; o++) {
                                        //from j -> m, k -> n and l -> o
                                        long r = sum[m+1][n+1][o+1] - sum[m+1][n+1][l] - sum[j][n+1][o+1] - sum[m+1][k][o+1]
                                                + sum[m+1][k][l] + sum[j][n+1][l] + sum[j][k][o+1] - sum[j][k][l];

                                        //long r= sum[m][n][o] - sum[j][k][o] - sum[j][n][l] - sum[m][k][l]  +
                                        //System.out.println("square " + j + " " + k + " "+ l + " " + m + " " + n + " " + o + " = " + r );

                                        if(r > max)
                                            max=r;
                                    }
                                }
                            }
                        }
                    }
                }

                /*

                for (int ep = 0; ep < a; ++ep) {
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
                                        if (sum[p][q][r] > max)
                                            max = sum[p][q][r];
                                    }
                                }
                            }
                        }
                    }
                }

                */

                System.out.println(max);
                if (i != t - 1) System.out.println();
            }
            catch(Exception ex){
                throw ex;
            }
        }
    }
}
