package ctci.recursion;
import java.util.*;

public class EightQueens {

    boolean[] row = new boolean[8];
    boolean[] col = new boolean[8];
    boolean[] ld = new boolean[16]; //total of 15 represented by i-j
    boolean[] rd = new boolean[16]; //total of 15, represented by i+j
    char[][] board = new char[8][8];
    int cnt = 0;
    public void eightQueens(){
        cnt = 0;
        for(int i=0; i<8; ++i)
            Arrays.fill(board[i], ' ');
        Arrays.fill(row, true);
        Arrays.fill(col, true);
        Arrays.fill(ld, true);
        Arrays.fill(rd, true);
        backtrack(0);
    }

    private void backtrack(int placed){

        if(placed == 8)
            printBoard();

        //we are placing a piece at row by row, now it is row[placed];

        for(int i=0; i<8; ++i){
            int d1 = placed-i + 7;
            int d2 = placed+i;
            if(col[i] && ld[d1] && rd[d2]){
                col[i] = false;
                ld[d1] = false;
                rd[d2] = false;
                board[placed][i] = 'X';
                backtrack(placed+1);
                col[i] = true;
                ld[d1] = true;
                rd[d2] = true;
                board[placed][i] = ' ';
            }
        }
    }

    private void printBoard(){
        System.out.println("Board " + (++cnt) );
        for(int i=0; i<8; ++i){
            for(int j=0; j<8; ++j){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        EightQueens test = new EightQueens();
        //There are 92 distinct solution.
        test.eightQueens();
    }
}
