package GoogleCodeJam.Y2014.Round1A;

import GoogleCodeJam.Y2016.QualificationRound.CountingSheep;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 14/3/2017.
 *
 * Reminder: although it is 2^40, it is too slow,
 * the permuatation is also slow
 * the method should be finding a flip.
 *
 * 0:37 to 1:36
 */
public class A {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "A-large-practice";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }

    int ans;

    private boolean equals(long[] a, long[] b){
        Arrays.sort(a);

        for(int i=0; i<a.length; ++i)
            if(a[i] != b[i]) return false;
        return true;
    }

    int N, L;
    long[] src;
    long[] dst;

    private void solve() {
        long[][] flip =new long[N][N];

        for(int i=0; i<N; ++i){
            for(int j=0; j<N; ++j){
                flip[i][j] = src[i] ^ dst[j];
            }
        }


        for(int k=0; k<N; ++k){
            boolean visited[] = new boolean[N];
            for(int i=0; i<N; ++i)
                    visited[i] = false;
            visited[k] = true;

            long search = flip[0][k];
            boolean searchFailed = false;

            for(int i=1; i<N; ++i){
                boolean found=false;
                for(int j=0; j<N; ++j){
                    if(visited[j]) continue;
                    if(flip[i][j] == search){
                        found = true;
                        visited[j] = true;
                    }
                }
                if(!found){
                    searchFailed = true;
                    break;
                }
            }

            if(!searchFailed)
                ans = Math.min(ans, Long.bitCount(search));
        }
    }

    private void flip(long[] d, int L){
        for(int i=0; i<d.length; ++i){
            d[i] = d[i] ^ (1L << L);
        }
    }



    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            N = sc.nextInt();
            L = sc.nextInt();

            src = new long[N];
            dst = new long[N];

            for(int j=0; j<N; ++j){
                String s = sc.next();
                long v = Long.parseLong(s, 2);
                src[j] = v;
            }

            for(int j=0; j<N; ++j){
                String s = sc.next();
                long v = Long.parseLong(s, 2);
                dst[j] = v;
            }
            Arrays.sort(dst);

            ans = Integer.MAX_VALUE;
            solve();

            if(ans == Integer.MAX_VALUE)
                out.println("NOT POSSIBLE");
            else
                out.println(ans);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new A().run();
    }
}
