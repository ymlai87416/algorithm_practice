package Facebook.Y2020.Quali;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;


//because of the question, it can solved in linear time. from A -> B, no in-between have in-out restriction
//and A must have out and B must have in, can be done in linear time (n^3) n^2 for checking each pair and in between log(n)
public class A {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2020\\Quali\\travel_restrictions_input.txt";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            out = new PrintStream(System.out);
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

    final int block=9999;
    int[][] ans = new int[51][51];

    private void solve(int N, String in, String out) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans[i][j] = i == j ? 0: Math.abs(i-j)==1 ? 1: block;
            }
        }
        for (int i = 0; i < N; i++) {
            char c = in.charAt(i);
            if(c == 'N')
                for (int j = 0; j < N; j++) {
                    ans[j][i] = i==j ? 0: block;
                }
        }

        for (int i = 0; i < N; i++) {
            char c = out.charAt(i);
            if(c == 'N')
                for (int j = 0; j < N; j++) {
                    ans[i][j] = i==j ? 0: block;

                }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    ans[i][j] = Math.min(ans[i][j], distAdd(ans[i][k],ans[k][j]));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(ans[i][j] == block) System.out.print("N");
                else System.out.print("Y");
            }
            System.out.println();
        }
        
        //return ans;
    }

    private int distAdd(int d1, int d2){
        if(d1 == block || d2 ==block) return block;
        else return d1+d2;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();

        for (int i = 1; i <= t; i++) {
            out.println("Case #" + i + ": ");
            int N = sc.nextInt();
            sc.nextLine();
            String in = sc.nextLine();
            String out = sc.nextLine();
            solve(N, in, out);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new A().run();
    }

}