package GoogleCodeJam.Y2017.Round1C.A;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2017\\Round1C\\A\\A-test.in";
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

    private double solve(int N, int K , int[] R, int[] H) {
        //find the largest one
        //find the other K-1 with the thickness.
        double[] surface = new double[N];
        double[] circum  = new double[N];

        //can we not choose the biggest
        for (int i = 0; i <N; i++) {
            surface[i] = 1.0*R[i] * R[i] * Math.PI;
            circum[i] = 2.0*Math.PI*R[i]*H[i];
        }

        Integer[] sortR = new Integer[N];
        Integer[] sortC = new Integer[N];
        for (int i = 0; i < N; i++) {
            sortR[i] = i; sortC[i] = i;
        }

        Arrays.sort(sortR, new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                Integer r1 =R[o1];
                Integer r2 =R[o2];
                if(r1.compareTo(r2) == 0) return o1-o2;
                else return r1.compareTo(r2);
            }
        });

        for (int i = 0; i < N; i++) {
            sortC[i] = sortR[i];
        }

        //we need 1000;
        double ans = 0;

        for (int i = K-1; i < N; i++) {
            //the base is K
            double t = surface[sortR[i]]  + circum[sortR[i]];

            if(K > 1) {
                //sort the rest with circumference
                Arrays.sort(sortC, 0, i, new Comparator<Integer>() {

                    @Override
                    public int compare(Integer o1, Integer o2) {
                        Double r1 = circum[o1];
                        Double r2 = circum[o2];
                        if (r1.compareTo(r2) == 0) return o1 - o2;
                        else return r1.compareTo(r2);
                    }
                });

                for (int j = 0; j < K-1; j++) {
                    t += circum[sortC[i - 1 - j]];
                }
            }

            if(ans < t){
                ans = t;
            }
        }


        return ans;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int N = sc.nextInt();
            int K =sc.nextInt();
            int[] R = new int[N];
            int[] H = new int[N];
            for (int j = 0; j < N; j++) {
                R[j] =sc.nextInt();
                H[j] = sc.nextInt();
            }
            out.print("Case #" + i + ": ");
            System.out.println(solve(N, K, R, H));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}