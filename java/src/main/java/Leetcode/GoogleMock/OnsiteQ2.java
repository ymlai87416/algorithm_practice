package Leetcode.GoogleMock;

public class OnsiteQ2 {

    public int maximalSquare(char[][] matrix) {
        //so what we do
        //rect_n(x, y) = rect_n-1(x, y) & rect_n-1(x+1, y) & rect_n-1(x, y+1) & rect_n-1(x+1, y+1)

        //this will lead to complexity of O(min(n, m))
        int m = matrix.length;
        int n = matrix[0].length;

        boolean allZero = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == '1'){
                    allZero = false;
                    break;
                }
            }
        }

        if(allZero) return 0;
        if(n ==1 || m==1) return 1;

        //now due with 2
        char[][] matrix2 = new char[m][n];

        int res = 1;
        for (int i = 2; i <= Math.min(m, n); i++) {
            //if I cannot find a rect in this step, return the previous should be ok
            allZero = true;
            for (int j = 0; j <= m-i; j++) {
                for (int k = 0; k <= n-i; k++) {
                    matrix2[j][k] = (matrix[j][k]== '1' && matrix[j+1][k] == '1' && matrix[j][k+1]== '1' && matrix[j+1][k+1] == '1') ? '1': '0';
                    if(matrix2[j][k] == '1') allZero = false;
                }
            }

            if(allZero)
                break;
            else res++;

            char[][] t = matrix;
            matrix = matrix2;
            matrix2 = t;
        }

        return res*res;
    }

    public static void main(String[] args){
        OnsiteQ2 s = new OnsiteQ2();
        char[][] m1 = new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        char[][] m2 = new char[][]{{'0','1'},{'1','0'}};
        char[][] m3 = new char[][]{{'0'}};
        char[][] m4 = new char[][]{{'1','1'},{'1','1'}};

        char[][] m5= new char[][]{{'1','1','1','1','1','1','1','1'},{'1','1','1','1','1','1','1','0'},{'1','1','1','1','1','1','1','0'},{'1','1','1','1','1','0','0','0'},{'0','1','1','1','1','0','0','0'}};

        System.out.println(s.maximalSquare(m5));
        System.out.println(s.maximalSquare(m1));
        System.out.println(s.maximalSquare(m2));
        System.out.println(s.maximalSquare(m3));

    }
}
