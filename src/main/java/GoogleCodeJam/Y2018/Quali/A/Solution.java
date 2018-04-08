package GoogleCodeJam.Y2018.Quali.A;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by ymlai on 8/4/2018.
 */
public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
                    */

            //IN = "A-test.in";
            IN = null;
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


    private void solve_small(int r, String input) {
        long ans = 0;

    }

    private void solve(int r, String input){
        int totalDamage = 0;
        PriorityQueue<Integer> damage = new PriorityQueue<Integer>(Collections.reverseOrder());

        int cur = 1;
        for(int i=0; i<input.length(); ++i){
            if(input.charAt(i) == 'C')
                cur = cur * 2;
            else {
                damage.offer(cur);
                totalDamage += cur;
            }
        }

        if(damage.size() > r)
            out.println("IMPOSSIBLE");
        else{
            int step = 0;
            while(totalDamage > r){
                Integer d = damage.poll();
                d = d/2;
                damage.offer(d);
                totalDamage -= d;
                step++;
            }

            out.println(step);
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int r = sc.nextInt();
            String c = sc.next();

            solve(r, c);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}

