package ProblemSolving.Max2DRangeSum;

import java.util.Scanner;

/**
 problem: https://onlinejudge.org/external/108/10827.pdf
 level:
 solution: O(n^4)  Kadane does not work because when we keep adding and limit exceed the square length,
 then we have to start substracting postive number.

 #dp #Max2DRangeSum

 **/

public class UVA10827 {
    static int[][] input = new int[75*2+1][75*2+1];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int ci=0; ci<nc; ++ci){
            int ns = sc.nextInt();

            for(int i=0; i<ns; ++i){
                for(int j=0; j<ns; ++j){
                    input[i][j] = sc.nextInt();
                    input[i+ns][j] = input[i][j];
                    input[i][j+ns] = input[i][j];
                    input[i+ns][j+ns] = input[i][j];
                }
            }

            //now do the checking
            int maxvalue = Integer.MIN_VALUE;

            for(int i=0; i<ns*2; ++i){
                for(int j=1; j<ns*2; ++j)
                    input[i][j] += input[i][j-1];
            }

            for(int i=1; i<ns*2; ++i){
                for(int j=0; j<ns*2; ++j)
                    input[i][j] += input[i-1][j];
            }

            for(int i=0; i<ns; ++i){
                for(int j=0; j<ns; ++j){
                    for(int k=1; k<=ns; ++k){
                        for(int l=1; l<=ns; ++l){
                            int temp = input[i+k-1][j+l-1];
                            if(i> 0) temp -= input[i-1][j+l-1];
                            if(j> 0) temp -= input[i+k-1][j-1];
                            if(j>0 && i>0) temp += input[i-1][j-1];
                            if(temp > maxvalue) maxvalue = temp;
                        }
                    }
                }
            }

            System.out.println(maxvalue);
        }


    }
}
