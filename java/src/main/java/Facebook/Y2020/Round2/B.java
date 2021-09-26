package Facebook.Y2020.Round2;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class B {

    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2020\\Round2\\elimination_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2020\\Round2\\elimination_output.txt";
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

    int T;
    double  p;
    double[][] dp = new double[8001][8001];

    private double[] solve(int T, double p) {
        double[] result = new double[T];
        this.T = T;
        this.p = p;

        for (int i = 0; i <= T; i++) {
            for (int j = 0; j <= T; j++) {
                dp[i][j] = Double.NEGATIVE_INFINITY;
            }
        }
        dp[1][0] = dp[0][1] = 1;
        
        for (int i = 0; i < T; i++) {
            result[i] = dpHelper(T-i-1, i);
        }

        return result;
    }

    private double dpHelper(int better, int worst){
        //now what we have?
        //me vs better
        //me vs worst
        //better vs better
        //better vs worst
        //worst vs worst

        //base case, either only worst=1, better=1
        if(dp[better][worst] != Double.NEGATIVE_INFINITY) return dp[better][worst];

        double total = better+worst+1;
        double meBetterP = better > 0 ? ((1/total * better/(total-1)) + (better/total * 1/(total-1))) : 0;
        double meWorstP = worst > 0 ? ((1/total * worst/(total-1)) + (worst/total * 1/(total-1))) : 0;
        double better2P =  better >=2 ? better/total * (better-1)/(total-1): 0;
        double worst2P = worst >= 2 ? worst/total * (worst-1)/(total-1) : 0;
        double betterWorstP = better > 0 && worst > 0 ? ((better/total * worst/(total-1)) + (worst/total * better/(total-1))): 0;

        double mebetter = meBetterP == 0 ? 0 : meBetterP * (p +  (1-p) * (1 + dpHelper(better-1, worst)));
        double meworst =  meWorstP == 0 ? 0 : meWorstP * (p * (1 + dpHelper(better, worst-1)) + (1-p) * 1);
        double better2 = better2P == 0 ? 0 : better2P * (1+dpHelper(better-1, worst));
        double worst2 = worst2P == 0 ? 0 : worst2P * (1+dpHelper(better, worst-1));
        double betterworst = betterWorstP == 0 ? 0 : betterWorstP * (p * (1+dpHelper(better, worst-1)) + (1-p) *  (1+dpHelper(better-1, worst)));

        dp[better][worst] = mebetter + meworst + better2 + worst2 + betterworst;
        return dp[better][worst];
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.println("Case #" + i + ": ");
            int T = sc.nextInt();
            double d = sc.nextDouble();
            double[] r = solve(T, d);
            for (int j = 0; j < r.length; j++) {
               out.format("%.8f\n",r[j]);
            }
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new B().run();
    }

}
