package GoogleCodeJam.Y2012.Round1B.A;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2012\\Round1B\\A\\A-test.in";
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

    private double[] solve(int N, int[] score, int total) {

        double[] r = new double[N];
        for (int i = 0; i < N; i++) {
            //the worst case is what? I got X point and then all other get more point than me....

            double min, max, mid = 0.5;
            min = 0;
            max = 1;
            while(Math.abs(min - max) > 1e-10){
                mid = (min+max)/2;
                double others = 1-mid;
                double myScore = score[i] + mid * total;
                boolean canFulfill = true;
                for (int j = 0; j < N; j++) {
                    if(j ==i) continue;
                    if(score[j] < myScore){
                        double lack = (myScore -score[j]) / total;
                        if(others > lack){
                            others -= lack;
                        }
                        else{
                            //max can be upgrade
                            canFulfill = false;
                            debug(i + " cannot fulfill at: " + mid);
                        }
                    }
                }

                if(canFulfill)
                    min = mid;
                else
                    max = mid;
            }

            r[i] = mid;
        }

        return r;
    }


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int N= sc.nextInt();
            int[] score= new int[N];

            int total  =0;
            for (int j = 0; j < N; j++) {
                score[j] = sc.nextInt();
                total += score[j];
            }

            out.print("Case #" + i + ":");
            double[] r = solve(N, score, total);
            for (int j = 0; j < N; j++) {
                out.format(" %.6f",r[j]*100);
            }
            out.println();
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}