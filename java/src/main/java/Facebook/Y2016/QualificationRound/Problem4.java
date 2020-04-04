package Facebook.Y2016.QualificationRound;


import java.io.*;
import java.util.*;

//start at 0:14, stop at 0:30 start again at 21:15, AC at 22:30
public class Problem4 {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "C:\\Users\\Tom\\Documents\\algorithm_practice\\text_editor";
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

    static class Pair implements Comparable<Pair>{
        int first, second;
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            if(first < o.first) return 1;
            else if(first > o.first) return -1;
            else return second - o.second;
        }
    }

    int[][][] m = new int[301][301][301];

    private int numOp(String start, String end){
        int sc = Math.min(start.length(), end.length());
        int common = 0;
        for(int i=0; i<sc; ++i)
            if(start.charAt(i) == end.charAt(i))
                common += 1;
            else            //really, your coding skill sucks... waste me 1 hour in here....
                break;

        return start.length() - common + (end.length() - common);
    }

    private int helper(int left, int n, int k){
        int result = 0;

        if (n < k)
            return Integer.MAX_VALUE;

        if(m[left][n][k] != -1) return m[left][n][k];

        if(k==0){
            int r = numOp(words[left], "");
            m[left][n][k] = r;
            return m[left][n][k];
        }

        int resultA = helper(n, n-1, k-1) + numOp(words[left], words[n]) + 1;
        int resultB = helper(left, n-1, k);
        result = Math.min(resultA, resultB);
        m[left][n][k] = result;

        return m[left][n][k];
    }

    private void solve(int c, int n, int k, String[] words) {
        int result;

        for(int i=0; i<301; ++i){
            for(int j=0; j<301; ++j){
                for(int x=0; x<301; ++x)
                    m[i][j][x] = -1;
            }

        }
        result = helper(0, n, k);

        System.out.println(result);
        out.println(result);
    }

    String[] words = new String[301];

    private void run() throws Exception {

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            System.out.print("Case #" + i + ": ");
            out.print("Case #" + i + ": ");
            int n = sc.nextInt();
            int k = sc.nextInt();
            sc.nextLine();

            words[0] = "";
            for(int j=1; j<=n; ++j){
                words[j] = sc.nextLine();
            }

            Arrays.sort(words, 1, n+1);
            String temp;
            for(int p=1, q=n; p <q; ++p , --q) {
                temp = words[p];
                words[p] = words[q];
                words[q] = temp;
            }

//            for(int x=0; x<=n; ++x)
//                System.out.print(words[x] + " ");
//            System.out.println();

            solve(i, n, k , words);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Problem4().run();
    }
}
