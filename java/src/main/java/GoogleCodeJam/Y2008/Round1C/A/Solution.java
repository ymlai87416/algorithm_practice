package GoogleCodeJam.Y2008.Round1C.A;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by ymlai on 11/4/2017.
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
            FILENAME = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1C\\A\\A-large-practice";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false)); ;

             */

            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1C\\A\\A-test.in";
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

    private long solve(int P, int K, int L, int[] freq) {
        Integer[] idx = new Integer[L];
        for(int i=0; i<L; ++i) idx[i] = i;
        Arrays.sort(idx, new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                return freq[o2]-freq[o1];
            }
        });

        long type = 0;
        int keyPress = 1;
        int keyRound = 0;
        for(int i=0; i<L; ++i){
            int j=  idx[i];
            int fi = freq[j];
            type += keyPress * fi;

            keyRound ++ ;
            if(keyRound == K){
                keyPress +=1;
                keyRound = 0;
            }
        }

        return type;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int P = sc.nextInt();
            int K = sc.nextInt();
            int L = sc.nextInt();
            int[] freq =new int[L];
            for (int j = 0; j < L; j++) {
                freq[j] = sc.nextInt();
            }

            long r = solve(P, K, L,freq);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}