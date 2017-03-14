package Facebook.Y2016.Round1;

import sun.security.pkcs.ParsingException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Tom on 10/1/2017.
 * 22:25, think of survival basis, but not working. think again to use bitmask. 01:50 AC
 */
public class Problem4 {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "C:\\Users\\Tom\\Documents\\algorithm_practice\\boomerang_tournament (1)";
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

    int[] pow2 = new int[] {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768};

    boolean[][] r = new boolean[16][65536];
    private void solve(int k, int[][] w) {

        boolean[] kill = new boolean[k];

        for(int i=0; i<k; ++i){
            kill[i] = false;
            for(int j=0; j<k; ++j){
                if(i == j) continue;
                if(w[i][j] == 0)
                    kill[i] = true;
            }
        }

        for(int i=0; i<16; ++i){
            for(int j=0; j<65536; ++j)
                r[i][j] = false;
        }

        for(int i=0; i<k; ++i)
            r[i][pow2[i]] = true;

        for(int i=2, j=0; i<=k; i*=2, ++j){
            for(int p=0; p<k; ++p){
                for(int q=0; q<k; ++q){
                    if(w[p][q] == 1){
                        for(int s : seq[j]){
                            if(!r[p][s]) continue;
                            if(j == 3){
                                if(r[q][~s & 65535])
                                    r[p][65535] = true;
                            }
                            else{
                                for(int t: seq[j]){
                                    if(!r[q][t]) continue;
                                    if((~s & t) == t)
                                        r[p][s+t] = true;
                                }
                            }

                        }
                    }
                }
            }
        }

        for(int i=0; i<k; ++i){
            int maxbcnt = 0;
            int bcnt;
            for(int j=0; j<65536; ++j)
                if(r[i][j]){
                    bcnt = Integer.bitCount(j);
                    if(bcnt > maxbcnt)
                        maxbcnt = bcnt;
                }

            //System.out.println("For " + i + " found max bit " + maxbcnt );

            int frank = -1;
            for(int p=k, q=0; p!=0; p/=2, ++q)
                if(p == maxbcnt)
                    frank = rank[q];

            String resultS = String.format("%d %d", frank, kill[i] ? k/2+1 : 1);
            out.println(resultS);
            System.out.println(resultS);
        }

    }

    ArrayList<Integer>[] seq = new ArrayList[4];
    int[] rank = new int[] {1, 2, 3, 5, 9};

    private void precompute(){

        for(int i=0; i<4; ++i)
            seq[i] = new ArrayList<>();

        for(int i=0; i<16; ++i)
            seq[0].add(1 << i);

        for(int i=0; i<15; ++i)
            for(int j=i+1; j<16; ++j)
                seq[1].add((1 << i) + (1 << j));

        for(int i=0; i<13; ++i)
            for(int j=i+1; j<14; ++j)
                for(int k=j+1; k<15; ++k)
                    for(int l=k+1; l<16; ++l)
                        seq[2].add((1 << i) + (1 << j) + (1 << k) + (1 << l));

        /*
        for(int i=0; i<9; ++i)
            for(int j=i+1; j<10; ++j)
                for(int k=j+1; k<11; ++k)
                    for(int l=k+1; l<12; ++l)
                        for(int m=l+1; m<13; ++m)
                            for(int n=m+1; n<14; ++n)
                                for(int o=n+1; o<15; ++o)
                                    for(int p=o+1; p<16; ++p)
                                        seq[3].add((1 << i) + (1 << j) + (1 << k) + (1 << l) + (1 << m) + (1 << n) + (1 << o) + (1 << p));
                                        */

    }

    int[][] w = new int[16][16];

    private void run() throws Exception {
        precompute();

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            System.out.print("Case #" + i + ": \n");
            out.print("Case #" + i + ": \n");

            int k = sc.nextInt();

            for(int p=0; p<k; ++p)
                for(int q=0; q<k; ++q)
                    w[p][q] = sc.nextInt();

            solve(k, w);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        long start_time = System.currentTimeMillis();
        new Problem4().run();
        long end_time = System.currentTimeMillis();
        long execution_time = (end_time - start_time);

        System.out.println(String.format("Total runtime: %dms", execution_time));
    }
}
