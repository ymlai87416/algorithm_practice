package ctci.hard;

public class MaxBlackSquare {

    public int[] maxBlackSquare(int[][] matrix){

        //do a horizontal prefix sum and vertical prefix sum
        int n = matrix.length;
        int[][] hPrefixSum = new int[n][n+1];
        int[][] vPrefixSum = new int[n+1][n];

        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                hPrefixSum[i][j+1] = matrix[i][j] +  hPrefixSum[i][j];
                vPrefixSum[i+1][j] = matrix[i][j] +  vPrefixSum[i][j];
            }
        }

        //search for n-
        for(int i= n; i>=1; --i){

            for(int x =0; x+i-1 <n; ++x){
                for(int y=0; y+i-1 <n; ++y){
                    int top = x;
                    int left = y;
                    int right = y+i-1;
                    int bottom = x+i-1;

                    int leftVCnt = vPrefixSum[bottom+1][left] - vPrefixSum[top][left];
                    int rightVCnt = vPrefixSum[bottom+1][right] - vPrefixSum[top][right];
                    int topHCnt = hPrefixSum[top][right+1] - hPrefixSum[top][left];
                    int bottomHCnt = hPrefixSum[bottom][right+1] - hPrefixSum[bottom][left];

                    if(leftVCnt == i && rightVCnt == i && topHCnt == i && bottomHCnt == i)
                        return new int[]{top, left, bottom, right};

                }
            }

        }

        //should not be here
        return null;
    }




    public static void main(String[] args) {
        MaxBlackSquare test = new MaxBlackSquare();
        int[][] data = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0},
                {1, 0, 1, 1, 1, 0},
                {1, 0, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1}
        };

        int[] r = test.maxBlackSquare(data);
        System.out.printf("top: %d, left: %d, bottom: %d, right: %d\n", r[0], r[1], r[2], r[3]);
    }
}
