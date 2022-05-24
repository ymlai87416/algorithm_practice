package ctci.arraystring;

public class ZeroMatrix {
    private void zeroMatrix(int[][] matrix){
        boolean colZ = false;
        int m = matrix.length;
        int n= matrix[0].length;

        for(int i=0; i<m;++i){
            for(int j=0; j<n; ++j){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    if(j == 0) colZ =true;
                }
            }
        }

        //set col 0
        for(int i=1; i<n; ++i){
            if(matrix[0][i] == 0)
                for(int j=0; j<m; ++j)
                    matrix[j][i]= 0;
        }
        //set row 0
        for(int i=0; i<m; ++i){
            if(matrix[i][0] == 0)
                for(int j=0; j<n; ++j)
                    matrix[i][j]= 0;
        }

        if(colZ)
            for(int j=0; j<m; ++j)
                matrix[j][0]= 0;


    }

    public static void main(String[] args) {
        ZeroMatrix test = new ZeroMatrix();
        int n = 4;
        int[][] matrix = new int[][]{
                {0, 3, 4, 1},
                {2, 3, 5, 0},
                {2, 2, 2, 2},
                {0, 9, 9, 0}
        };

        test.zeroMatrix(matrix);

        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }
}
