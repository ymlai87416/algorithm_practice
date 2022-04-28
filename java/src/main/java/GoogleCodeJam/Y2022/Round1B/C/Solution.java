package GoogleCodeJam.Y2022.Round1B.C;
/**
 * Created by Tom on 9/4/2016.
 */
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            //IN = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/GoogleCodeJam/Y2022/Round1B/C/C-test.in";
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

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    int magic=51;
    private int[] rotate8(int data){
        int[] r = new int[8];
        r[0] = data;
        for (int i = 1; i < 8; i++){
            r[i] = rotate8_(data, i);
        }

        return r;
    }

    private int rotate8_(int data, int distance){
        distance = distance & 7;
        data &= 0xFF;
        return (data >> distance) | (data << (8 - distance)) & 0xFF;
    }

    private int count1(int n){
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    private HashSet[] initGuess(int magic) {
        int[] rr = rotate8(magic);

        HashSet[] bin = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            bin[i]  = new HashSet<Integer>();
        }
        for (int i = 1; i < 256; i++) {
            for (int j: rr) {
                int re = i^j;
                int c1 = count1(re);
                bin[c1].add(re);
            }
        }
        return bin;
    }

    private String formatN(int N){
        return String.format("%8s", Integer.toBinaryString(N)).replace(' ', '0');
    }

    private boolean solve() {
        Random r = new Random();
        out.println(formatN(magic));
        var guess = initGuess(magic);
        while(true){
            int cnt1 = sc.nextInt();
            if(cnt1 == 0)
                return true;
            else if(cnt1== -1)
                return false;
            else{
                //System.err.println("Got xx" + cnt1);

                HashSet<Integer> curSet =guess[cnt1];
                var bestGuess = formatN(0);
                var maxZeroCount = 0;
                var minLenMax = 0;
                var nextBin = guess;

                /*
                var randomCheck = new HashSet<Integer>();

                while(randomCheck.size() < 20){
                    randomCheck.add(r.nextInt(255) + 1);
                }*/

                for (int i=1; i<256; ++i) {

                    var bin = new HashSet[9];
                    for (int j = 0; j < 9; j++) {
                        bin[j] = new HashSet<Integer>();
                    }

                    var rr = rotate8(i);
                    int lenZero = 0;

                    for (int p: curSet) {

                        for(int j:  rr){
                            var re = p ^ j;
                            var c1  =count1(re);
                            bin[c1].add(re);
                            if(re == 0) ++lenZero;
                        }
                    }

                    var lenMax = 0;
                    for (int t = 1; t < 9; t++) {
                        lenMax = Math.max(lenMax,bin[t].size());
                    }

                    if (bestGuess.compareTo("00000000") == 0 ||
                            (lenZero > maxZeroCount) || (lenZero == maxZeroCount && lenMax < minLenMax)
                    ) {
                        bestGuess = formatN(i);
                        maxZeroCount = lenZero;
                        minLenMax = lenMax;
                        nextBin = bin;
                    }

                }

                out.println(bestGuess);
                guess = nextBin;

            }

        }
    }

    private void test(){
        int[] case1 = rotate8(0b11111111);
        int[] case2 = rotate8(0b00000000);
        int[] case3 = rotate8(0b01111101);

        for (int ii: case3) {
            System.err.println(formatN(ii));
        }
    }

    private void run() throws Exception {

        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            boolean r = solve();
            if(!r) break;
        }

        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        Solution sol = new Solution();
        //sol.test();
        sol.run();
    }

}