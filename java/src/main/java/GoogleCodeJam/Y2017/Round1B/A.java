package GoogleCodeJam.Y2017.Round1B;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ymlai on 22/4/2017.
 */
public class A {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "A-large (2)";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false)); ;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    static class P implements Comparable<P>{
        int distance;
        int speed;

        public P(int d, int s){
            distance = d;
            speed = s;
        }

        @Override
        public int compareTo(P o) {
            if(distance < o.distance)
                return 1;
            else if(distance > o.distance)
                return -1;
            else{
                return speed-o.speed;
            }
        }
    }

    private void solve(int D, int N, List<P> horses) {
        double ans = 0;

        double timeTaken = 0;
        for(int i=0; i<N; ++i){
            double t = (D-horses.get(i).distance) * 1.0 / horses.get(i).speed;
            if(timeTaken < t)
                timeTaken = t;
        }

        ans = D/timeTaken;
        out.format("%.6f\n", ans);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            System.out.println("Case #" + i + ": ");

            int D, N;
            D = sc.nextInt();
            N = sc.nextInt();

            ArrayList<P> ps = new ArrayList<>();
            for(int j=0; j<N; ++j){
                ps.add(new P(sc.nextInt(), sc.nextInt()));
            }

            solve(D, N, ps);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new A().run();
    }

}
