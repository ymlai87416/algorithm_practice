package Facebook.Y2016.Round1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//start at 20:16, AC at 20:53
public class Problem2MT {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "C:\\Users\\Tom\\Documents\\algorithm_practice\\laundro_matt (1)";
            IN = FILENAME + ".txt";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static class WashingTime implements Comparable<WashingTime>{
        long time;
        int inc;

        public WashingTime(int t, int inc){
            time = t;
            this.inc = inc;
        }

        @Override
        public int compareTo(WashingTime o) {
            long r = time - o.time;
            if(r == 0) return inc - o.inc;
            else if(r > 0) return 1;
            else return -1;
        }
    }

    private long solve(int l, int n, int m, int d, int[] wm) {
        long[] wts = new long[1000001];

        PriorityQueue<WashingTime> pq = new PriorityQueue<>();

        for(int i=0; i<n; ++i){
            WashingTime wt =  new WashingTime(wm[i], wm[i]);
            pq.add(wt);
        }

        for(int i=0; i<l; ++i){
            WashingTime wt = pq.poll();
            wts[i] = wt.time;
            wt.time += wt.inc;
            pq.add(wt);
        }

        pq.clear();

        if(m >= l){ //if more dryer than load, just dump it as soon as washing finish.
            wts[l-1] += d;
        }
        else{
            for(int i=0; i<m; ++i){
                WashingTime wt =  new WashingTime(0,d);
                pq.add(wt);
            }
            for(int i=0 ; i< l; ++i){
                WashingTime wt = pq.poll();
                wt.time = Math.max(wt.time, wts[i]) ;
                wt.time += wt.inc;

                wts[i] = wt.time;
                pq.add(wt);
            }
        }

        return wts[l-1];

    }



    private void run() throws Exception {

        int t = sc.nextInt();

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        Future<Long>[] fs = new Future[t];
        for(int i=0; i<t; ++i){
            int l = sc.nextInt();
            int n = sc.nextInt();
            int m = sc.nextInt();
            int d = sc.nextInt();

            int[] wm = new int[100001 ];
            for(int j=0; j<n; ++j){
                wm[j] = sc.nextInt();
            }

            fs[i] = executorService.submit(() -> solve(l, n, m, d, wm));
        }

        for (int i = 1; i <= t; i++) {
            System.out.print("Case #" + i + ": ");
            out.print("Case #" + i + ": ");

            long r = fs[i-1].get();
            System.out.println(r);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        long start_time = System.currentTimeMillis();
        new Problem2MT().run();
        long end_time = System.currentTimeMillis();
        long execution_time = (end_time - start_time);

        System.out.println(String.format("Total runtime: %dms", execution_time));
    }
}
