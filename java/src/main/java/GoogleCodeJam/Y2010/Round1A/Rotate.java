package GoogleCodeJam.Y2010.Round1A;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 30/4/2016.
 */
public class Rotate {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "A-large-practice";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, true)); ;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void solve() {
        int ans = 0;

        int board; int k;
        board = sc.nextInt();
        k = sc.nextInt();
        sc.nextLine();
        char[][] boardx = new char[board][board];

        for(int i=0; i<board; ++i){
            String bb = sc.nextLine();
            for(int j=0; j<board; ++j){
                boardx[i][j] = bb.charAt(j);
            }
        }
        char[][] newboard = new char[board][board];

        int ni, nj;
        ni =0;
        for(int i=0; i<board; ++i){
            nj = 0;
            for(int j=board-1; j>=0; --j){
                newboard[ni][nj++] = boardx[j][i];
            }
            ++ni;
        }

        debugBoard(newboard);

        for(int i=0; i<board; ++i)Arrays.fill(boardx[i], '.');

        for(int ny=0; ny<board; ++ny){
            int tofill = board-1;
            for(int nx=board-1; nx>=0; --nx){
                if(newboard[nx][ny] == '.') continue;
                boardx[tofill--][ny] = newboard[nx][ny];
            }
        }

        debugBoard(boardx);

        boolean redWin=false;
        boolean blueWin = false;
        for(int i=0; i<board && !(redWin && blueWin) ; ++i){
            for(int j=0; j<board && !(redWin && blueWin); ++j){
                if(boardx[i][j] == 'R'){
                    redWin = redWin || checkWin(boardx, i, j, k, board);
                } else if(boardx[i][j] == 'B'){
                    blueWin = blueWin || checkWin(boardx, i, j, k, board);
                }
            }
        }

        if(redWin && blueWin)
            out.println("Both");
        else if(redWin)
            out.println("Red");
        else if(blueWin)
            out.println("Blue");
        else
            out.println("Neither");
    }

    boolean checkWin(char[][] boardx, int i, int j, int k, int board){
        //horizontal
        boolean result = false;
        int cnt = 0;
        for(int p=0; p<k && j+p<board; ++p){
            if(boardx[i][j] == boardx[i][j+p]) ++cnt;
            else break;
        }
        if(cnt == k) {return true;}
        // down
        cnt = 0;
        for(int p=0; p<k && i+p<board; ++p){
            if(boardx[i][j] == boardx[i+p][j]) ++cnt;
            else break;
        }
        if(cnt == k) {return true;}
        // left
        cnt = 0;
        for(int p=0; p<k && j+p<board && i-p>=0; ++p){
            if(boardx[i][j] == boardx[i-p][j+p]) ++cnt;
            else break;
        }
        if(cnt == k) {return true;}
        // right
        cnt = 0;
        for(int p=0; p<k && j+p<board && i+p<board; ++p){
            if(boardx[i][j] == boardx[i+p][j+p]) ++cnt;
            else break;
        }
        if(cnt == k) {return true;}

        return false;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            solve();
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Rotate().run();
    }

    public void debugBoard(char[][] board){
        for(int i=0; i<board.length; ++i){
            for(int j=0; j<board[i].length; ++j){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
