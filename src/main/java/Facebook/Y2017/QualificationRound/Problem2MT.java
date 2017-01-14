package Facebook.Y2017.QualificationRound;


import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//start at
public class Problem2MT {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "C:\\Users\\Tom\\Documents\\algorithm_practice\\lazy_loading";
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

    private int solve(int k, int[] w) {
        Arrays.sort(w, 0, k);
        ArrayDeque<Integer> qu = new ArrayDeque<Integer>();
        for(int i=k-1; i>=0; --i)
            qu.addLast(w[i]);

        int result = 0;
        while(!qu.isEmpty()){
            int d = qu.pollFirst();

            int itemNeed = 50/d;
            if(50%d != 0) itemNeed +=1;

            boolean canPack = true;
            for(int j=0; j<itemNeed-1; ++j) {
                Integer a = qu.pollLast();
                if(a == null)
                    canPack = false;
            }

            if(canPack) result++;
        }

        return result;
    }


    private void run() throws Exception {

        int t = sc.nextInt();

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        Future[] fs = new Future[t];
        for(int i=0; i<t; ++i){
            int k = sc.nextInt();
            int[] w = new int[k];
            for(int j= 0; j<k; ++j){
                w[j] = sc.nextInt();
            }

            fs[i] = executorService.submit(() -> solve(k, w));
        }


        for (int i = 1; i <= t; i++) {
            System.out.print("Case #" + i + ": ");
            out.print("Case #" + i + ": ");

            System.out.println((int) fs[i-1].get());
            out.println((int) fs[i-1].get());
        }
        sc.close();
        out.close();

        executorService.shutdown();
    }

    public static void main(String args[]) throws Exception {
        long start_time = System.currentTimeMillis();
        new Problem2MT().run();
        long end_time = System.currentTimeMillis();
        long execution_time = (end_time - start_time);

        System.out.println(String.format("Total runtime: %dms", execution_time));
    }
}
