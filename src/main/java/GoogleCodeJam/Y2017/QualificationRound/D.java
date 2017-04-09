package GoogleCodeJam.Y2017.QualificationRound;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by ymlai on 8/4/2017.
 */
public class D {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "D-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false)); ;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    char[][] board;
    int N;

    private void solve(char[][] board, int N) {
        int ans = 0;

        Map<Pair, String> commands = new TreeMap<>();

        this.board = board;
        this.N = N;

        int p= 0;
        int x = 0;
        for(int i=0; i<N; ++i)
            for(int j=0; j<N; ++j)
                if(board[i][j] == '+')
                    p++;
                else if(board[i][j] == 'x')
                    x++;
                else if(board[i][j] == 'o'){
                    p++;
                    x++;
                }

        int cnt = 0;

        //add plus
        for(int i=0; i<N; ++i) {
            if(i > 0 && i< N-1) continue;
            for (int j = 0; j < N; ++j) {
                if (board[i][j] == '+') continue;
                if (checkSafeP(i, j)) {
                    if (board[i][j] == 'x') {
                        board[i][j] = 'o';
                        commands.put(new Pair(i, j), "o");
                        ++p;
                    } else {
                        board[i][j] = '+';
                        commands.put(new Pair(i, j), "+");
                        ++p;
                    }
                }
            }
        }

        //add cross
        for(int i=0; i<N && x<N; ++i){
            for(int j=0; j<N; ++j){
                if(board[i][j] == 'x') continue;
                if(checkSafeX(i, j)) {
                    if (board[i][j] == '+') {
                        board[i][j] = 'o';
                        commands.put(new Pair(i,j), "o");
                        ++x;
                    }
                    else {
                        board[i][j] = 'x';
                        commands.put(new Pair(i,j), "x");
                        ++x;
                    }
                }
            }
        }

        //debug
        System.out.println("debug");
        int score =0;
        for(int i=0; i<N; ++i){
            for(int j=0; j<N; ++j) {
                if(board[i][j] == 'o') score +=2;
                else if(board[i][j] == 'x' || board[i][j] == '+') score+=1;
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

        out.println(score + " " + commands.size());
        for(Pair pair: commands.keySet()){
            out.println(commands.get(pair) + " " + (pair.r+1) + " " + (pair.c+1));
        }
    }

    private boolean checkSafeP(int r, int c){
        //check r+c
        for(int i=0; i<N; ++i)
            if(r+c-i >= N || r+c-i < 0 ) continue;
            else if(board[i][r+c-i] == '+' || board[i][r+c-i] == 'o')
                return false;

        //check r-c
        for(int i=0; i<N; ++i)
            if(i-r+c < 0 || i-r+c >=N ) continue;
            else if(board[i][i-r+c] == '+' || board[i][i-r+c] == 'o')
                return false;

        return true;
    }

    private boolean checkSafeX(int r, int c){
        for(int i=0; i<N; ++i)
            if(board[r][i] == 'x' || board[r][i] == 'o')
                return false;
        for(int i=0; i<N; ++i)
            if(board[i][c] == 'x' || board[i][c] == 'o')
                return false;

        return true;
    }

    private void solveBruteForce(){}


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int N = sc.nextInt();
            int C = sc.nextInt();

            char[][] board = new char[N][N];
            for(int p=0; p<N; ++p)
                for(int q=0; q<N; ++q)
                    board[p][q] = '.';

            for(int p=0; p<C; ++p){
                String s = sc.next();
                int r = sc.nextInt();
                int c = sc.nextInt();

                board[r-1][c-1] = s.charAt(0);
            }

            solve(board, N);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new D().run();
    }
}

class Pair implements Comparable<Pair>{
    int r, c;
    public Pair(int r, int c){
        this.r = r;
        this.c = c;
    }


    @Override
    public int compareTo(Pair o) {
        if(r > o.r)
            return 1;
        else if(r < o.r)
            return -1;
        else {
            if(c > o.c)
                return 1;
            else if(c < o.c)
                return -1;
            else
                return 0;
        }
    }
}

