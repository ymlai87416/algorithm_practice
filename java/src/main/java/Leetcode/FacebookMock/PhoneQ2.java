package Leetcode.FacebookMock;

public class PhoneQ2 {

    static class NumMatrix {

        int[][] sum = null;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length; int n= matrix[0].length;

            sum = new int[m][n];

            for(int i=0; i<m; ++i){
                for (int j = 0; j < n; j++) {
                    int a = i==0 ? 0: sum[i-1][j];
                    int b = j==0 ? 0: sum[i][j-1];
                    int c = (i==0 || j == 0) ? 0 : sum[i-1][j-1];
                    sum[i][j] = a + b - c + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            //sum all up, and we need to get what?
            /*
                from 0, 0 => row1-1, col1-1
                from 0, 0 => row1-1, col2
                from 0, 0 => row1, col2-1;
             */

            if(row1 == 0 && col1 == 0){
                return sum[row2][col2];
            }
            else if(row1 == 0){
                return sum[row2][col2] - sum[row2][col1-1];
            }
            else if(col1 == 0){
                return sum[row2][col2] - sum[row1-1][col2];
            }
            else{
                return +sum[row1-1][col1-1] - sum[row1-1][col2] - sum[row2][col1-1] + sum[row2][col2];
            }
        }
    }

    public static void main(String[] args){
        PhoneQ2 s = new PhoneQ2();

        int[][] mat = new int[][] {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};

        NumMatrix k = new NumMatrix(mat);
        System.out.println(k.sumRegion(2, 1, 4, 3));
        System.out.println(k.sumRegion(1, 1, 2, 2));
        System.out.println(k.sumRegion(1, 2, 2, 4));
    }

}
