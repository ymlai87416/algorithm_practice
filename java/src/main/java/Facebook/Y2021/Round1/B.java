package Facebook.Y2021.Round1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
            //IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\A2-test.in";
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round1\\traffic_control_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round1\\traffic_control_output.txt";
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

    int[][] map = new int[51][51];

    private boolean solve(int N, int M, int A, int B) {
        //the step to move from TL to BR is (N-1)+(M-1)
        //the top rows is always 1, cannot heard
        if(A < N+M-1) return false;
        if(B < N+M-1) return false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = 1;
            }
        }

        map[N-1][0] = B-N-(M-2);
        map[N-1][M-1] = A-N-(M-2);

        return true;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            int M = sc.nextInt();
            int A = sc.nextInt();
            int B = sc.nextInt();
            boolean r = solve(N, M, A, B);
            if(r){
                out.println("Possible");
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < M; k++) {
                        if(k > 0) out.print(" ");
                        out.print(map[j][k]);
                    }
                    out.println();
                }
            }
            else
                out.println("Impossible");
        }
        sc.close();
        out.close();
    }

    private void generateTestCase() throws IOException {
        FileWriter myWriter = new FileWriter("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round1\\B_gen.txt");
        myWriter.write("200\n");
        for (int i = 5; i <=50 ; i+=5) {
            for (int j = 5; j <=50 ; j+=5) {
                //2 test case
                int a = i+j-2;
                myWriter.write("" + i + " " + j + " " + a + " " + (a+2) + "\n");

                a = 1000;
                myWriter.write("" + i + " " + j + " " + a + " " + a  + "\n");
            }
        }

        myWriter.close();
    }

    public static void main(String args[]) throws Exception {
        //new B().generateTestCase();
        new B().run();
    }

}
