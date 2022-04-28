package GoogleCodeJam.Y2022.Round1B.A;
/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/GoogleCodeJam/Y2022/Round1B/A/A-test.in";
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

    private int solve(int[] cake) {
        int left = 0;
        int right = cake.length-1;

        int coin = 0;
        int curMax = 0;

        while(left <= right) {
            int cc;
            if (cake[left] < cake[right]) {
                cc = cake[left];
                left++;

            } else {
                cc = cake[right ];
                right--;
            }

            if(cc >=  curMax) {
                coin++;
                curMax = cc;
            }
        }

        return coin;
    }


    private void run() throws Exception {

        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            int N = sc.nextInt();
            out.print("Case #" + i + ": ");
            int[] cake = new int[N];
            for(int j=0; j<N; ++j){
                cake[j] = sc.nextInt();
            }
            int a = solve(cake);
            out.println(a);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}