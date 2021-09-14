package Facebook.Y2021.Quali;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class B {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\B-test.in";
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\xs_and_os_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\xs_and_os_output.txt";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            if(OUT == null)
                out = new PrintStream(System.out);
            else out = new PrintStream(OUT);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private void debug(Object... s){
        if(debugflag) {
            //System.out.println(s);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                sb.append(s[i].toString() + " ");
            }
            System.out.println("\033[0;34m" + sb.toString() + "\033[0;30m");
        }
    }

    private boolean isVow(char c){
        return c == 'A' ||c == 'E' ||c == 'I' ||c == 'O' ||c == 'U';
    }
    
    private int[] solve(int N, char[][] board) {
        //it seems only to check if a row is block and count
        int minX = Integer.MAX_VALUE;
        int combo = 0;
        
        int[] oRow = new int[N];
        int[] oCol = new int[N];
        int[] xRow = new int[N];
        int[] xCol = new int[N];
        int[] dRow = new int[N];
        int[] dCol = new int[N];
        
        //check row
        for (int i = 0; i < N; i++) {
            xRow[i] = 0;
            oRow[i] = 0;
            for (int j = 0; j < N; j++) {
                if(board[i][j] == 'O') oRow[i] +=1;
                else if(board[i][j] == 'X') xRow[i] +=1;
                else if(board[i][j]== '.') dRow[i] = j;
            } 
        }

        //check column
        for (int i = 0; i < N; i++) {
            xCol[i] = 0;
            oCol[i] = 0;
            for (int j = 0; j < N; j++) {
                if(board[j][i] == 'O') oCol[i] +=1;
                else if(board[j][i] == 'X') xCol[i] +=1;
                else if(board[j][i]== '.') dCol[i] = j;
            }
        }
        
        HashSet<Integer> a = new HashSet<>();

        //min is 1 or else it is completed
        //now check each row and column
        for (int i = 0; i < N; i++) {
            if(oRow[i] == 0){
                int left = N - xRow[i];

                int idx= left == 1 ? i*N + dRow[i] : -1;

                if(left < minX){
                    minX = left;
                    combo = 1;
                    if(minX == 1) a.add(idx);
                }
                else if(left == minX){
                    if(minX == 1 && a.contains(idx))  continue;
                    combo+=1;
                }
            }

            if(oCol[i] == 0){
                int left = N - xCol[i];

                int idx= left == 1 ? dCol[i]*N+i : -1;

                if(left < minX){
                    minX = left;
                    combo = 1;
                    if(minX == 1) a.add(idx);
                }
                else if(left == minX){
                    if(minX == 1 && a.contains(idx)) continue;
                    combo+=1;
                }
            }
        }
        
        //now check each column
        return new int[]{minX, combo};
    }


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = parseInt(sc.nextLine());

            String temp;
            char[][] board = new char[N][N];
            for (int j = 0; j < N; j++) {
                temp = sc.nextLine();
                for (int k = 0; k < N; k++) {
                    board[j][k] = temp.charAt(k);
                }
            }
            int[] r = solve(N, board);
            if(r[0] == Integer.MAX_VALUE) out.println("Impossible");
            else out.format("%d %d\n", r[0], r[1]);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new B().run();
    }
}
