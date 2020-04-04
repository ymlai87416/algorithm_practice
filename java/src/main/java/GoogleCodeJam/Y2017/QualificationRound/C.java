package GoogleCodeJam.Y2017.QualificationRound;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by ymlai on 8/4/2017.
 */
public class C {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "C-large";
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

    private void solve(long N, long K) {
        long ansMin, ansMax;
        ansMin = -1;
        ansMax = -1;

        if(K == 1){
            if(N % 2 == 0) {
                ansMin = N/2-1;
                ansMax = N/2;
            }
            else{
                ansMin = N/2;
                ansMax = N/2;
            }
        }
        else {
            Map<Region, Long> regions = new TreeMap<Region, Long>();
            if(N % 2 == 0)
                regions.put(new Region(N/2, N/2-1), 1L);
            else
                regions.put(new Region(N/2, N/2), 1L);

            long tmp = 2;
            while(tmp <= K){
                Map<Region, Long> next = new TreeMap<Region, Long>();
                for(Region r : regions.keySet()){
                    if(r.mn == r.mx){
                        Region x;
                        if(r.mx%2==1)
                            x = new Region(r.mx/2, r.mx/2);
                        else
                            x = new Region(r.mx/2, r.mx/2-1);

                        if(next.containsKey(x))
                            next.put(x, next.get(x) + regions.get(r)*2);
                        else
                            next.put(x, regions.get(r)*2);
                    }
                    else{
                        Region small, big;

                        if(r.mn%2==1)
                            small= new Region(r.mn/2, r.mn/2);
                        else
                            small = new Region(r.mn/2, r.mn/2-1);

                        if(r.mx%2==1)
                            big= new Region(r.mx/2, r.mx/2);
                        else
                            big = new Region(r.mx/2, r.mx/2-1);

                        if(next.containsKey(small))
                            next.put(small, next.get(small) + regions.get(r));
                        else
                            next.put(small, regions.get(r));

                        if(next.containsKey(big))
                            next.put(big, next.get(big) + regions.get(r));
                        else
                            next.put(big, regions.get(r));
                    }
                }
                tmp *=2;
                regions = next;
            }

            long Kless = K-tmp/2;

            for(Region r : regions.keySet()){
                long cnt = regions.get(r);

                if(Kless < cnt){
                    ansMax = r.mx;
                    ansMin = r.mn;
                    break;
                }
                else
                    Kless -= cnt;
            }
        }

        out.println(ansMax + " " + ansMin);
    }


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            long N = sc.nextLong();
            long K = sc.nextLong();
            solve(N, K);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new C().run();
    }

}

class Region implements Comparable<Region>{
    long mx;
    long mn;
    public Region(long max, long min){
        this.mx = max;
        this.mn = min;
    }

    @Override
    public int compareTo(Region o) {
        if(mn > o.mn)
            return -1;
        else if(mn < o.mn)
            return 1;
        else{
            if(mx > o.mx)
                return -1;
            else if(mx < o.mx)
                return 1;
            else
                return 0;
        }
    }
}