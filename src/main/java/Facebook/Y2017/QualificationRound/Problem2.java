package Facebook.Y2017.QualificationRound;


import java.io.*;
import java.util.*;

//start at
public class Problem2 {
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

    int[] w = new int[100];

    private void solve(int k, int[] w) {
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

        System.out.println(result);
        out.println(result);
    }


    private void run() throws Exception {

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            System.out.print("Case #" + i + ": ");
            out.print("Case #" + i + ": ");
            int k = sc.nextInt();
            for(int j= 0; j<k; ++j){
                w[j] = sc.nextInt();
            }

            solve(k, w);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Problem2().run();
    }
}
