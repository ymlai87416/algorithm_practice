package ctci.arraystring;

//TODO: not working, find a way to fix it.
public class RotateMatrix {
    public void rotateMatrix(int[][] matrix){
        int n = matrix.length;
        int mid = n/2;
        int loopMid= n%2==1 ? (n+1)/2 : n/2;

        for(int i=0; i<loopMid; ++i){
            for(int j=0; j<loopMid; ++j){
                int tv;
                int cx  = i;
                int cy = j;

                tv = matrix[cx][cy];
                for(int k=0; k<4; ++k){
                    int ox = cx - mid;  // < 0
                    int oy = cy - mid;  // > 0

                    int nx = -oy + mid;
                    int ny = ox + mid;

                    int tv2 = matrix[nx][ny];
                    matrix[nx][ny] = tv;

                    cx = nx;
                    cy = ny;
                    tv = tv2;
                }

            }
        }

    }


    public static void main(String[] args) {
        RotateMatrix test = new RotateMatrix();

        int[][] matrix3 = {
                {1,2,3}, {4,5,6}, {7,8,9}
        };

        int[][] matrix4 = {
                {1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}
        };

        test.rotateMatrix(matrix3);
        //test.rotateMatrix(matrix4);

        printMatrix("3x3", matrix3);
        printMatrix("4x4", matrix4);
    }

    private static void printMatrix(String header, int[][] matrix){
        System.out.println(header);

        int n = matrix.length;

        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }
}
