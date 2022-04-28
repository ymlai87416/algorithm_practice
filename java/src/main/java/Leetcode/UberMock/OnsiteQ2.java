package Leetcode.UberMock;

import java.util.Arrays;

public class OnsiteQ2 {

    public boolean isValidSudoku(char[][] board) {
        int[] numExist = new int[9];
        for (int i = 0; i < 9; i++) {
            Arrays.fill(numExist, 0);
            //we check for the row
            for (int j = 0; j < 9; j++) {
                if(board[i][j] != '.')
                    if(numExist[board[i][j]-'1'] > 0)
                        return false;
                    else numExist[board[i][j]-'1'] = 1;
            }
        }

        for (int i = 0; i < 9; i++) {
            Arrays.fill(numExist, 0);
            //we check for the col
            for (int j = 0; j < 9; j++) {
                if(board[j][i] != '.')
                    if(numExist[board[j][i]-'1'] > 0)
                        return false;
                    else numExist[board[j][i]-'1'] = 1;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(numExist, 0);
                //we check for the small square

                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        int cx = i*3+k;
                        int cy = j*3+l;
                        if(board[cx][cy] != '.')
                            if(numExist[board[cx][cy]-'1'] > 0)
                                return false;
                            else numExist[board[cx][cy]-'1'] = 1;
                    }
                }


            }
        }

        return true;
    }

    public static void main(String[] args){

    }
}
