package ctci.sortsearch;

public class SortedMatrixSearch {

    public boolean sortedMatrixSearch(int[][] matrix, int target){
        int m = matrix.length;
        int n = matrix[0].length;

        int row = 0;
        int column = n-1;

        while(row < m && column >= 0){
            if(matrix[row][column] == target)
                return true;
            else if(matrix[row][column] > target)
                column--;
            else
                row++;

        }

        return false;
    }


    public static void main(String[] args) {
        int[][] data = new int[][]{
                {15, 20, 70, 85},
                {20, 35, 80, 95},
                {30, 55, 95, 105 },
                {40, 80, 100, 120}
        };

        SortedMatrixSearch test = new SortedMatrixSearch();
        System.out.println(test.sortedMatrixSearch(data, 95));
        System.out.println(test.sortedMatrixSearch(data, 170));
        System.out.println(test.sortedMatrixSearch(data, 10));
    }
}
