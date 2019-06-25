package Leetcode;

import java.util.Arrays;

public class SearchA2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,3,5,7},{10,11,16,20},{23,30,34,50}
        };
        Solution sol = new Solution();
        boolean r = sol.searchMatrix(matrix, 3);
        System.out.println(r);
    }

    static
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int b = binarySearch(matrix, target);
            return b != -1;
        }

        private int binarySearch(int[][] matrix, int target) {
            if(matrix.length== 0) return -1;
            int start = 0;
            int end = (matrix.length * matrix[0].length - 1);
            int mid;

            while (start <= end) {
                mid = (start + end) / 2;

                int midValue = matrix[mid / matrix[0].length][mid % matrix[0].length];

                if (midValue == target) return mid;
                else if (midValue < target)
                    start = mid + 1;
                else
                    end = mid - 1;
            }

            return -1;
        }
    }
}
