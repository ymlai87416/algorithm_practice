package StringProcessing.StringMatchingStandard;

/**
 * Created by Tom on 29/4/2022.
 *
 * AC in 2.560s
 *
 * problem: https://onlinejudge.org/external/14/1449.pdf
 * #UVA #Lv4 #string #string_matching
 */

import java.io.*;
import java.util.*;

public class UVA1449 {
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            //IN = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/StringProcessing/StringMatchingStandard/UVA1449.in";
            //OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\consistency_chapter_2_output.txt";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            if(OUT == null)
                out = new PrintStream(System.out);
            else out = new PrintStream(OUT);
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

    private void debug(Object... s){
        if(debugflag) {
            //System.out.println(s);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                sb.append(s[i].toString() + " ");
            }
            System.out.println("\033[0;34m" + sb.toString() + "\033[0;30m");
        }
    }


    static final int MAX_N = 1_000_001;
    static char[] T = new char[MAX_N];
    static char[] P = new char[MAX_N]; // T = text, P = pattern
    static int[] b = new int[MAX_N];
    static int n, m; // b = back table, n = length of T, m = length of P

    static void kmpPreprocess() { // call this before calling kmpSearch()
        int i = 0, j = -1;
        b[0] = -1; // starting values
        while (i < m) { // pre-process the pattern string P
            while (j >= 0 && P[i] != P[j]) j = b[j]; // different, reset j using b
            i++;
            j++; // if same, advance both pointers
            b[i] = j; // observe i = 8, 9, 10, 11, 12, 13 with j = 0, 1, 2, 3, 4, 5
        }
    } // in the example of P = "SEVENTY SEVEN" above

    static int kmpSearch() { // this is similar as kmpPreprocess(), but on string T
        ArrayList<Integer> foundPosition = new ArrayList<>();
        int i = 0, j = 0; // starting values
        while (i < n) { // search through string T
            while (j >= 0 && T[i] != P[j]) j = b[j]; // different, reset j using b
            i++;
            j++; // if same, advance both pointers
            if (j == m) { // a match found when j == m
                //System.out.format("%s is found at index %d in T\n", String.valueOf(P, 0, m), i - j);
                foundPosition.add(i-j);
                j = b[j]; // prepare j for the next possible match
            }
        }
        return foundPosition.size();
    }

    static boolean compare(int i, int j){
        return T[i] == P[j];
    }

    private void solve(ArrayList<String> patterns, String text) {
        int maxResult = 0;
        ArrayList<String> resultPatterns = new ArrayList<>();
        n = text.length();
        for(int i=0; i<n; ++i)
            T[i] = text.charAt(i);
        
        for(String pp: patterns) {
            m = pp.length();
            for(int i=0; i<m; ++i)
                P[i] = pp.charAt(i);
            Arrays.fill(b, 0);
            
            kmpPreprocess();
            int occ = kmpSearch();

            if(occ > maxResult){
                maxResult = occ;
                resultPatterns.clear();
                resultPatterns.add(pp);
            }
            else if(occ == maxResult)
                resultPatterns.add(pp);
        }

        out.println(maxResult);
        for(String p : resultPatterns){
            out.println(p);
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        while(true) {
            int p = sc.nextInt();
            if(p == 0) break;
            ArrayList<String> patterns = new ArrayList<>();
            for(int i=0; i<p; ++i)
                patterns.add(sc.next());
            String text = sc.next();
            solve(patterns, text);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new UVA1449().run();
    }

}