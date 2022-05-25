package ctci.hard;

public class MaxBlackSquare {

    public int[] maxBlackSquare(int[][] matrix){

        //do a horizontal prefix sum and vertical prefix sum
        int n = matrix.length;
        int[][] hPrefixSum = new int[n][n];
        int[][] vPrefixSum = new int[n][n];

        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                hPrefixSum[i][j] = matrix[i][j] + hPrefixSum[i][j-1];
                vPrefixSum[i][j] = matrix[i][j] + vPrefixSum[i-1][j];
            }
        }

        //search for n-
        for(int i= n; i>=1; --i){

            for(int x =0; x+i-1 <n; ++x){
                for(int y=0; y+i-1 <n; ++y){
                    int top = x;
                    int left = y;
                    int right = y+n-1;
                    int bottom = x+n-1;

                    int leftVCnt = vPrefixSum[bottom][left]
                            - (top-1 < 0 ? 0: vPrefixSum[top-1][left]);
                    int rightVCnt = vPrefixSum[bottom][right]
                            - (top -1 < 0 ? 0: vPrefixSum[top-1][right]);
                    int topHCnt = hPrefixSum[top][right]
                            - (left -1 < 0 ? 0 : hPrefixSum[top][left-1]);
                    int bottomHCnt = hPrefixSum[bottom][right]
                            - (left -1 < 0 ? 0: hPrefixSum[bottom][left-1]);

                    if(leftVCnt == n && rightVCnt == n && topHCnt == n && bottomHCnt == n)
                        return new int[]{top, left, bottom, right};

                }
            }

        }

        //should not be here
        return null;
    }


    public static void main(String[] args) {

    }
}
