package Leetcode;

import java.util.Stack;

/*
number: 380
url: https://leetcode.com/problems/climbing-stairs/
level: easy
solution: fibonacci number
 */

public class MaximalSquare {
    public static void main(String[] args){
        char[][] input = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};

        //input = new char[][]{{'1'}};
        Solution s = new Solution();
        System.out.println(s.maximalSquare(input));
    }

    static
    class Solution {

        public int maximalSquare(char[][] matrix) {
            return dpHelper(matrix);

        }

        public int dpHelper(char[][] matrix){
            if(matrix.length == 0 || matrix[0].length == 0) return 0;
            int r=0;
            int[][] dp = new int[matrix.length][matrix[0].length];

            for(int i=0; i<matrix[0].length; ++i) {
                if(matrix[0][i] == '1'){
                    r = 1;
                    dp[0][i]=1;
                }
            }
            for(int i= 1; i<matrix.length; ++i){
                if(matrix[i][0] == '1'){
                    r = Math.max(1,r );
                    dp[i][0]=1;
                }
                for(int j=1; j<matrix[i].length; ++j){
                    if(matrix[i][j] == '1'){
                        dp[i][j] = Math.min(dp[i-1][j] , Math.min(dp[i][j-1], dp[i-1][j-1]))+1;
                        r = Math.max(r, dp[i][j]*dp[i][j]);
                    }
                    else dp[i][j] = 0;
                }
            }

            return r;
        }

        //this is O(n^2 sqrt(n)), beat only 5.5%
        public int helper(char[][] matrix) {
            if(matrix.length == 0 || matrix[0].length == 0) return 0;

            int[][] horz = new int[matrix.length][matrix[0].length];

            for(int i=0; i<matrix.length; ++i){
                horz[i][0] = matrix[i][0] == '1' ? 1 : 0;
                for(int j=1; j<matrix[i].length; ++j)
                    horz[i][j] = horz[i][j-1]+(matrix[i][j] == '1' ? 1: 0);
            }

            for(int i=1; i<matrix.length; ++i){
                for(int j=0; j<matrix[i].length; ++j){
                    horz[i][j] += horz[i-1][j];
                }
            }

            //debug
            /*
            for(int i=0; i<matrix.length; ++i) {
                for (int j = 0; j < matrix[i].length; ++j)
                    System.out.print(horz[i][j] + " ");
                System.out.println();
            }*/

            if(horz[horz.length-1][horz[0].length-1] == 0)
                return 0;
            int maxS =1;
            int maxSi = 1;
            for(int i=0; i<horz.length; ++i){
                for(int j=0; j<horz[i].length; ++j){
                    int tmaxS = Math.min(horz.length-i, horz[i].length-j);
                    for(int k=maxSi+1; k<=tmaxS; ++k){
                        //top left = (i, j)
                        //bottom right = (i+k-1, j+k-1)

                        int n1 = horz[i+k-1][j+k-1] - safeGet(horz, i-1, j+k-1) - safeGet( horz, i+k-1, j-1)
                                + safeGet(horz, i-1, j-1);

                        if(n1 == k* k) {
                            //System.out.format("Found square of %d: %d %d %d %d\n", k, i, j, i+k-1, j+k-1);
                            maxS = Math.max(maxS, n1);
                            maxSi = k;
                        }
                    }
                }
            }

            return maxS;
        }

        private int safeGet(int[][] mat, int i, int j){
            if(i< 0 || i>=mat.length) return 0;
            else if(j<0 || j>=mat[0].length) return 0;
            else return mat[i][j];
        }
    }
}
