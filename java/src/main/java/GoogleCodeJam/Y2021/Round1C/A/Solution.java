package GoogleCodeJam.Y2021.Round1C.A;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2021\\Round1C\\A\\A-test.in";
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

    boolean debugflag = true;
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

    private double solve(int N, int K, int[] P) {

        Arrays.sort(P);

        int[] r2 = new int[N+1];
        int[] r1 = new int[N+1];

        r2[0] = P[0] - 1;
        r1[0] = P[0] - 1;

        for (int i = 1; i < N; i++) {
            //use 2 to take the whole thing
            //use 1 to take half the range
            r2[i] = P[i] - P[i-1] - 1;
            r1[i] = (P[i] + P[i-1]) / 2 - P[i-1]; //
        }
        r2[N] = K - P[P.length-1];
        r1[N] = r2[N];
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N+1; i++) {
            sb.append(r2[i] + " ");
        }
        debug(sb.toString());

        sb= new StringBuilder();
        for (int i = 0; i < N+1; i++) {
            sb.append(r1[i] + " ");
        }
        debug("r1", sb.toString());



        Arrays.sort(r2);
        Arrays.sort(r1);

        int r2a = r2[N];
        int r1a = r1[N] + r1[N-1];
        if(r2a > r1a)
            return 1.0* r2a / K;
        else
            return 1.0 * r1a / K;

    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            int K = sc.nextInt();
            int[] P = new int[N];
            for (int j = 0; j < N; j++) {
                P[j] = sc.nextInt();
            }
            double r = solve(N, K, P);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
