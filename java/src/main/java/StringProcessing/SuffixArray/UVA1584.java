package StringProcessing.SuffixArray;

/**
 * Created by Tom on 29/4/2022.
 *
 * AC in 0.18s
 *
 * problem: https://onlinejudge.org/external/15/1584.pdf
 * #UVA #Lv2 #suffix_array
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class UVA1584 {
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            //IN = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/StringProcessing/SuffixArray/UVA1584.in";
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


    int MAX_N = 100_010; // second approach: O(n log n)
    char[] T = new char[MAX_N]; // the input string, up to 100K characters
    int n; // the length of input string
    int[] RA = new int[MAX_N], tempRA = new int[MAX_N]; // rank array and temporary rank array
    int[] SA = new int [MAX_N], tempSA = new int[MAX_N]; // suffix array and temporary suffix array
    int[] c = new int[MAX_N];

    void countingSort(int k) { // O(n)
        int i, sum, maxi = Math.max(300, n); // up to 255 ASCII chars or length of n
        Arrays.fill(c, 0); // clear frequency table
        for (i = 0; i < n; i++) // count the frequency of each integer rank
            c[i + k < n ? RA[i + k] : 0]++;
        for (i = sum = 0; i < maxi; i++) {
            int t = c[i]; c[i] = sum; sum += t; }
        for (i = 0; i < n; i++) // shuffle the suffix array if necessary
            tempSA[c[SA[i]+k < n ? RA[SA[i]+k] : 0]++] = SA[i];
        for (i = 0; i < n; i++) // update the suffix array SA
            SA[i] = tempSA[i];
    }

    void constructSA() { // this version can go up to 100000 characters
        int i, k, r;
        for (i = 0; i < n; i++) RA[i] = T[i]; // initial rankings
        for (i = 0; i < n; i++) SA[i] = i; // initial SA: {0, 1, 2, ..., n-1}
        for (k = 1; k < n; k <<= 1) { // repeat sorting process log n times
            countingSort(k); // actually radix sort: sort based on the second item
            countingSort(0); // then (stable) sort based on the first item
            tempRA[SA[0]] = r = 0; // re-ranking; start from rank r = 0
            for (i = 1; i < n; i++) // compare adjacent suffixes
                tempRA[SA[i]] = // if same pair => same rank r; otherwise, increase r
                        (RA[SA[i]] == RA[SA[i-1]] && RA[SA[i]+k] == RA[SA[i-1]+k]) ? r : ++r;
            for (i = 0; i < n; i++) // update the rank array RA
                RA[i] = tempRA[i];
            if (RA[SA[n-1]] == n-1) break; // nice optimization trick
        }
    }


    private String solve(String input) {
        long ans = 0;
        int _n = input.length();
        n = input.length() * 2;
        Arrays.fill(T, '\0');
        for(int i=0; i<_n; ++i){
            T[i] = input.charAt(i);
            T[_n+i] = T[i];
        }
        T[2*_n] = '$';

        constructSA();
        for (int i = 0; i < n; i++)
            if(SA[i] < _n)
                return getCycle(SA[i], _n);

        return null;
    }

    private String getCycle(int start, int nchar){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<nchar; ++i){
            sb.append(T[i+start]);
        }
        return sb.toString();
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            String b = sc.next();
            System.out.println(solve(b));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new UVA1584().run();
    }

}