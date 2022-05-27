package ctci.hard;

public class MaxSubmatrix {

    public int maxSubmatrix(int[][] matrix){

        //only veritical prefix sum
        int m=  matrix.length;
        int n = matrix[0].length;
        int[][] vPrefixSum = new int[m+1][n];

        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j)
                vPrefixSum[i+1][j] = matrix[i][j] + vPrefixSum[i][j];
        }
        int result = Integer.MIN_VALUE;
        for(int i=0; i<m; ++i){
            for(int j=i+1; j<=m; ++j){
                int sum = 0;
                for(int k=0; k<n; ++k){
                    int val = vPrefixSum[j][k] - vPrefixSum[i][k];
                    sum = Math.max(sum+val, val);

                    if(sum > result)
                        result = sum;
                }
            }
        }
        return result;
    }

    private int bf(int[][] data ) {
        int m = data.length;
        int n = data[0].length;
        int rr = 0;
        for (int i = 0; i < m; ++i){
            for (int j = i + 1; j < m; ++j) {
                for (int k = 0; k < n; ++k) {
                    for (int l = k + 1; l < n; ++l) {
                        int x = sumHelper(data, i, j, k, l);
                        if(rr < x)
                            rr=x;
                    }
                }
            }
        }
        return rr;
    }
    private int sumHelper(int[][] data, int top, int bottom, int left, int right){
        int d = 0;
        for(int i=top; i<=bottom; ++i){
            for(int j=left; j<=right; ++j){
                d += data[i][j];
            }
        }
        return d;
    }

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {9, -8, 1, 3, -2},
                {-3, 7, 6, -2, 4},
                {6, -4, -4, 8, -7}
        };
        MaxSubmatrix test =new MaxSubmatrix();
        System.out.println(test.maxSubmatrix(data));
        System.out.println(test.bf(data));

    }
}
