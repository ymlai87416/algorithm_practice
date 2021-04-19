package GoogleCodeJam.Y2016.Round1B.B;

/**
 * Created by Tom on 9/4/2016.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.TreeMap;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "B-large-practice (1)";
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

    static String iIdx = "0123456789";
    static String leftIdx = "1234567890";
    static String rightIdx = "9012345678";

    private String SA;
    private String SB;

    static class Pair implements Comparable<Pair>{
        long first;
        long second;

        public Pair(long a, long b){
            first = a;
            second = b;
        }

        @Override
        public int compareTo(Pair o) {
            if(first < o.first)
                return -1;
            else if(first > o.first)
                return 1;
            else{
                if(second < o.second)
                    return -1;
                else if(second > o.second)
                    return 1;
                else return 0;
            }
        }
    }

    private Pair minPair(Pair a, Pair b){
        if(a == null) return b;
        if(b  == null) return a;
        long diffA = Math.abs(a.first-a.second);
        long diffB = Math.abs(b.first-b.second);
        if(diffA < diffB)
            return a;
        else if(diffA > diffB)
            return b;
        else{
            if(a.first < b.first)
                return a;
            else if(a.first > b.first)
                return b;
            else{
                if(a.second <= b.second)
                    return a;
                else
                    return b;
            }
        }
    }

    TreeMap<Pair, Pair> dp;

    private Pair solveHelper(int i, long ia, long ib){
        if(i == SA.length()) {
            return new Pair(ia, ib);
        }

        /*Pair dpPair  = new Pair(ia, ib);
        if(dp.containsKey(dpPair))
            return dp.get(dpPair);*/

        char a = SA.charAt(i);
        char b = SB.charAt(i);

        Pair p = null;

        if(ia > ib){
            long nia, nib;
            if(a == '?')
                nia = ia*10+0;
            else
                nia = ia*10+iIdx.indexOf(a);
            if(b == '?')
                nib = ib*10+9;
            else
                nib = ib*10+iIdx.indexOf(b);
            p = solveHelper(i+1, nia, nib);
        }
        else if(ia < ib){
            long nia, nib;
            if(a == '?')
                nia = ia*10+9;
            else
                nia = ia*10+iIdx.indexOf(a);
            if(b == '?')
                nib = ib*10+0;
            else
                nib = ib*10+iIdx.indexOf(b);
            p = solveHelper(i+1, nia, nib);
        }
        else{
            if(a == '?' && b=='?'){

                Pair p1 = solveHelper(i+1, ia*10+0, ib*10+0);
                p = minPair(p, p1);

                p1 = solveHelper(i+1, ia*10+0, ib*10+1);
                p = minPair(p, p1);

                p1 = solveHelper(i+1, ia*10+1, ib*10+0);
                p = minPair(p, p1);

                p1 = solveHelper(i+1, ia*10+0, ib*10+9);
                p = minPair(p, p1);

                p1 = solveHelper(i+1, ia*10+9, ib*10+0);
                p = minPair(p, p1);
            }
            else if(a != '?' && b == '?'){
                Pair p1 = solveHelper(i+1, ia*10+iIdx.indexOf(a), ib*10+iIdx.indexOf(a));
                p = minPair(p, p1);

                p1 = solveHelper(i+1, ia*10+iIdx.indexOf(a), ib*10+leftIdx.indexOf(a));
                p = minPair(p, p1);

                p1 = solveHelper(i+1, ia*10+iIdx.indexOf(a), ib*10+rightIdx.indexOf(a));
                p = minPair(p, p1);

                p1 = solveHelper(i+1, ia*10+iIdx.indexOf(a), ib*10+0);
                p = minPair(p, p1);

                p1 = solveHelper(i+1, ia*10+iIdx.indexOf(a), ib*10+9);
                p = minPair(p, p1);
            }
            else if(a == '?' && b != '?'){

                Pair p1 = solveHelper(i+1, ia*10+iIdx.indexOf(b), ib*10+iIdx.indexOf(b));
                p = minPair(p, p1);

                p1 = solveHelper(i+1, ia*10+leftIdx.indexOf(b), ib*10+iIdx.indexOf(b));
                p = minPair(p, p1);

                p1 = solveHelper(i+1, ia*10+rightIdx.indexOf(b), ib*10+iIdx.indexOf(b));
                p = minPair(p, p1);

                p1 = solveHelper(i+1, ia*10+0, ib*10+iIdx.indexOf(b));
                p = minPair(p, p1);

                p1 = solveHelper(i+1, ia*10+9, ib*10+iIdx.indexOf(b));
                p = minPair(p, p1);
            }
            else if(a != '?' && b != '?'){
                p = solveHelper(i+1, ia*10+iIdx.indexOf(a), ib*10+iIdx.indexOf(b));
            }
        }

        /*try {
            dp.put(dpPair, p);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }*/

        return p;
    }

    private void solve(String a, String b) {
        int ans = 0;

        SA = a;
        SB = b;
        dp = new TreeMap<>();

        Pair p = solveHelper(0, 0, 0);

        String RA = String.valueOf(p.first);
        String RB = String.valueOf(p.second);

        while(RA.length() != SA.length())
            RA = '0'+RA;
        while(RB.length() != SB.length())
            RB = '0'+RB;

        out.format("%s %s\n", RA, RB);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String a = sc.next();
            String b = sc.next();
            solve(a, b);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}