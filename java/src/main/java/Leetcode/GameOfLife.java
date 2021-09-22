package Leetcode;

/**
problem: https://leetcode.com/problems/game-of-life/
level: medium
solution: don't modify the board, create a new board and save the result.

#array

 */

public class GameOfLife {
    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        };
        Solution sol = new Solution();
        sol.gameOfLife(nums);
    }

    static
    class Solution {
        public void gameOfLife(int[][] board) {
            int[][] nextBoard = new int[board.length][board[0].length];

            for(int i=0; i<board.length; ++i){
                for(int j=0; j<board[i].length; ++j){

                    if(board[i][j] == 1) {
                        int neighbourCnt = charCount(board, i, j, 1);
                        //rule 1: under populate
                        if(neighbourCnt < 2)
                            nextBoard[i][j] = 0;
                        //rule 2: survive
                        else if(neighbourCnt == 2 || neighbourCnt == 3)
                            nextBoard[i][j] = 1;
                        //rule 3: over populate
                        else if(neighbourCnt > 3)
                            nextBoard[i][j] = 0;
                    }
                    else {
                        //rule 4: reproduce
                        if( charCount(board, i, j, 1) == 3)
                            nextBoard[i][j]=1;
                        else
                            nextBoard[i][j]=0;
                    }

                    int dc = charCount(board, i, j, 1);
                    //System.out.format("%d %d %d %d\n", i, j, dc, board[i][j]);
                }
            }

            for(int i=0; i<board.length; ++i){
                for(int j=0; j<board[i].length; ++j)
                    board[i][j] = nextBoard[i][j];
            }
        }

        private int charCount(int[][] board, int x, int y, int c){
            int r = 0;
            for(int i=x-1; i<=x+1; ++i){
                for(int j=y-1; j<=y+1; ++j){
                    if(i == x && y == j) continue;
                    if(0<=i && i<board.length && 0<=j && j<board[i].length && board[i][j] == c)
                        ++r;
                }
            }

            return r;
        }
    }
}
