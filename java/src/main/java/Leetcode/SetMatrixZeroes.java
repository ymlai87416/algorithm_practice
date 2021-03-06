package Leetcode;

/**
number: 73
url: https://leetcode.com/problems/set-matrix-zeroes/
level: medium
solution: create a array of m, and n, and then set m[i] and n[j] = 0 if I find arr[i][j] = 0

 #array

 **/

public class SetMatrixZeroes {
    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1,3,1,5}};

        nums = new int[][]{
                {1, 1, 1},
                {0, 1, 2}
        };

        Solution s = new Solution();
        s.setZeroes(nums);

        for(int i=0; i<nums.length; ++i) {
            for (int j = 0; j < nums[i].length; ++j)
                System.out.print(nums[i][j] + " ");
            System.out.println();
        }
    }

    static
    class Solution {
        public void setZeroes(int[][] matrix) {
            int zeroDir = 0;
            for(int i=0; i<matrix.length; ++i){
                for(int j=0; j<matrix[i].length; ++j){
                    if(matrix[i][j] == 0){
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;  //they have been traversed.

                        if(i == 0)
                            zeroDir |= 1;
                        if(j == 0)
                            zeroDir |= 2;
                    }
                }
            }

            for(int i=1; i<matrix.length; ++i){
                if(matrix[i][0] == 0){
                    for(int j=0; j<matrix[i].length; ++j)
                        matrix[i][j]= 0;
                }
            }

            for(int i=1; i<matrix[0].length; ++i){
                if(matrix[0][i] == 0){
                    for(int j=0; j<matrix.length; ++j)
                        matrix[j][i]= 0;
                }
            }

            if(matrix[0][0] == 0){
                if((zeroDir & 2) > 0)
                    for(int i=0; i<matrix.length; ++i)
                        matrix[i][0] =0;
                if((zeroDir & 1) > 0)
                    for(int i=0; i<matrix[0].length; ++i)
                        matrix[0][i] =0;
            }
        }
    }
}
