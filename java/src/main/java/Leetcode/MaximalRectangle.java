package Leetcode;

import java.util.Stack;


/*
number: 85
problem: https://leetcode.com/problems/maximal-rectangle/
level: hard
solution: 1. naive is O(n^4), check if i, j, i+h, j+w all have 1
            2. O(n^3) -> create a height array for each row, and use brute force to solve it (16ms)
            3. O(n^2) -> create a height array for each row, solve it with O(n) 11ms
related problem: 84 https://leetcode.com/problems/largest-rectangle-in-histogram/

#hashTable #dp #stack

 */

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

            //return dpHelper(matrix);
            return helper2(matrix);

        }

        //O(n^2) algorithm, using largest rectange area O(n)
        public int helper2(char[][] matrix){

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

                r = Math.max(r, largestRectangleArea2(height));
            }

            return r;
        }

        private int largestRectangleArea2(int[] heights){
            //left record the point smaller than current
            int[] left = new int[heights.length];
            int r = 0;

            //int[] right = new int[heights.length];

            left[0] = 0;
            Stack<Integer> st = new Stack<Integer>();
            st.push(0);
            for(int i=1; i<heights.length; ++i){
                left[i]=0;
                while(!st.isEmpty()){
                    int u = st.peek();
                    if(heights[u] < heights[i]) {
                        left[i] = u+1;
                        break;
                    }
                    else
                        st.pop();
                }

                //if nothing left = 0;
                st.push(i);
            }

            st.clear();

            //right[heights.length-1] = heights.length-1;
            r = Math.max(r, heights[heights.length-1] * (heights.length-1 - left[heights.length-1] + 1));
            int right;

            st.push(heights.length-1);
            for(int i=heights.length-1; i>=0; --i){
                right=heights.length-1;

                while(!st.isEmpty()){
                    int u = st.peek();
                    if(heights[u] < heights[i]) {
                        right = u-1;
                        break;
                    }
                    else
                        st.pop();
                }

                //if nothing left = 0;
                st.push(i);

                r = Math.max(r, heights[i] * (right - left[i] + 1));
            }

            return r;
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

        //loop all i, j pair to find the largest rectangle
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
