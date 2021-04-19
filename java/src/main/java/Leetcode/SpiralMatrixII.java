package Leetcode;

/**
number: 59
problem: https://leetcode.com/problems/spiral-matrix-ii/
level: medium
solution: layer by layer

#array

 **/

public class SpiralMatrixII {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] r = s.generateMatrix(1);
        System.out.println(r);
    }

    static
    class Solution {
        public int[][] generateMatrix(int n) {
            int[][] m = new int[n][n];

            int cnt=1;

            int x, y; x= 0; y=0;
            while(n > 0){
                cnt =fill(x, y, m, n, cnt);
                n-=2;
                x++; y++;
            }

            return m;
        }

        private int fill(int x, int y, int[][] m, int len, int start){
            if(len == 0) return start;
            else if(len == 1){
                m[x][y] = start++;
                return start;
            }
            else {
                int top = y;
                int left = x;
                int bottom = y + len - 1;
                int right = x + len - 1;
                for (int i = left; i < right; ++i) {
                    m[top][i] = start++;
                }
                for (int i = top; i < bottom; ++i) {
                    m[i][right] = start++;
                }
                for (int i = right; i > left; --i) {
                    m[bottom][i] = start++;
                }
                for (int i = bottom; i > top; --i) {
                    m[i][left] = start++;
                }
                return start;
            }
        }
    }
}