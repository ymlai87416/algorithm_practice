package Leetcode;

/*
number: 36
problem: https://leetcode.com/problems/valid-sudoku/
level: Medium
solution: just check the number, don't need to solve it.

#hashTable

 */

public class ValidSudoku {
    public static void main(String[] args) {
        char[][] board = new char[][]
            {
                    {'5','3','.','.','7','.','.','.','.'},
                    {'6','.','.','1','9','5','.','.','.'},
                    {'.','9','8','.','.','.','.','6','.'},
                    {'8','.','.','.','6','.','.','.','3'},
                    {'4','.','.','8','.','3','.','.','1'},
                    {'7','.','.','.','2','.','.','.','6'},
                    {'.','6','.','.','.','.','2','8','.'},
                    {'.','.','.','4','1','9','.','.','5'},
                    {'.','.','.','.','8','.','.','7','9'}
            };

        Solution s = new Solution();
        System.out.println(s.isValidSudoku(board));
    }

    static
    class Solution {
        public boolean isValidSudoku(char[][] board) {
            boolean[] test9 = new boolean[9];
            //verticle
            for(int i=0; i<9; ++i){
                for(int u=0; u<9; ++u) test9[u] = false;
                for(int j=0; j<9; ++j){
                    if(board[i][j] != '.'){
                        char c = board[i][j];
                        if(test9[c-'1']) return false;
                        test9[c-'1'] = true;
                    }
                }
            }
            //horizontal
            for(int i=0; i<9; ++i){
                for(int u=0; u<9; ++u) test9[u] = false;
                for(int j=0; j<9; ++j){
                    if(board[j][i] != '.'){
                        char c = board[j][i];
                        if(test9[c-'1']) return false;
                        test9[c-'1'] = true;
                    }
                }
            }
            //grid3x3
            for(int i=0; i<3; ++i){
                for(int j=0; j<3; ++j){
                    for(int u=0; u<9; ++u) test9[u] = false;

                    for(int k=0; k<3; ++k){
                        for(int l=0; l<3; ++l){
                            int x = i*3 +k;
                            int y = j*3 +l;
                            char c = board[x][y];
                            if(c != '.') {
                                if (test9[c - '1']) return false;
                                test9[c - '1'] = true;
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
