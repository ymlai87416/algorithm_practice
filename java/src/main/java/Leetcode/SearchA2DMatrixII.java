package Leetcode;

/*
number: 240
url: https://leetcode.com/problems/search-a-2d-matrix-ii/
level: medium
solution: there are 2 corner which is very special, the bottom left and top right
            if the target is bigger, a path can be chosen, the target is smaller, another path can be chosen.

            there is nothing called sorted matrix.. XD
 */

public class SearchA2DMatrixII {
    public static void main(String[] args) {
        int[][] n = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        n = new int[][]{{1,3,5}};

        n = new int[][]{
                {1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}
        };

        n = new int[][]{
                {1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}
        };

        Solution sol = new Solution();
        boolean r = sol.searchMatrix(n, 5);
        System.out.println(r);
    }

    static
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            if(matrix.length==0) return false;

            //return helper(matrix, 0, 0, target);
            return helper2(matrix, target, 0, 0, matrix.length-1, matrix[0].length-1);
            //return helper3(matrix, matrix[0].length-1, 0, target);
        }

        //not a linear search...  40.54%
        public boolean helper(int[][] matrix, int x, int y, int target){
            if(x >= matrix.length) return false;
            if(y >= matrix[x].length) return false;

            if(matrix[x][y] == target)
                return true;
            if(matrix[x][y] < target) {
                boolean r = helper(matrix, x + 1, y, target) || helper(matrix, x, y + 1, target);
                if(!r) matrix[x][y] = Integer.MAX_VALUE;
                return r;
            }
            else
                return false;
        }

        //O(m+n) solution 5ms => 100%
        public boolean helper3(int[][] matrix, int x, int y, int target){
            System.out.format("%d %d\n", x, y);

            if(y >= matrix.length || y < 0) return false;
            if(x >= matrix[y].length || x < 0) return false;

            if(matrix[y][x] == target)
                return true;
            if(matrix[y][x] < target)
                return helper3(matrix, x, y+1, target); //search next row try to find it.
            else
                return helper3(matrix, x-1, y, target); //search right to find it maybe
        }

        //runtime O(n^1.58) 11ms 10.94%
        public boolean helper2(int[][] matrix, int target, int top, int left, int bottom, int right){
            //System.out.format("%d %d %d %d: %d\n", top, left, bottom, right, target);
            //if matrix does not contains any elements
            if(top > bottom || left > right) return false;
            if(top == bottom && left == right) return matrix[top][left] == target;

            // Special case for iteration with 1*2 matrix
            // mat[i][j] and mat[i][j+1] are only two elements.
            // So just check second element
            if (top == bottom && left + 1 == right)
                return matrix[top][right] == target || matrix[top][left] == target;

            //if target > mid, then I can know it is not on the top left rectangle, 2 rectangle to check
            //if target < mid, then I know it must be on the top left rectangle including itself.
            int midx = (left+right)/2;
            int midy = (top+bottom)/2;

            if(target == matrix[midy][midx])
                return true;
            else if(matrix[midy][midx] > target){
                return helper2(matrix, target, top, left, bottom, midx-1) ||
                        //helper2(matrix, target, top, midx, midy, right);
                        helper2(matrix, target, top, midx, midy-1, right);
            }
            else{
                return //helper2(matrix, target, top, midx, midy, right) ||
                        helper2(matrix, target, top, midx+1, midy, right) ||
                        helper2(matrix, target, midy+1, left, bottom, right);

            }

        }
    }
}
