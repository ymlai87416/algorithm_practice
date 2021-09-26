package Facebook.Y2020.Round2;

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
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2020\\Round2\\capastaty_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2020\\Round2\\capastaty_output.txt";
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

    int[] Sr=  new int[1000000];
    int[] Xr=  new int[1000000];
    int[] Yr=  new int[1000000];

    private long solve(int N, int K, int[] S, int[] Sc, int[] X, int[] Xc, int[] Y, int[] Yc) {
        int Si_2 = 0, Si_1 = 0, Xi_2 = 0, Xi_1 = 0, Yi_2 = 0, Yi_1 = 0;

        long result = 0;
        long maxCust = 0, minCust =0, totalCust = 0, excess=0;
        for (int i = 0; i <N; i++) {
            if(i <K){
                Sr[i] = S[i];
                Xr[i] = X[i];
                Yr[i] = Y[i];
            }
            else{
                Sr[i] = (int)((1L * Sc[0] * Si_2 + 1L * Sc[1] * Si_1 + Sc[2]) % Sc[3]);
                Xr[i] = (int)((1L * Xc[0] * Xi_2 + 1L * Xc[1] * Xi_1 + Xc[2]) % Xc[3]);
                Yr[i] = (int)((1L * Yc[0] * Yi_2 + 1L * Yc[1] * Yi_1 + Yc[2]) % Yc[3]);
            }

            maxCust += (Xr[i] + Yr[i]);
            minCust += (Xr[i]);
            totalCust += Sr[i];
            int curExcess = Sr[i] > (Xr[i] + Yr[i]) ? Sr[i] - Xr[i] - Yr[i] : 0;
            int curLack = Xr[i] > Sr[i] ? Xr[i] - Sr[i] : 0;

            if(curExcess > 0) excess+=curExcess;
            if(curLack > 0){
                excess -= curLack;
                result+=curLack; //move occurred, hard criteria
            }

            Si_2 = Si_1; Si_1 = Sr[i];
            Xi_2 = Xi_1; Xi_1 = Xr[i];
            Yi_2 = Yi_1; Yi_1 = Yr[i];
        }

        //now we just sum the min and max and see if customer must go,
        if(totalCust > maxCust) return -1;
        if(totalCust < minCust) return -1;
        else{
            /*even the total cust is within range, there must be some move due to
                1. excess > 0 => must move to other with S[i] between X[i] and X[i] + Y[i]; hard
                2. excess < 0 => customer has been fulfilled and drawn from area that S[i] between X[i] and X[i] + Y[i]
             */
            if(excess > 0) result += excess;
        }
        return result;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            int K = sc.nextInt();
            int[] S = new int[K];
            int[] Sc = new int[4];
            int[] X = new int[K];
            int[] Xc = new int[4];
            int[] Y = new int[K];
            int[] Yc = new int[4];

            for (int j = 0; j < K; j++) {
                S[j] = sc.nextInt();
            }

            for (int j = 0; j < 4; j++) {
                Sc[j] = sc.nextInt();
            }

            for (int j = 0; j < K; j++) {
                X[j] = sc.nextInt();
            }

            for (int j = 0; j < 4; j++) {
                Xc[j] = sc.nextInt();
            }

            for (int j = 0; j < K; j++) {
                Y[j] = sc.nextInt();
            }

            for (int j = 0; j < 4; j++) {
                Yc[j] = sc.nextInt();
            }

            long r = solve(N, K, S, Sc, X, Xc, Y, Yc);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new A().run();
    }

}
