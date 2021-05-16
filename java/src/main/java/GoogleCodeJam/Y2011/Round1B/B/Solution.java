package GoogleCodeJam.Y2011.Round1B.B;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2011\\Round1B\\B\\B-test.in";
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

    int C;
    int D;
    int[] P;
    int[] V;
    private double solve(int C, int D, int[] P, int[] V) {
        this.C = C;
        this.D = D;
        this.P = P;
        this.V = V;

        //do binary search
        //max 10^6 * 10^6 = move all the point to one size
        double min = 0;
        double max = 1e12;
        double mid;

        int loop = 0;
        while(Math.abs(min-max) > 10e-7 && loop < 250){
            mid = (min+max)/2;

            if(verify(mid)){
                max = mid;
            }
            else
                min = mid;
            ++loop;
        }

        debug("sh" + loop);

        return max;
    }

    private boolean verify(double t){
        //debug("verify " + t);

        int leftIdx = 0;
        int rightIdx = P.length-1;

        boolean ok=true;
        Double leftBound = null;
        Double rightBound =null;

        while(true){
            if(leftBound != null && rightBound != null && rightBound < leftBound)
                return false;

            //if leftIdx == rightIdx
            if(leftIdx == rightIdx){
                //try to move
                for (int i = 0; i < V[leftIdx]; i++) {
                    if(leftBound == null)
                        leftBound = P[leftIdx] - t;
                    else {
                        double target = leftBound + D;
                        double delta =target - P[leftIdx];
                        if(delta == 0) leftBound = (double) P[leftIdx];
                        else if(delta < 0) leftBound = Math.max(target, P[leftIdx] - t);
                        else if(delta > 0){
                            if(P[leftIdx] + t < target)
                                return false;
                            else
                                leftBound = target; //I just need to get there
                        }
                    }
                }

                if(rightBound != null)
                    return (leftBound+D) <= rightBound;
                else
                    return true;
            }

            //if leftIdx + 1 = rightIdx

            //all left people will try to distribute
            for (int i = 0; i < V[leftIdx]; i++) {
                if(leftBound == null)
                    leftBound = P[leftIdx] - t;
                else {
                    double target = leftBound + D;
                    double delta =target - P[leftIdx];
                    if(delta == 0) leftBound = (double) P[leftIdx];
                    else if(delta < 0) leftBound = Math.max(target, P[leftIdx] - t);
                    else if(delta > 0){
                        if(P[leftIdx] + t < target)
                            return false;
                        else
                            leftBound = target; //I just need to get there
                    }
                }
            }

            //all right people will try to distribute
            for (int i = 0; i < V[rightIdx]; i++) {
                if(rightBound == null)
                    rightBound = P[rightIdx] + t;
                else {
                    double target = rightBound - D;
                    double delta =target - P[rightIdx];
                    if(delta == 0) rightBound = (double) P[rightIdx];
                    else if(delta > 0) rightBound = Math.min(target, P[rightIdx] + t);
                    else if(delta < 0){
                        if(P[rightIdx] - t > target)
                            return false;
                        else
                            rightBound = target; //I just need to get there
                    }
                }
            }


            if(leftIdx +1 == rightIdx){
                //check left bound right bound
                return (leftBound+D) <= rightBound;
            }
            ++leftIdx;
            --rightIdx;
        }

    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int C = sc.nextInt();
            int D = sc.nextInt();
            int[] P = new int[C];
            int[] V = new int[C];
            for (int j = 0; j < C; j++) {
                P[j] =sc.nextInt();
                V[j] = sc.nextInt();
            }
            double r = solve(C, D, P, V);
            out.format("%.6f\n", r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }


}
