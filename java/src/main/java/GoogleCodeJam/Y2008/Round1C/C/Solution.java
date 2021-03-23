package GoogleCodeJam.Y2008.Round1C.C;

/**
 * Created by Tom on 9/4/2016.
 * https://www.geeksforgeeks.org/count-number-of-increasing-sub-sequences-onlogn/
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
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1C\\C\\C-test.in";
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

    int[] smallDp = new int[500001];
    int[] seq;
    private long solveSmall(int n, int m, long X, long Y, long Z, int[] A) {

        seq = new int[n];
        for (int i = 0; i < 500001; i++) {
            smallDp[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            seq[i] = A[i% m];
            long t = X * A[i%m] + Y * (i+1);
            A[i%m] = (int)(Math.floorMod(t,Z));
        }

        /*
        System.out.print("D: ");
        for (int i = 0; i < seq.length; i++) {
            System.out.print(seq[i] + " ");
        }
        System.out.println();

         */

        long ans = 0;

        for (int i = 0; i < seq.length; i++) {
            ans = (ans + smallHelper(i)) % 1000000007;
        }
        return ans;
    }

    private long smallHelper(int start){
        if(start == seq.length-1) return 1; //last element
        if(smallDp[start] != -1) return smallDp[start];
        //find all the increasing, then add them all together
        //this loop must be optimize s.t. finding k is in log(n)
        long ans = 1;
        for (int i = start+1; i < seq.length; i++) {
            if(seq[start] < seq[i]){
                ans = (ans+ smallHelper(i)) %  1000000007;
            }
        }
        smallDp[start] = (int) ans;
        return ans;
    }



    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            long X = sc.nextInt();
            long Y = sc.nextInt();
            long Z = sc.nextInt();
            int[] A = new int[m];
            for (int j = 0; j < m; j++) {
                A[j] = sc.nextInt();
            }
            long a = solveSmall(n, m, X, Y, Z, A);
            out.print("Case #" + i + ": ");
            out.println(a);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}