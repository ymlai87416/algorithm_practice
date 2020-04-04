package Leetcode;

/*
number: 48
url: https://leetcode.com/problems/rotate-image/
level: medium
solution: rotate layer by layer
 */

public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        Solution s = new Solution();
        s.rotate(matrix);

        for(int i=0; i<matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }

    static
    class Solution {
        public void rotate(int[][] matrix) {
            int n = matrix.length;

            rotateRing(matrix, 0, 0, matrix.length);
        }

        private void rotateRing(int[][] matrix, int x, int y, int n){

            if(n<2) return;

            //move it this way
            for(int i=0; i<n-1; ++i){
                int temp = matrix[x][y+i];
                /*
                matrix[x][y+i] = matrix[x+i][y+n-1];
                matrix[x+i][y+n-1] = matrix[x+n-1][y+n-1-i];
                matrix[x+n-1][y+n-1-i] = matrix[x+n-1-i][y];
                matrix[x+n-1-i][y] = temp;
                */

                matrix[x][y+i] = matrix[x+n-1-i][y];
                matrix[x+n-1-i][y] =  matrix[x+n-1][y+n-1-i];
                matrix[x+n-1][y+n-1-i] = matrix[x+i][y+n-1];
                matrix[x+i][y+n-1] = temp;
            }

            //debug(matrix);
            rotateRing(matrix, x+1, y+1, n-2);
        }

        private void debug(int[][] matrix){
            for(int i=0; i<matrix.length; ++i) {
                for (int j = 0; j < matrix[i].length; ++j)
                    System.out.print(matrix[i][j] + " ");
                System.out.println();
            }
        }
    }

}
