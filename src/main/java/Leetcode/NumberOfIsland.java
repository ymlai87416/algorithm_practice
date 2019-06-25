package Leetcode;

public class NumberOfIsland {
    public static void main(String[] args){
        int a = 0b11111111111111111111111111111101;
        Solution s = new Solution();
        System.out.println(s.numIslands(null));
    }

    static
    class Solution {
        public int numIslands(char[][] grid) {
            int r = 0;
            for(int i=0; i<grid.length; ++i){
                for(int j=0; j<grid[0].length; ++j){
                    if(grid[i][j] == '1'){
                        floodFill(grid, i, j);
                        r+= 1;
                    }
                }
            }

            return r;
        }

        private void floodFill(char[][] grid, int i, int j){
            grid[i][j] = 'X';
            if(i > 0 && grid[i-1][j] == '1') floodFill(grid, i-1, j);
            if(j > 0 && grid[i][j-1] == '1') floodFill(grid, i, j-1);
            if(i < grid.length-1 && grid[i+1][j] == '1') floodFill(grid, i+1, j);
            if(j < grid[0].length-1 && grid[i][j+1] == '1') floodFill(grid, i, j+1);
        }
    }
}
