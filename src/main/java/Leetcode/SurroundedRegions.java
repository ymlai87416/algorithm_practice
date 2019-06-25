package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class SurroundedRegions {
    public static void main(String[] args){
        char[][] board = new char[][]{
                {'O','O','O','O','X','X'},
                {'O','O','O','O','O','O'},
                {'O','X','O','X','O','O'},
                {'O','X','O','O','X','O'},
                {'O','X','O','X','O','O'},
                {'O','X','O','O','O','O'},
        };

        Solution s = new Solution();
        s.solve(board);
        //s.debugBoard(board);
    }

    static
    class Solution {
        private void debugBoard(char[][] board){
            System.out.println("******************");
            for(int i=0; i<board.length; ++i){
                for(int j=0; j<board[i].length; ++j)
                    System.out.print(board[i][j]);
                System.out.println();
            }
            System.out.println("******************");
        }

        public void solve(char[][] board) {
            for(int i=0; i<board.length; ++i)
                for(int j=0; j<board[i].length; ++j)
                    if(board[i][j] == 'O') board[i][j] = 'C';

            for(int i=0; i<board.length; ++i){
                for(int j=0; j<board[i].length; ++j){
                    if(board[i][j] == 'C') {
                        ArrayList<Integer> xList = new ArrayList<Integer>();
                        ArrayList<Integer> yList = new ArrayList<Integer>();
                        floodFill(board, i, j, xList, yList, 'O');
                        debugBoard(board);

                        boolean canTurnB = true;
                        for(int u=0; u<xList.size(); ++u){
                            canTurnB = canTurnB && canTurn(board, xList.get(u), yList.get(u));
                        }

                        System.out.println("can turn? " + canTurnB);

                        if(canTurnB){
                            for(int u=0; u<xList.size(); ++u){
                                board[xList.get(u)][yList.get(u)] = 'X';
                            }
                        }
                    }
                }
            }
        }

        private void floodFill(char[][] board, int x, int y, List<Integer> xList, List<Integer> yList, char floodChar){
            char c = board[x][y];
            xList.add(x); yList.add(y);
            board[x][y] = floodChar;
            if(x > 0 && board[x-1][y] == c) floodFill(board, x-1, y, xList, yList, floodChar);
            if (x < board.length-1 && board[x+1][y] == c) floodFill(board, x+1, y, xList, yList, floodChar);
            if (y > 0 && board[x][y-1] == c) floodFill(board, x, y-1, xList, yList, floodChar);
            if (y < board[x].length-1 && board[x][y+1] == c) floodFill(board, x, y+1, xList, yList, floodChar);
        }

        private boolean canTurn(char[][] board, int x, int y){
            if(x == 0 || x == board.length-1) return false;
            if(y == 0 || y == board[x].length-1) return false;

            return (board[x-1][y] == 'X' || board[x-1][y] == 'O') &&
                    (board[x+1][y] == 'X' || board[x+1][y] == 'O') &&
                    (board[x][y-1] == 'X' || board[x][y-1] == 'O') &&
                    (board[x][y+1] == 'X' || board[x][y+1] == 'O');
        }

    }
}
