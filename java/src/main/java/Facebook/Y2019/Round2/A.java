package Facebook.Y2019.Round2;


import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class A {
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2019\\Round2\\on_the_run_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2019\\Round2\\on_the_run_output.txt";
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

    private String solve(int N, int M, int K, int A, int B, int[] R, int[] C) {
        int ans = 0;

        if(K == 1) return "N";
        else{
            //check x
            //check both agent location
            int xc = (A+B)%2;
            int a1 = (R[0]+C[0])%2;
            int a2 = (R[1]+C[1])%2;

            if((a1 == a2) && (xc == a1)) return "Y";
            else return "N";
        }

    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();
            int A = sc.nextInt();
            int B = sc.nextInt();
            int[] R = new int[2];
            int[] C = new int[2];
            for (int j = 0; j < K; j++) {
                R[j] =sc.nextInt();
                C[j] = sc.nextInt();
            }
            String r = solve(N, M, K, A, B, R, C);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new A().run();
    }

}
