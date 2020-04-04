package GoogleCodeJam.Y2018.Round1A.B; /**
 * Created by Tom on 9/4/2016.
 */
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
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
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(System.in);
            out = new PrintStream(System.out);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void solve(int robot, long bit, int counter, long[] M, long[] S, long[] P) {
        boolean debug =false;

        long min_t = 0;
        long max_t = initRobotT(bit, counter, M, S, P, robot);

        if(debug) System.out.println("Init time " + max_t);

        long mid_t = 0, prev_mid_t = 0;

        while(true){
            prev_mid_t = mid_t;
            mid_t = (max_t + min_t)/2;
            if(mid_t == prev_mid_t)
            break;

            boolean success = assignRobotT(bit, counter, M, S, P, robot, mid_t);
            if(success){
                max_t = mid_t;  // max always store success case
                if(debug) System.out.println("Success clearing under " + mid_t);
            }
            else{
                min_t = mid_t; //min always store failed case
                if(debug) System.out.println("Fail to clearing under " + mid_t);
            }
        }

        out.println(max_t);
    }

    long initRobotT(long bit, int counter, long[] M, long[] S, long[] P, int robot){

        long bit_left = bit;
        long max_time = 0;

        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < counter; i++) {
            index.add(i);
        }

        Collections.sort(index, new Comparator<Integer>() {
            @Override
            public int compare(Integer idx1, Integer idx2) {
                return M[idx1] > M[idx2] ? -1: M[idx1] == M[idx2] ? 0: 1;
            }
        });

        for(int i=0; i<robot; ++i){
            long clear_time = 0;
            long shop_bit = Math.min(M[index.get(i)], bit_left);
            clear_time = shop_bit * S[index.get(i)] + P[index.get(i)];

            if(clear_time > max_time)
                max_time = clear_time;
            bit_left -= shop_bit;
        }

        return max_time;
    }

    private boolean assignRobotT(long bit, int counter, long[] M, long[] S, long[] P, int robot, long maxTime){
       //run out of robot / counter
        int[] counter_best_clear = new int[counter];

        long[] best_clear = new long[counter];
        for(int i=0; i<counter; ++i){
            best_clear[i] = Math.max(0, Math.min(M[i], (maxTime - P[i]) / S[i]));
        }

        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < counter; i++) {
            index.add(i);
        }

        Collections.sort(index, new Comparator<Integer>() {
            @Override
            public int compare(Integer idx1, Integer idx2) {
                return best_clear[idx1] > best_clear[idx2] ? -1: best_clear[idx1] == best_clear[idx2] ? 0: 1;
            }
        });

        //debug
        /*
        for(int i=0; i<counter; ++i)
            System.out.print(index.get(i) + " ");
        System.out.println();

        for(int i=0; i<counter; ++i)
            System.out.print(best_clear[index.get(i)] + " ");
        System.out.println();
        */
        //debug

        long bit_left=bit;
        for(int i=0; i<robot; ++i){
            bit_left -= best_clear[index.get(i)];
        }

        if(bit_left <= 0)
            return true;
        else
            return false;

        //find the max [robot] best counter and do the clearing there

        //if still bit left return false, if success return true
    }


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int robot = sc.nextInt();
            int bit = sc.nextInt();
            int counter = sc.nextInt();
            long[] M = new long[counter];
            long[] S = new long[counter];
            long[] P = new long[counter];
            for(int p=0; p<counter; ++p){
                M[p] = sc.nextLong();
                S[p] = sc.nextLong();
                P[p] = sc.nextLong();
            }

            solve(robot, bit, counter, M, S, P);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}