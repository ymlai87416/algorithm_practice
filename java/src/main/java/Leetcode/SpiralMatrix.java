package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
number: 54
problem: https://leetcode.com/problems/spiral-matrix/
level: medium
solution: layer by layer

#array
 **/

public class SpiralMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        //matrix = new int[][]{{1},{3}, {2}};
        Solution s = new Solution();
        System.out.println(s.spiralOrder(matrix));
    }

    static
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            int h = matrix.length-1;
            int w = h >= 0 ? matrix[0].length-1: 0;
            int x, y;
            x = y = 0;
            ArrayList<Integer> result = new ArrayList<>();
            while (h >= 0 && w >= 0) {
                ring(matrix, x, y, y+w, x+h, result);
                x++;
                y++;
                h -= 2;
                w -= 2;
            }

            return result;
        }

        public void ring(int[][] matrix, int left, int top, int right, int bottom, List<Integer> result) {
            if(right == left){
                for(int i=top; i<=bottom; ++i)
                    result.add(matrix[i][left]);
            }
            else if(top == bottom){
                for(int i=left; i<=right; ++i)
                    result.add(matrix[top][i]);
            }
            else {
                for (int i = left; i < right; ++i)
                    result.add(matrix[top][i]);
                for (int i = top; i < bottom; ++i)
                    result.add(matrix[i][right]);
                for (int i = right; i > left; --i)
                    result.add(matrix[bottom][i]);
                for (int i = bottom; i > top; --i)
                    result.add(matrix[i][left]);
            }
        }

    }
}