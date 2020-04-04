package GoogleCodeJam.Y2010.QualificationRound;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 9/4/2016.
 */

public class StoreCredit {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    Pair[] itemValue = new Pair[2001];
    int[] value = new int[2001];

    class Pair implements Comparable<Pair>{
        int value;
        int order;
        public Pair(int value, int order){
            this.value = value;
            this.order = order;
        }

        @Override
        public int compareTo(Pair o) {
            return value - o.value;
            /*if(value == o.value) return order - o.order;
            else return value - o.value;*/
        }
    }


    static{
        try{
            FILENAME = "A-large-practice";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, true)); ;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void solve() {
        int credit = sc.nextInt();
        int totItem = sc.nextInt();


        for(int i=0; i<totItem; ++i){
            int v = sc.nextInt();
            itemValue[i] = new Pair(v, i);
            value[i] = v;
        }

        Arrays.sort(itemValue, 0, totItem);

        int i, j=0;
        for(i=0; i<totItem; ++i){
            int comple = credit - value[i];
            int pos = Arrays.binarySearch(itemValue, 0, totItem, new Pair(comple, 0));
            if(pos < 0) continue;
            if(itemValue[pos].order == i) {
                if (pos + 1 < totItem && itemValue[pos + 1].value == itemValue[pos].value)
                    pos = pos + 1;
                else continue;
            }
            j = itemValue[pos].order;
            break;
        }
        out.format("%d %d\n", i+1, j+1);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            solve();
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new StoreCredit().run();
    }

}