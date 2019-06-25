package Leetcode;

import java.util.Stack;

public class MaximalRectangle {
    public static void main(String[] args){
        /*
        char[][] input = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        */

        char[][] input = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        input = new char[][]{{'0', '1'}, {'1', '0'}};

        //input = new char[][]{{'1'}};
        Solution s = new Solution();
        System.out.println(s.maximalRectangle(input));
    }

    static
    class Solution {

        public int maximalRectangle(char[][] matrix) {

            return dpHelper(matrix);

        }

        //O(n^3) algorithm, using largest rectangle area
        public int dpHelper(char[][] matrix){

            if(matrix.length == 0) return 0;
            if(matrix[0].length == 0) return 0;
            //call
            int[] height = new int[matrix[0].length];
            int r = 0;
            for(int i=0; i<matrix.length; ++i){
                for(int j=0; j<matrix[i].length; ++ j){
                    if(matrix[i][j] == '1')
                        height[j] = height[j] + (matrix[i][j] == '1' ? 1:0);
                    else
                        height[j] = 0;
                }

                r = Math.max(r, largestRectangleArea(height));
            }

            return r;
        }

        private int largestRectangleArea(int[] heights) {
            int res = 0;
            for (int i = 0; i < heights.length; ++i) {
                if (i + 1 < heights.length && heights[i] <= heights[i + 1]) {
                    continue;
                }
                int minH = heights[i];
                for (int j = i; j >= 0; --j) {
                    minH = Math.min(minH, heights[j]);
                    int area = minH * (i - j + 1);
                    res = Math.max(res, area);
                }
            }
            return res;
        }

    }
}
