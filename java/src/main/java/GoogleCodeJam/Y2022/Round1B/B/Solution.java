package GoogleCodeJam.Y2022.Round1B.B;
/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
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
            IN = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/GoogleCodeJam/Y2022/Round1B/B/B-test.in";
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

    int[][] pump = new int[1001][101];
    int N, P;
    HashMap<Long, Long> memo;

    private long solve() {
        memo = new HashMap<>();

        for (int i = 0; i < N; i++) {
            Arrays.sort(pump[i], 0, P);
        }
        long r = helper(0, 0);

        return r;
    }

    private long key(int cur, int idx){
        return idx * 10_000_000_000L + cur;
    }

    private long helper(int cur, int idx){
        //I will sort it, so either we pump from high to low or low to high
        if(idx == N) return 0;
        long k = key(cur, idx);
        if(memo.containsKey(k))
            return memo.get(k);

        int H = pump[idx][P-1];
        int L = pump[idx][0];
        int HL = Math.abs(H-cur) + (H-L);
        int LH = Math.abs(cur-L) + (H-L);

        long a =  HL+helper(L, idx+1);
        long b = LH+ helper(H, idx+1);
        long r = Math.min(a, b);

        memo.put(k, r);
        return r;
    }


    private void run() throws Exception {

        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            N = sc.nextInt();
            P = sc.nextInt();
            out.print("Case #" + i + ": ");

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < P; k++) {
                    pump[j][k] = sc.nextInt();
                }
            }
            long r = solve();
            System.out.println(r);
        }
        sc.close();
        out.close();
    }


    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}